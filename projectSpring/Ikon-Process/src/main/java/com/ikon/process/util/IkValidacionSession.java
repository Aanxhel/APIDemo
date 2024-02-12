/**
 * 
 */
package com.ikon.process.util;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.json.request.IkRequestSessionJson;

/**
 * @author icb_ipsra
 * @since October, 2021
 *
 */
public class IkValidacionSession {

	private static final Logger LOGG = LoggerFactory.getLogger(IkValidacionSession.class);

	/**
	 * Valida si los parametros ingresados tienene eror
	 * 
	 * @param request --> Objeto transportador de datos.
	 * @return objetto de tipo DatosErroresJson
	 */
	public DatosErroresJson validaErroresSessiones(IkRequestSessionJson request) {
		DatosErroresJson datosErroresJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		LOGG.info("[:::::. {} .:::::]"," Validating Object IkRequestSentenceCard ");
		
		if (request.getCveSession() == null) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("cveSession")));
		} 
		if ( request.getSessionName() == null || request.getSessionName().equals("") ){
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("sessionName")));
		} else {
			if ( request.getSessionName().length() > Constantes.MAX_LENGTH_INT_150 ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("sessionName")));
			}
		}
		if ( request.getSessionDesc() != null && !request.getSessionDesc().equals("") ) {
			if ( request.getSessionDesc().length() > Constantes.MAX_LENGTH_INT_150 ) {
				 errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("sesionDesc")));
			}
		}
		
		if (request.getCveSession() == Constantes.VALOR_INT_CERO ) {
			if ( request.getSentences().isEmpty() || request.getSentences().size() == Constantes.VALOR_INT_CERO ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("Array sentences")));
			}
		}

		datosErroresJson.setErrores(errores);

		return datosErroresJson;
		
	}
	

}
