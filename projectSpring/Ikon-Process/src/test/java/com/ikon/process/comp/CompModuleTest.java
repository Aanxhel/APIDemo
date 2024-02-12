package com.ikon.process.comp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ikon.process.dao.IkModuleDao;
import com.ikon.process.dto.CompModuleDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CompModuleTest {

	private static final Logger LOGG = LoggerFactory.getLogger(CompModuleTest.class);
	
	@Autowired
	private IkModuleDao compModuleDao;
	
	
	@Test
	public void testInjectionDao () {
		
		LOGG.info("[:::::::::  {}  ::::::::]", "Validando CI");
		assertNotNull(compModuleDao);
	
	}
	
	@Test
	public void getCompModuleTest () {
		LOGG.info("[:::::::::  {}  ::::::::]", "Validando Contenido objeto NOT NULL");
		
		try {
			ArrayList<CompModuleDto> compModule = compModuleDao.obtieneModule();
			assertNotNull(compModule);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
