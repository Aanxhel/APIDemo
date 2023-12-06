package com.cdc.testing.util;

import java.util.Arrays;
import java.util.List;

public class Constantes {
	
	public static final Long INTERNAL_SERVER_ERROR_EC = 500l;
	public static final Long BAD_REQUEST_INCORRECT_EC = 400l;
	public static final Long NOT_AUTHENTICATED_EC = 401l;
	public static final Long NOT_FOUND_EC = 404l;
	public static final Long ACCEPTED = 202l;
	public static final Long NO_CONTENT = 204l;

	public static final String NOT_FOUND_MSJ = "No se encontró registro con la información proporcionada.";
	public static final String BAD_REQUEST_INCOMPLETE_CAMPOS = "Datos requeridos incompletos";
	public static final String NOT_FOUND_ERROR = "No se encontraron datos";
	public static final String BAD_REQUEST_FORMAT_ERROR_SOLICITUD = "Ocurrio un error al procesar tu solicitud.";
	public static final String NOT_AUTHENTICATED = "No Autenticado";
	public static final String TOO_MANY_INFO = "No puede ser procesada la solicitud debido a que el correo esta asociado a más de 3 personas";
	
	public static final List<String> VOCALES = Arrays.asList("A","E","I","O","U","a","e","i","o","u");
	
	public static final List<String> PALABRAS_INCOVENIENTES = Arrays.asList("BUEI","BUEY","CACA","CACO","CAGA","CAGO","CAKA","CAKO","COGE","COJA",
			"COJE","COJI","COJO","CULO","FETO","GUEY","JOTO","KACA","KACO","KAGA","KAGO","KOGE","KOJO","KAKA","KULO","MAME","MAMO","MEAR","MEAS",
			"MEON","MION","MOCO","MULA","PEDA","PEDO","PENE","PUTA","PUTO","QULO","RATA","RUIN");
	
	public static final List<String> NOMBRES_COMUNES = Arrays.asList("MARIA","MARI","MA","MA.","JOSE","J","J.");
	
	public static final List<String> COMPUESTO = Arrays.asList("DA","DAS","DE","DEL","DER","DI","DIE","DD","EL","LA","LOS","LAS","LE","LES","MAC","MC",
			"VAN","VON","Y");
	
}
