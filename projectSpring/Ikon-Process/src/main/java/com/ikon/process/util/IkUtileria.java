/**
 * 
 */
package com.ikon.process.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.json.response.IkResponseGeneric;


/**
 * @author IsraelC
 * @Since Julio 24, 2021
 *
 */
public class IkUtileria {

	private static final Logger LOGG = LoggerFactory.getLogger(IkUtileria.class);
	
	/***
	 * Metodo que genera estructura de errores	
	 * @param codigo
	 * @param mensaje
	 * @param paramentro
	 * @return
	 */
	public static DatosErroresJson generaSegmentoError ( String codigo, String mensaje, String paramentro ) {
		
		LOGG.info("[::::::::::  { }  :::::::::::]","Generated structure Error  ");
		
		DatosErroresJson datosErrores = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		
		errores.add(new ErroresJson(codigo, mensaje.concat(" "+paramentro )));
		
		datosErrores.setErrores(errores);
		
		return datosErrores;
	}
	
	/***
	 * Permite generar una respuesta
	 * @param code
	 * @param message
	 * @param paramentro
	 * @return
	 */
	public static  IkResponseGeneric generaResponse ( int code, String message, String paramentro ) {
		LOGG.info("[::::::::::  { }  :::::::::::]","Generated structure Error  ");
		IkResponseGeneric response  = new IkResponseGeneric();
		response.setCode(code);
		response.setMessage(message.concat(paramentro));
		return response;
	}
	
	
	/**
	 * Valida que el correo tenga un formato correcto
	 * @param email
	 * @return
	 */
	public static final boolean validaExpresion (String expresion, String valor) {
		Pattern pattern = Pattern.compile(expresion);
		Matcher matcher = pattern.matcher(valor);
		return matcher.matches();
	}
	
	/**
	 * Valida el formato de la fecha
	 * @param fecha
	 * @return
	 */
	public static boolean validaFormatoFecha (String fecha ) {
		boolean fechaCorrecta = true;
		
		String[] fechaArray = new String[fecha.split("/").length];
		fechaArray = fecha.split("/");
		
		if ( fechaArray[0] != null && !fechaArray[0].equals("") ) {
			fechaArray[0] = fechaArray[0].length() == 1 ?"0".concat(fechaArray[0]):fechaArray[0];
		}
		if ( fechaArray[1] != null && !fechaArray[1].equals("") ) {
			fechaArray[1] = fechaArray[0].length() == 1 ?"0".concat(fechaArray[1]):fechaArray[1];
		}
		
		if ( fechaArray[0].length() != 2 ) {
			fechaCorrecta = false;
		} 
		if ( fechaArray[1].length() != 2 ) {
			fechaCorrecta = false;
		}
		if ( fechaArray[2].length() != 4 ) {
			fechaCorrecta = false;
		}
		
		try {
			
			if (fechaCorrecta ) {
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			
				formato.parse(fecha);
				fechaCorrecta = true;
			}
		
		} catch (ParseException e) {
			fechaCorrecta = false;
		}
		
		return fechaCorrecta;
	}
	
	/**
	 * Permite convertir un String en fecha de tipo (Date).
	 * 
	 * @param fecha Cadena de fecha dd/MM/yyyy
	 * @return Objeto Date
	 */
	public static Date ParseFecha(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate = new Date();
		
		try {
			fechaDate = formato.parse(fecha);
		} catch (ParseException ex) {
			LOGG.error("[::::::::::: {} :::::::::::]", ex);
		}
		
		return fechaDate;
	}
    
	
	public static void main(String[] args) {
      // System.out.println(validaExpresion("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", "aaaaaa.es"));
		
		System.out.println(ParseFecha("12/01/1978"));
		
	}
}
