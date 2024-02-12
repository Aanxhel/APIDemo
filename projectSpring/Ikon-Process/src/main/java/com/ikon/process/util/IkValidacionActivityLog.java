/**
 * 
 */
package com.ikon.process.util;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.json.request.IKRequestActivityLogJson;

/**
 * @author icb_ipsra
 *
 */
public class IkValidacionActivityLog {

	private static final Logger LOGG = LoggerFactory.getLogger(IkValidacionSenteceCard.class);
	
	public DatosErroresJson validaErroresSentenceCard(IKRequestActivityLogJson request) {
		
		DatosErroresJson datosErroresJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		LOGG.info("[:::::. {} .:::::]"," Validating Object IkRequestSentenceCard ");
		
		if ( request.getNomUsername() == null ||  request.getNomUsername().equals("") ) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("nomUsername")));
		} else {
			if ( request.getNomUsername().length() > Constantes.MAX_LENGTH_INT_100 ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("nomUsername")));
			}
		}
		if ( request.getTimStart() == null ||  request.getTimStart().equals("") ) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("timStart")));
		} else {
			if ( request.getTimStart().length() !=  Constantes.MAX_LENGTH_INT_8) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("timStart")));
			}
		}
		if ( request.getTimEnd() == null ||  request.getTimEnd().equals("") ) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("timEnd")));
		} else {
			if ( request.getTimEnd().length() !=  Constantes.MAX_LENGTH_INT_8) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("timEnd")));
			}
		}
		if ( request.getDesModule() == null ||  request.getDesModule().equals("") ) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("desModule")));
		} else {
			if ( request.getDesModule().length() !=  Constantes.MAX_LENGTH_INT_100) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("desModule")));
			}
		}
		
		if ( request.getDesSession() == null ||  request.getDesSession().equals("") ) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("desSession")));
		} else {
			if ( request.getDesSession().length() !=  Constantes.MAX_LENGTH_INT_100) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("desSession")));
			}
		}
		
		if ( request.getNumSentences() == null ||  request.getNumSentences().equals("") ) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("numSentences")));
		} else {
			if ( request.getNumSentences().length() !=  Constantes.MAX_LENGTH_INT_50) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("numSentences")));
			}
		}
		
		return datosErroresJson;
	}
	
	
	
}
