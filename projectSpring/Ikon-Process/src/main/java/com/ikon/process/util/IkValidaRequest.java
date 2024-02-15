/**
 * 
 */
package com.ikon.process.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.ikon.process.json.request.IKRequestActivityLogJson;
import com.ikon.process.json.request.IkRequestCard;
import com.ikon.process.json.request.IkRequestFileJson;
import com.ikon.process.json.request.IkRequestModuleJson;
import com.ikon.process.json.request.IkRequestSentenceCard;
import com.ikon.process.json.request.IkRequestSessionJson;

/**
 * @author IsraelC
 * @since Octuber, 2021
 *
 */
public class IkValidaRequest {
	
	private static final Logger LOGG = LoggerFactory.getLogger(IkValidaRequest.class);
	/**
	 * Valida que el request sea correcto y de acuerdo a la estructura
	 * de la clase principal
	 * @param requestJson
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unused")
	public static boolean validaRequestCardTopic( String requestCardTopic ) {
		boolean valida = false;
		LOGG.info("[:::::::::::: { Validando Structura JSON IkRequestCard } :::::::::::]");
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.getFactory().enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
			IkRequestCard result = mapper.readValue(requestCardTopic, IkRequestCard.class);
			valida = true;
		} catch (InvalidFormatException e) {
			LOGG.error("[::::::: InvalidFormatException JSON  " +e.getMessage()  +":::::::::::]" );
			System.out.println("[::::::: Error estructura JSON :::::::::::]" + e.getMessage());
		} catch (JsonParseException e) {
			// TODO: handle exception
			LOGG.error("[::::::: JsonParseException JSON  " +e.getMessage()  +":::::::::::]" );
			
		} catch (Exception e) {
			LOGG.error("[::::::: Exception  " +e.getMessage()  +":::::::::::]" );
			System.out.println("[::::::: Error estructura JSON :::::::::::]" + e);
		}
		return valida;
	}
	
	
	/**
	 * Valida que el request sea correcto y de acuerdo a la estructura
	 * de la clase principal
	 * @param requestJson
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unused")
	public static boolean validaRequestCardSentences ( String requestSentenceCard ) {
		boolean valida = false;
		LOGG.info("[:::::::::::: { Validando Structura JSON IkRequestCard } :::::::::::]");
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.getFactory().enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
			IkRequestSentenceCard result = mapper.readValue(requestSentenceCard, IkRequestSentenceCard.class);
			valida = true;
		} catch (InvalidFormatException e) {
			LOGG.error("[::::::: InvalidFormatException JSON  " +e.getMessage()  +":::::::::::]" );
			System.out.println("[::::::: Error estructura JSON :::::::::::]" + e.getMessage());
		} catch (JsonParseException e) {
			// TODO: handle exception
			LOGG.error("[::::::: JsonParseException JSON  " +e.getMessage()  +":::::::::::]" );
			
		} catch (Exception e) {
			LOGG.error("[::::::: Exception  " +e.getMessage()  +":::::::::::]" );
			System.out.println("[::::::: Error estructura JSON :::::::::::]" + e);
		}
		return valida;
	}
	
	/**
	 * Valida que el request sea correcto y de acuerdo a la estructura
	 * de la clase principal
	 * @param requestJson
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unused")
	public static boolean validaRequestSession ( String requestSessionJson ) {
		boolean valida = false;
		LOGG.info("[:::::::::::: { Validando Structura JSON IkRequestCard } :::::::::::]");
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.getFactory().enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
			IkRequestSessionJson result = mapper.readValue(requestSessionJson, IkRequestSessionJson.class);
			valida = true;
		} catch (InvalidFormatException e) {
			LOGG.error("[::::::: InvalidFormatException JSON  " +e.getMessage()  +":::::::::::]" );
			System.out.println("[::::::: Error estructura JSON :::::::::::]" + e.getMessage());
		} catch (JsonParseException e) {
			// TODO: handle exception
			LOGG.error("[::::::: JsonParseException JSON  " +e.getMessage()  +":::::::::::]" );
			
		} catch (Exception e) {
			LOGG.error("[::::::: Exception  " +e.getMessage()  +":::::::::::]" );
			System.out.println("[::::::: Error estructura JSON :::::::::::]" + e);
		}
		return valida;
	}
	
	/**
	 * Valida que el request sea correcto y de acuerdo a la estructura
	 * de la clase principal
	 * @param requestJson
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unused")
	public static boolean validaRequestModule ( String requestModuleJson ) {
		boolean valida = false;
		LOGG.info("[:::::::::::: { Validando Structura JSON requestModuleJson } :::::::::::]");
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.getFactory().enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
			IkRequestModuleJson result = mapper.readValue(requestModuleJson, IkRequestModuleJson.class);
			valida = true;
		} catch (InvalidFormatException e) {
			LOGG.error("[::::::: InvalidFormatException JSON  " +e.getMessage()  +":::::::::::]" );
			System.out.println("[::::::: Error estructura JSON :::::::::::]" + e.getMessage());
		} catch (JsonParseException e) {
			// TODO: handle exception
			LOGG.error("[::::::: JsonParseException JSON  " +e.getMessage()  +":::::::::::]" );
			
		} catch (Exception e) {
			LOGG.error("[::::::: Exception  " +e.getMessage()  +":::::::::::]" );
			System.out.println("[::::::: Error estructura JSON :::::::::::]" + e);
		}
		return valida;
	}
	
	/**
	 * Valida que el request sea correcto y de acuerdo a la estructura
	 * de la clase principal
	 * @param requestJson
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unused")
	public static boolean validaRequestDatoFile ( String requestDatoImagenJson ) {
		boolean valida = false;
		LOGG.info("[:::::::::::: { Validando Structura JSON requestDatoImagenJson } :::::::::::]");
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.getFactory().enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
			IkRequestFileJson result = mapper.readValue(requestDatoImagenJson, IkRequestFileJson.class);
			valida = true;
		} catch (InvalidFormatException e) {
			LOGG.error("[::::::: requestDatoImagenJson JSON  " +e.getMessage()  +":::::::::::]" );
			System.out.println("[::::::: Error requestDatoImagenJson JSON :::::::::::]" + e.getMessage());
		} catch (JsonParseException e) {
			// TODO: handle exception
			LOGG.error("[::::::: JsonParseException  requestDatoImagenJson JSON  " +e.getMessage()  +":::::::::::]" );
			
		} catch (Exception e) {
			LOGG.error("[::::::: Exception  " +e.getMessage()  +":::::::::::]" );
			System.out.println("[::::::: Error estructura JSON :::::::::::]" + e);
		}
		return valida;
	}
	
	
	/**
	 * Valida que el request sea correcto y de acuerdo a la estructura
	 * de la clase principal
	 * @param requestJson
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unused")
	public static boolean validaRequestActivityLog ( String requestActivityMonitoryJson ) {
		boolean valida = false;
		LOGG.info("[:::::::::::: { Validando Structura JSON IKRequestActivityLogJson } :::::::::::]");
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.getFactory().enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
			IKRequestActivityLogJson result = mapper.readValue(requestActivityMonitoryJson, IKRequestActivityLogJson.class);
			valida = true;
		} catch (InvalidFormatException e) {
			LOGG.error("[::::::: InvalidFormatException JSON  " +e.getMessage()  +":::::::::::]" );
			System.out.println("[::::::: Error estructura JSON :::::::::::]" + e.getMessage());
		} catch (JsonParseException e) {
			// TODO: handle exception
			LOGG.error("[::::::: JsonParseException JSON  " +e.getMessage()  +":::::::::::]" );
			
		} catch (Exception e) {
			LOGG.error("[::::::: Exception  " +e.getMessage()  +":::::::::::]" );
			System.out.println("[::::::: Error estructura JSON :::::::::::]" + e);
		}
		return valida;
	}
	
	
	

}
