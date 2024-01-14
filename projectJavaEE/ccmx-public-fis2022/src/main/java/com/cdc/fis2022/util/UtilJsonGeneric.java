package com.cdc.fis2022.util;


import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;

public class UtilJsonGeneric {
	
	private static Logger LOGG = LogManager.getLogger(UtilJsonGeneric.class);
	
	   /**
		 * Valida que el request sea correcto y de acuerdo a la estructura
		 * de la clase principal
		 * @param <T>
		 * @param requestJson
		 * @param object
		 * @return
		 */
		public static <T> boolean validaRequestObject (String requestJson, Class<T> claseObjeto) {
			boolean valida = false;
			
			String nombreClase = claseObjeto.toString();
			
			LOGG.info("[:::::::::::: Validando Request-Json nobreClase: {}  :::::::::::]", nombreClase);
			
			try {
				
				ObjectMapper mapper = new ObjectMapper();
				mapper.getFactory().enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
				@SuppressWarnings("unused")
				Object result = mapper.readValue(requestJson, claseObjeto);
				valida = true;
			} catch (InvalidFormatException e) {
				LOGG.info("[:::::::  InvalidFormatException {} InvalidFormatException {} :::::::::::]", nombreClase, e.getMessage());
			} catch (JsonParseException e) {
				LOGG.info("[:::::::  InvalidFormatException {} JsonParseException {} :::::::::::]", nombreClase, e.getMessage());
			} catch (Exception e) {
				LOGG.info("[:::::::  InvalidFormatException {} Exception {} :::::::::::]", nombreClase, e.getMessage());
			}
			
			LOGG.info("[:::::::::::: ValidaRequest.validaRequestSaveCreditData Status  { " + valida +" } :::::::::::]");
			
			
			return valida;
		} 
		
		/***
		 * convierte una cadena de tipo json a Objeto
		 * @param <T>
		 * @param requestJson
		 * @return
		 */
		public static <T> Object convierteJsonObject (String requestJson, Class<T> claseObjeto ) {
			
			LOGG.info("[:::::::::::: ConvierteJsonObjeto.convierteJsonObjecto { Conviertiendo JSON en Objeto } :::::::::::]");
			
			Object objectRequest = new Object();
			ObjectMapper objectMapper = new ObjectMapper();
			
			try {
				objectRequest = objectMapper.readValue(requestJson, claseObjeto);
				
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				LOGG.error("JsonParseException " + e.getMessage());
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				LOGG.error("JsonMappingException " + e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOGG.error("IOException " + e.getMessage());
				e.printStackTrace();
			}
			
			return objectRequest;
		}  

}
