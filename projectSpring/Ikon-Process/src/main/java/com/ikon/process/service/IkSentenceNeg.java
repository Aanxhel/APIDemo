/**
 * 
 */
package com.ikon.process.service;

import org.springframework.http.ResponseEntity;

/**
 * Implementa la firma de cada uno de los metodos de la clase IkSentenceNegImpl
 * @author icb_ipsra
 * @since October, 2021
 *
 */
public interface IkSentenceNeg {
	
	/***
	 * Asigna tarjetas a una sentencia
	 * @param requestSentenceCard
	 * @return
	 */
	ResponseEntity<Object> asignaCardSentences (String requestSentenceCard );
	
	/***
	 * Obtiene todos las sentences y sus tarjetas asociadas y no asociadas a una sessión 
	 * @return
	 */
	ResponseEntity<Object> getCatAllSentencesCards ( );
	/**
	 * Elimina una sentencia
	 * @param cveSentence
	 * @return
	 */
	ResponseEntity<Object> eliminaSentencia (Integer cveSentence);
	
	/***
	 * Obtiene los datos de una sentencia por ID
	 * @param cveSentence
	 * @return
	 */
	ResponseEntity<Object> getSentencesById (Integer cveSentence);
	
	/***
	 * Obtiene todos las sentences y sus tarjetas NO asociadas a una sessión 
	 * @return
	 */
	ResponseEntity<Object> notassociatedSession ( );

}
