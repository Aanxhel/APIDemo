package com.ikon.process;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ikon.process.service.ProcessFrameANeg;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FrameATest {

	private static final Logger LOGG = LoggerFactory.getLogger(FrameATest.class);
	
	@Autowired
	private ProcessFrameANeg processFrameANeg;
	
	
	@Test
	public void testInjectionDao () {
		
		LOGG.info("[:::::::::  {}  ::::::::]", "FrameATest Validando CI");
		assertNotNull(processFrameANeg);
	
	}
	
   @Test
    public void obtieneCatHeader () {
	
	   String requestBusquedaFrame = "{\"cveModulo\":1,\"cveTopic\":1}";
	   
	   LOGG.info("[:::::::::  {}  ::::::::]", " Iniciando pruebas unitarias ");
	   ResponseEntity<Object>  entity = processFrameANeg.frameBusqueda(requestBusquedaFrame);
	   assertNotNull(entity);
	
	}
   
   @Test
   public void agregaRegistroTest () {
	   
	String requestFrameAJson = "{\"cveModule\":1,\"cveTopic\":1,\"desTxtSent\":\"orange\",\"pathDesImgSent\":\"/Users/icb_ipsra/Documents/imagens/pathDesImgSent.jpeg\",\"pathDSoundSent\":\"/Users/icb_ipsra/Documents/imagens/pathDSoundSent.jpeg\",\"pathVideoSent\":\"/Users/icb_ipsra/Documents/imagens/pathVideoSent.jpeg\",\"pathMeanSent\":\"/Users/icb_ipsra/Documents/imagens/pathMeanSent.jpeg\",\"pathDesInfoSent\":\"/Users/icb_ipsra/Documents/imagens/pathDesInfoSent.jpeg\",\"cveStatus\":0}";
	ResponseEntity<Object>  entity = processFrameANeg.agregaDatosFrameA(requestFrameAJson);
	assertNotNull(entity);
	   
	   
   }
   
   
}
