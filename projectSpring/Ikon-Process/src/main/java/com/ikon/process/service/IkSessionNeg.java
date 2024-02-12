/**
 * 
 */
package com.ikon.process.service;

import org.springframework.http.ResponseEntity;

/**
 * @author icb_ipsra
 */
public interface IkSessionNeg {

	/***
	 * Obtiene todos los registros de sessiones Asociadas y no Asociadas
	 * @return
	 */
	ResponseEntity<Object> getCatAllSentencesCards ( );
	
	/***
	 * Inserta/modifica una session-sentences
	 * @param requestSessionJson
	 * @return
	 */
	ResponseEntity<Object> movimientoSentence (String requestSessionJson );
	
	/***
	 * Elimina una session
	 * @param cveSession
	 * @return
	 */
	ResponseEntity<Object> eliminaSession (Integer cveSession);
	
	/**
	 * Obtiene una sessión con sus sentencias asignadas a una sentencias
	 * @param cveSession
	 * @return
	 */
	ResponseEntity<Object> getSessionesById(Integer cveSession);
	
	/**
	 * Obtiene una sessión con sus sentencias no asocidas
	 * @param cveSession
	 * @return
	 */
	ResponseEntity<Object> getSessionesNotAssociated( );
}


