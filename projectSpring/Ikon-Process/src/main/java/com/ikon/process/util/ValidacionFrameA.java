/**
 * 
 */
package com.ikon.process.util;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.json.request.RequestDatosFameJson;
import com.ikon.process.json.request.RequestFrameAJson;

/**
 * Clase encargada de realizar todoas la validaciones relacionadas
 * al frameA
 * 
 * @author icb_ipsra
 *
 */
public class ValidacionFrameA {

	private static final Logger LOGG = LoggerFactory.getLogger(ValidacionFrameA.class);
	
	/***
	 * Valida la estructura sea correcta de la peticion
	 * @param request
	 * @return
	 */
	public DatosErroresJson validaErroresBusquedaFrame(RequestDatosFameJson request) {
		DatosErroresJson datosErroresJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		
		LOGG.info("[:::::::::::: { Validando contenido de JSON  }:::::::::::]");
		
		if ( request.getCveModulo() == Constantes.VALOR_INT_CERO || request.getCveTopic() == Constantes.VALOR_INT_CERO) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_6.concat("cveModulo, cveTopic")));
		}
		datosErroresJson.setErrores(errores);
		
		return datosErroresJson;
		
	}
	
	/***
	 * Valida la estructura sea correcta de la peticion
	 * @param request
	 * @return
	 */
	public DatosErroresJson validaErroresFrameA(RequestFrameAJson request) {
		DatosErroresJson datosErroresJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		
		LOGG.info("[:::::::::::: { Validando contenido de JSON validaErroresFrameA }:::::::::::]");
		
		if ( request.getCveModule() == Constantes.VALOR_INT_CERO || request.getCveTopic() == Constantes.VALOR_INT_CERO) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_6.concat("cveModule, cveTopic")));
		}
		
		if ( request.getDesTxtSent() == null || request.getDesTxtSent().equals("") ){
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1.concat("cveModule, cveTopic")));
		} else {
			
			if (request.getDesTxtSent().length() >= Constantes.MAX_LENGTH_INT_200 ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("desTxtSent")));
			}
		}
		if (request.getPathDesImgSent() == null || request.getPathDesImgSent().equals("")) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1.concat("pathDesImgSent")));
		} else {
			
			if ( request.getPathDesImgSent().length() >= Constantes.MAX_LENGTH_INT_2000 ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("pathDesImgSent")));
			}
		}
		if ( request.getPathDSoundSent() != null && !request.getPathDSoundSent().equals("")) {
			if ( request.getPathDSoundSent().length() >= Constantes.MAX_LENGTH_INT_2000 ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("pathDSoundSent")));
			}
		}
		if ( request.getPathVideoSent() != null && !request.getPathVideoSent().equals("")) {
			if ( request.getPathVideoSent().length() >= Constantes.MAX_LENGTH_INT_2000 ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("pathVideoSent")));
			}
		}
		if ( request.getPathMeanSent() != null && !request.getPathMeanSent().equals("")) {
			if ( request.getPathMeanSent().length() >= Constantes.MAX_LENGTH_INT_2000 ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("pathMeanSent")));
			}
		}
		if ( request.getPathDesInfoSent() != null && !request.getPathDesInfoSent().equals("")) {
			if ( request.getPathDesInfoSent().length() >= Constantes.MAX_LENGTH_INT_2000 ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("pathDesInfoSent")));
			}
		}
		
		datosErroresJson.setErrores(errores);
		
		return datosErroresJson;
		
	}
	
	
	
}
