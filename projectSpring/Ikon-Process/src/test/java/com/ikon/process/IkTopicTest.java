package com.ikon.process;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ikon.process.dao.IkTopicDao;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class IkTopicTest {

	private static final Logger LOGG = LoggerFactory.getLogger(IkTopicTest.class);
	
	@Autowired
	private IkTopicDao ikTopicDao;
	
	@Test
	public void testInjectionDao () {
			
		LOGG.info("[:::::::::  {}  ::::::::]", "Validating injection of dependency");
		assertNotNull(ikTopicDao);
	
	}
	
	@Test
	public void testInsertRecord () {
		Integer sequence = new Integer (0);
		
		try {
			
			sequence = this.ikTopicDao.insertTopic("PRONOUMS III");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotEquals(-1, sequence); 
		
		
	}
	
	
	@Test
	public void testInsertExistRecord () {
		Integer sequence = new Integer (0);
		
		try {
			
			sequence = this.ikTopicDao.insertTopic("PRONOUMS II");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(-1, sequence); 
		
	}
	
	
	
  
   
   
}
