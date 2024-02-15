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
import com.ikon.process.json.request.IKRequestActivityLogJson;
import com.ikon.process.json.request.IkRequestCard;
import com.ikon.process.json.request.IkRequestFileJson;
import com.ikon.process.json.request.IkRequestModuleJson;
import com.ikon.process.json.request.IkRequestSentenceCard;
import com.ikon.process.json.request.IkRequestSessionJson;

/**
 * Class in charge of converting a JSON to Objects
 * @author IsraelC
 * @since Octuber, 2021
 *
 */
public class IkConvierteJsonObjeto {
	private static final Logger LOGG = LoggerFactory.getLogger(IkConvierteJsonObjeto.class);

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
	public static IkRequestCard convierteCardTopic (String requestCardTopic ) {
		
		LOGG.info("[:::::. { } .:::::]", "Converting JSON to Object - requestCardTopic ");
		
		IkRequestCard request = new IkRequestCard();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			
			request = objectMapper.readValue(requestCardTopic, IkRequestCard.class);
			
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
	public static IkRequestSentenceCard convierteSenteceCard (String requestSentenceCard ) {
		
		LOGG.info("[:::::. { } .:::::]", "Converting JSON to Object - requestCardTopic ");
		
		IkRequestSentenceCard request = new IkRequestSentenceCard();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			
			request = objectMapper.readValue(requestSentenceCard, IkRequestSentenceCard.class);
			
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
	public static IkRequestSessionJson convierteSession (String requestSessionJson ) {
		
		LOGG.info("[:::::. { } .:::::]", "Converting JSON to Object - convierteSession ");
		
		IkRequestSessionJson request = new IkRequestSessionJson();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			
			request = objectMapper.readValue(requestSessionJson, IkRequestSessionJson.class);
			
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
	public static IkRequestModuleJson convierteModule (String requestModuleJson ) {
		
		LOGG.info("[:::::. { } .:::::]", "Converting JSON to Object - convierteModule ");
		
		IkRequestModuleJson request = new IkRequestModuleJson();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			
			request = objectMapper.readValue(requestModuleJson, IkRequestModuleJson.class);
			
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
	
	
	 /* convierte una cadena de tipo json a Objeto
	 * @param requestJson
	 * @return
	 */
	public static IkRequestFileJson convierteDatoImagen (String requestDatoImagenJson ) {
		
		LOGG.info("[:::::. { } .:::::]", "Converting JSON to Object - convierteDatoImagen ");
		
		IkRequestFileJson request = new IkRequestFileJson();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			request = objectMapper.readValue(requestDatoImagenJson, IkRequestFileJson.class);
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

	/* convierte una cadena de tipo json a Objeto
	 * @param requestJson
	 * @return
	 */
	public static IKRequestActivityLogJson convierteActivityLog (String requestActivityMonitoryJson ) {
		
		LOGG.info("[:::::. { } .:::::]", "Converting JSON to Object - requestActivityMonitoryJson ");
		
		IKRequestActivityLogJson request = new IKRequestActivityLogJson();
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			request = objectMapper.readValue(requestActivityMonitoryJson, IKRequestActivityLogJson.class);
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

}
