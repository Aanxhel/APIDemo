/**
 * 
 */
package com.ikon.process.util;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.json.request.IkRequestSentenceCard;

/**
 * @author icb_ipsra
 * @since October, 2021
 *
 */
public class IkValidacionSenteceCard {

	private static final Logger LOGG = LoggerFactory.getLogger(IkValidacionSenteceCard.class);

	/**
	 * Valida si los parametros ingresados tienene eror
	 * 
	 * @param request --> Objeto transportador de datos.
	 * @return objetto de tipo DatosErroresJson
	 */
	public DatosErroresJson validaErroresSentenceCard(IkRequestSentenceCard request) {
		DatosErroresJson datosErroresJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();

		LOGG.info("[:::::. {} .:::::]"," Validating Object IkRequestSentenceCard ");
		
		if ( request.getCveSentence() == null ) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("cveSentence")));
		}
		
		if ( request.getSentenceName() == null || request.getSentenceName().equals("") ) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("sentenceName")));
		} else {
			if (request.getSentenceName().length() > Constantes.MAX_LENGTH_INT_100 ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("sentenceName")));
			}
		}
		if ( request.getPathSound() != null && !request.getPathSound().equals("") ) {
			if ( request.getPathSound().length() > Constantes.MAX_LENGTH_INT_1000 ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("pathSound")));
			}
		}
		if ( request.getPathVideo() != null && !request.getPathVideo().equals("") ) {
			if ( request.getPathVideo().length() > Constantes.MAX_LENGTH_INT_1000 ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("pathVideo")));
			}
		}
		
		if ( request.getPathInfo() != null && !request.getPathInfo().equals("") ) {
			if ( request.getPathInfo().length() > Constantes.MAX_LENGTH_INT_1000 ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("pathInfo")));
			}
		}
		
		if ( request.getCveSentence() == Constantes.VALOR_INT_CERO ) {
			if ( request.getCards().isEmpty() || request.getCards().size() == Constantes.VALOR_INT_CERO ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("Array cards")));
			}
		}	
		datosErroresJson.setErrores(errores);

		return datosErroresJson;
		
	}
	

}
