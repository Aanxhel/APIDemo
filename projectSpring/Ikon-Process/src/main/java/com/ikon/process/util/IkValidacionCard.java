/**
 * 
 */
package com.ikon.process.util;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.json.request.IkRequestCard;

/**
 * @author icb_ipsra
 * @since October, 2021
 *
 */
public class IkValidacionCard {

	private static final Logger LOGG = LoggerFactory.getLogger(IkValidacionCard.class);

	/**
	 * Valida si los parametros ingresados tienene eror
	 * 
	 * @param request --> Objeto transportador de datos.
	 * @return objetto de tipo DatosErroresJson
	 */
	public DatosErroresJson validaErroresCatalago(IkRequestCard request) {
		DatosErroresJson datosErroresJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();

		LOGG.info("[:::::. {} .:::::]"," Validating Object IkRequestCard ");

		if ( request.getCveTopic() != null && request.getCveTopic() == Constantes.VALOR_INT_CERO)  {
			if (request.getTopicName() == null || request.getTopicName().equals("")) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("topicName")));
			} else {
			
				if ( request.getTopicName().length() > Constantes.MAX_LENGTH_INT_150 ) {
					errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("topicName")));
				}
			}
		}
		
		if  ( request.getCveCard() == null) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("cveCard")));
		}
		
		if ( request.getCardText() == null || request.getCardText().equals("") ) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("cardText")));
		} else {
			if ( request.getCardText().length() > Constantes.MAX_LENGTH_INT_150 ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("cardText")));
			}
		}
		
		if ( request.getPathImage() == null || request.getPathImage().equals("")) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("pathImage")));
		} else {
			if ( request.getPathImage().length() > Constantes.MAX_LENGTH_INT_1000 ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("pathImage")));
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
		if ( request.getPathMeaning() != null && !request.getPathMeaning().equals("") ) {
			if ( request.getPathMeaning().length() > Constantes.MAX_LENGTH_INT_1000 ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("pathMeaning")));
			}
		}
		datosErroresJson.setErrores(errores);

		return datosErroresJson;
		
	}
	

}
