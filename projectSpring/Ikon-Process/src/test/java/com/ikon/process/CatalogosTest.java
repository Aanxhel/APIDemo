package com.ikon.process;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ikon.process.dao.CatHeaderDao;
import com.ikon.process.dto.CatHeaderDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CatalogosTest {

	private static final Logger LOGG = LoggerFactory.getLogger(CatalogosTest.class);
	
	@Autowired
	private CatHeaderDao catalogosDao;
	
	
	//@Test
	public void testInjectionDao () {
		
		LOGG.info("[:::::::::  {}  ::::::::]", "Validando CI");
		assertNotNull(catalogosDao);
	
	}
	
	@Test
	public void obtieneCatHeader () {
	
		try {
			
			ArrayList<CatHeaderDto> catHeader = catalogosDao.obtieneHeader();
			
			assertNotNull(catHeader);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
