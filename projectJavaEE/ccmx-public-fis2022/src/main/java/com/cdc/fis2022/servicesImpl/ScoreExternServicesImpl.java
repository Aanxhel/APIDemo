package com.cdc.fis2022.servicesImpl;


import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cdc.fis2022.beans.BeanAccesN;
import com.cdc.fis2022.beans.Chain;
import com.cdc.fis2022.beans.ResponseScoreExtern;
import com.cdc.fis2022.services.IScoreExternServices;
import com.cdc.fis2022.util.UtilJsonGeneric;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
public class ScoreExternServicesImpl implements IScoreExternServices{
	
	@Value("${url.score.extern}")
	private String urlScoreExtern;
	
	
	private RestTemplate restTemplate = new RestTemplate();
	
	
	private static final Logger logger = LogManager.getLogger("ScoreExternServiceImpl");

	@Override
	public ResponseEntity<ResponseScoreExtern> sendCadenagetScore(Chain chain, BeanAccesN keyA) {
//		ResponseEntity<ResponseScoreExtern> responseScore = new ResponseEntity<ResponseScoreExtern>();
		ResponseScoreExtern responseScoreExtern = new ResponseScoreExtern();


		logger.info("ruta de scoreExtern ====> "+urlScoreExtern);

		
		try {
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.set("X-DEVOPS-APPNAME", "scoreFis-qa"); // keyA.getDEVOPS_APP_NAME()
			httpHeaders.set("X-DEVOPS-AUTH", "pY74mstFTtCmyTsy6gkMLVYS8UwKgNxVTZ.qmLCjy6zMPpgNJJNQ63Sio9_N5Tood"); // keyA.getDEVOPS_AUTH()	
			
//			Map<String, String> params = new HashMap<String, String>();
//			params.put("cadenaVariables", chain.getChain());
			
//			JsonObject jsonObject = new JsonObject();
//			jsonObject.addProperty("cadenaVariables", chain.getChain());
			
			// chain = "{\"cadenaVariables\": \""+chain+"\"}";
			chain.setChain("{\"cadenaVariables\": \""+chain.getChain()+"\"}");
			
			logger.info("Se enviaran estos accesos de NGINX, BORRAR SOLO PARA LOG => "+httpHeaders+"\n");
//			logger.info("params => "+params);
		
			HttpEntity<Object> entity = new HttpEntity<Object>(chain.getChain(), httpHeaders);
			
			
			
			logger.info("entidad a enviar al servicio de score entity => "+entity+"\n");		
			ResponseEntity<ResponseScoreExtern> responseEntity = restTemplate.postForEntity(urlScoreExtern, entity, ResponseScoreExtern.class);		
			logger.info("\nresponseEntity en score External => "+responseEntity+"\n");
	
			if(responseEntity.getBody().getScoreNoHit().getScore() > 0) {
				responseScoreExtern = responseEntity.getBody();	
				
				
				logger.info("return del servicio score Extern => "+responseEntity.getBody()+"\n");
				return new ResponseEntity<>(responseScoreExtern, HttpStatus.OK);
			
			}else {
				logger.info("No se logro devovler Score ... "+responseEntity.getStatusCode());
				responseScoreExtern = responseEntity.getBody();
			//	responseEntity.getStatusCode().valueOf(404);
				return new ResponseEntity<>(responseScoreExtern, HttpStatus.NOT_FOUND);
	
			}
			
		
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("Error al invocar servicio de Score External ....");
			logger.info("=> "+e.getMessage()+"\n => "+e.getCause()+
			e.getStackTrace()+"\n"+
			e.getLocalizedMessage());
		}
		
		
		return new ResponseEntity<>(responseScoreExtern, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// ******************************************
	
	
	public String convertObjectToJson(Chain chain) {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonStr = null;
		try {
				jsonStr = objectMapper.writeValueAsString(chain);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonStr;
} 

	
	
	
	
	
	
	
	
	

}
