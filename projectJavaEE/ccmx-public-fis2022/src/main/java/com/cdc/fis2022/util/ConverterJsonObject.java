package com.cdc.fis2022.util;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cdc.fis2022.beans.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//public class ConverterJsonObject {

//	private static final Logger logger = LogManager.getLogger("ConverterJsonObject");
	/***
	* convierte una cadena de tipo json a Objeto
	* @param requestJson
	* @return
	*/
	/*
	public static RequestMatch convierteJsonObjectoMatch (String requestJson ) {
	
		logger.info("[:::::::::::: ConvierteJsonObjeto.convierteJsonObjectoMatch { Conviertiendo JSON en Objeto } :::::::::::]");
	
	
	RequestMatch requestPagoJson = new RequestMatch();
	ObjectMapper objectMapper = new ObjectMapper();
	
	try {
		requestPagoJson = objectMapper.readValue(requestJson, RequestMatch.class);
		
	} catch (JsonParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return requestPagoJson;
} 
}
*/