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
import com.ikon.process.json.request.RequestCatalogoJson;
import com.ikon.process.json.request.RequestCompModuleJson;
import com.ikon.process.json.request.RequestDatosFameJson;
import com.ikon.process.json.request.RequestFrameAJson;

/**
 * @author IsraelC
 * @since Julio,24 2021
 *
 */
public class ValidaRequest {
	
	private static final Logger LOGG = LoggerFactory.getLogger(ValidaRequest.class);
	/**
	 * Valida que el request sea correcto y de acuerdo a la estructura
	 * de la clase principal
	 * @param requestJson
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unused")
	public static boolean validaRequestCatalogo( String requestCatalogoJson) {
		boolean valida = false;
		LOGG.info("[:::::::::::: { Validando Structura JSON } :::::::::::]");
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.getFactory().enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
			RequestCatalogoJson result = mapper.readValue(requestCatalogoJson, RequestCatalogoJson.class);
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
	public static boolean validaRequestbusquedaFrame( String requestBusquedaFrame) {
		boolean valida = false;
		LOGG.info("[:::::::::::: { Validando Structura JSON requestBusquedaFrame } :::::::::::]");
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.getFactory().enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
			RequestDatosFameJson result = mapper.readValue(requestBusquedaFrame, RequestDatosFameJson.class);
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
	public static boolean validaRequestDatosFrame( String requestFrameAJson) {
		boolean valida = false;
		LOGG.info("[:::::::::::: { Validando Structura JSON requestFrameAJson } :::::::::::]");
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.getFactory().enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
			RequestFrameAJson result = mapper.readValue(requestFrameAJson, RequestFrameAJson.class);
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
	
	
	
	//-- New version
	
	/**
	 * Valida que el request sea correcto y de acuerdo a la estructura
	 * de la clase principal
	 * @param requestJson
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unused")
	public static boolean validaRequestCompModule( String requestCompModule) {
		boolean valida = false;
		LOGG.info("[:::::::::::: {} :::::::::::]", "JSON validating");
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.getFactory().enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
			RequestCompModuleJson result = mapper.readValue(requestCompModule, RequestCompModuleJson.class);
			valida = true;
		} catch (InvalidFormatException e) {
			LOGG.error("[::::::: InvalidFormatException JSON  " +e.getMessage()  +":::::::::::]" );
		} catch (JsonParseException e) {
			// TODO: handle exception
			LOGG.error("[::::::: JsonParseException JSON  " +e.getMessage()  +":::::::::::]" );
		} catch (Exception e) {
			LOGG.error("[::::::: Exception  " +e.getMessage()  +":::::::::::]" );
		}
		return valida;
	}
	
	
	public static void main(String[] args) {
		
		String requestCompModule = "{\"movement\":\"LIST\",\"data\":[{\"cveModule\":5,\"moduleName\":\"\"}]}";
		System.out.println( validaRequestCompModule(requestCompModule));
	}
	
	
	

}
