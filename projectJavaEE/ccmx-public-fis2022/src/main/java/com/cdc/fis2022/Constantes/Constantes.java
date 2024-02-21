package com.cdc.fis2022.constantes;

public enum Constantes {
	
	
	// DB PROEPRTIES
	SCORE_FIS_SCHEMA("CDC"),
	SCORE_FIS_PKG("PEPG_MODELO_FIS2"),
	SCORE_FIS_FNCN("CCFN_FIS2_CAL_VARIABLES"),
	SCORE_FIS_SP("CDC_SP_GETDATA"),
	
	// DB_MESSAGES
	SCORE_PARAM_DAO_1("VE_NCONENCFOLIO"),
	SCORE_PARAM_DAO_2("VE_NPERSCVE"),
	SCORE_PARAM_DAO_3("VS_VARIABLES_CALCULADAS"),
	SCORE_BAD_RESPONSE_CHAIN("||||||||||||||||||||||||||||||||||||"),
	
	// variable a comparar 
	SCORE_VALUE_COMPARE("folioOtorgante"),
	
	SCORE_PERSON_NOT_FOUND("La persona No fue encontrada"),
	
	SCORE_NON_VALUE("0"),
	
	SCORE_WORD_NO("NO"),
	
	SCORE_MATCH_OK("Match OK"),
	
	SCORE_NOT_GET_CHAIN_VALUES("No se logro obtener la cadena de valores .. :("),
	SCORE_NOT_GET_CHAIN_VALUES_DAO("NO SE PUDO OBTENER LA CADENA A ENVIAR A SCORE EXTERNAL "),
	SCORE_NOT_CADENA("NOTCADENA"),
	
	
	SCORE_BAD_REQUEST("Error: Bad Request"),
	SCORE_ERROR_CALL_SERVER("Error al llamar al servidor (Bad request)"),
	SCORE_FOLIO_NO_EXISTS("FolioConsulta NO existe"),
	
	// SCORE EXTERNAL AC
	SCORE_EXT_HD_NAME("X-DEVOPS-APPNAME"),
	SCORE_EXT_HD_AN("X-DEVOPS-AUTH");

	
	
	
	// VARIABLE PAR APODER OBTENER EL VALOR DEL ENUM;
	public final String value;
	
	


	// CONSTRUCTOR DE LA CLASE PARA QUE SE PUEDAN LLAAMR LOS VALORES DEL ENUM
	private Constantes(String value) {
		this.value = value;
		
	}
	
	


	
	
	
	
} // CIERRE DE LA CLASE CONSTANTES
