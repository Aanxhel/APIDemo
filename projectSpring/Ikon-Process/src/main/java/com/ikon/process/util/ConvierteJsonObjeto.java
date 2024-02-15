/**
 * 
 */
package com.ikon.process.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikon.process.json.request.RequestCatalogoJson;
import com.ikon.process.json.request.RequestCompModuleJson;
import com.ikon.process.json.request.RequestDatosFameJson;
import com.ikon.process.json.request.RequestFrameAJson;

/**
 * Clase encargada de convertir un Json a objetos
 * @author IsraelC
 * @since Julio 24, 2021
 *
 */
public class ConvierteJsonObjeto {
	private static final Logger LOGG = LoggerFactory.getLogger(ConvierteJsonObjeto.class);

	/**
	 * Genera una instancia de JsonParsery - JsonGenerator para 
	 * implementar la lectura y escritura de una cadena JSON 
	 */
	private static ObjectMapper mapper = new ObjectMapper();

	
	/***
	 * convierte una cadena de tipo json a Objeto
	 * @param requestJson
	 * @return
	 */
	public static RequestCatalogoJson convierteJsonObjectoCatalogo (String requestCatalogoJson ) {
		
		LOGG.info("[:::::::::::: {Conviertiendo JSON en Objeto } :::::::::::]");
		
		RequestCatalogoJson request = new RequestCatalogoJson();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			request = objectMapper.readValue(requestCatalogoJson, RequestCatalogoJson.class);
			
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
		
		return request;
	} 
	
	
	/***
	 * convierte una cadena de tipo json a Objeto
	 * @param requestJson
	 * @return
	 */
	public static RequestFrameAJson convierteJsonFrameA (String requestFrameAJson ) {
		
		LOGG.info("[:::::::::::: { Conviertiendo JSON en Objeto RequestFrameAJson } :::::::::::]");
		
		RequestFrameAJson request = new RequestFrameAJson();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			request = objectMapper.readValue(requestFrameAJson, RequestFrameAJson.class);
			
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
		return request;
	} 
	
	
	/***
	 * convierte una cadena de tipo json a Objeto
	 * @param requestJson
	 * @return
	 */
	public static RequestDatosFameJson convierteJsonBusquedaDatosFrame (String requestBusquedaFrame ) {
		
		LOGG.info("[:::::::::::: {Conviertiendo JSON en Objeto } :::::::::::]");
		
		RequestDatosFameJson request = new RequestDatosFameJson();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			request = objectMapper.readValue(requestBusquedaFrame, RequestDatosFameJson.class);
			
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
		return request;
	} 
	
	
	/***
	 * convierte una cadena de tipo json a Objeto
	 * @param requestJson
	 * @return
	 */
	public static RequestCompModuleJson convierteJsonCompModule (String requestCompModule ) {
		
		LOGG.info("[:::::::::::: { } :::::::::::]", "Converting JSON to Object");
		
		RequestCompModuleJson request = new RequestCompModuleJson();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			request = objectMapper.readValue(requestCompModule, RequestCompModuleJson.class);
			
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
		
		return request;
	} 
	
	
	

	/**
	 * Convierte un Objeto a cadena JSON
	 * @param objeto
	 * @return
	 * @throws JsonProcessingException 
	 */
	public static String convierteObjectJson(Object objeto) throws JsonProcessingException {
		String json = null;
		try {
			json = mapper.writeValueAsString(objeto);
		} catch (JsonProcessingException e) {
			throw e;
		}
		return json;
	}

	

}
