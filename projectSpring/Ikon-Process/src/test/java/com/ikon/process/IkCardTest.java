package com.ikon.process;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ikon.process.service.IkCardNeg;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class IkCardTest {

	private static final Logger LOGG = LoggerFactory.getLogger(IkCardTest.class);
	
	@Autowired
	private IkCardNeg cardNeg;
//	
//	@Test
//	public void testInjectionDao () {
//			
//		LOGG.info("[:::::::::  {}  ::::::::]", "Validating injection of dependency");
//		assertNotNull(cardNeg);
//	
//	}
	
	@Test
	public void testInsertTopic () {
		
		LOGG.info("[:::::::::  {}  ::::::::]", "Validating injection of dependency");
		String requestCardTopic = null;
		requestCardTopic = "{\"cveTopic\":0,\"topicName\":\"PRONOUMS\",\"cveCard\":0,\"cardText\":\"I\",\"pathImage\":\"RUTA DE LA IMAGEN\",\"pathSound\":\"RUTA DEL SONIDO\",\"pathVideo\":\"RUTA DEL VIDEO\",\"pathMeaning\":\"RUTA DEL PDF\"}";
		requestCardTopic = "{\"cveTopic\":0,\"topicName\":\"COLORS\",\"cveCard\":0,\"cardText\":\"RED\",\"pathImage\":\"RUTA DE LA IMAGEN\",\"pathSound\":\"RUTA DEL SONIDO\",\"pathVideo\":\"RUTA DEL VIDEO\",\"pathMeaning\":\"RUTA DEL PDF\"}";
		requestCardTopic = "{\"cveTopic\":0,\"topicName\":\"VERB TO BE\",\"cveCard\":0,\"cardText\":\"AM\",\"pathImage\":\"RUTA DE LA IMAGEN\",\"pathSound\":\"RUTA DEL SONIDO\",\"pathVideo\":\"RUTA DEL VIDEO\",\"pathMeaning\":\"RUTA DEL PDF\"}";
		ResponseEntity<Object> entity = null;
		
		entity = cardNeg.insertCardTopic(requestCardTopic);
		
		assertEquals(200, entity.getStatusCodeValue());
		
	}
	
//	@Test
//	public void testInsert () {
//		
//		LOGG.info("[:::::::::  {}  ::::::::]", "Validating injection of dependency");
//		String requestCardTopic = null;
//		requestCardTopic = "{\"cveTopic\":1,\"topicName\":\"PRONOUMS\",\"cveCard\":0,\"cardText\":\"YOU\",\"pathImage\":\"RUTA DE LA IMAGEN\",\"pathSound\":\"RUTA DEL SONIDO\",\"pathVideo\":\"RUTA DEL VIDEO\",\"pathMeaning\":\"RUTA DEL PDF\"}";
//		ResponseEntity<Object> entity = null;
//		
//		entity = cardNeg.insertCardTopic(requestCardTopic);
//		
//		assertEquals(200, entity.getStatusCodeValue());
//		
//	}
	
//	@Test
//	public void testExistCardTopic () {
//		
//		LOGG.info("[:::::::::  {}  ::::::::]", "Validating injection of dependency");
//		String requestCardTopic = null;
//		requestCardTopic = "{\"cveTopic\":1,\"topicName\":\"PRONOUMS\",\"cveCard\":0,\"cardText\":\"YOU\",\"pathImage\":\"RUTA DE LA IMAGEN\",\"pathSound\":\"RUTA DEL SONIDO\",\"pathVideo\":\"RUTA DEL VIDEO\",\"pathMeaning\":\"RUTA DEL PDF\"}";
//		ResponseEntity<Object> entity = null;
//		
//		entity = cardNeg.insertCardTopic(requestCardTopic);
//		
//		assertEquals(204, entity.getStatusCodeValue());
//		
//	}
	
	
	
	
	
  
   
   
}
