/**
 * 
 */
package com.ikon.process.service;

import org.springframework.http.ResponseEntity;

/**
 * @author icb_ipsra
 *
 */
public interface IkCardNeg {
	
	/**
	 * Metodo encargado de insertar un topic cuando es 0 y topicname != null
	 * Si existe el topicName  y cveCard este realizará una modificación 
	 * @param requestCardTopic
	 * @return
	 */
	ResponseEntity<Object> insertCardTopic(String requestCardTopic );
	
	/**
	 * regresa una lista de tarjetas de acuerdo al topic 
	 */
	ResponseEntity<Object> obtineCardbyTopic( Integer cveTopic);
	
	/**
	 * Elimina una tarjeta, siempre y cuando no este asociada a una sentencia.
	 * @param cveCard
	 * @return
	 */
	ResponseEntity<Object> eliminaCard ( Integer cveCard );
	
	/***
	 * Getting card information
	 * cveCard == 0 Here getting all card information
	 * cveCard > 0 getting card information by ID
	 * @param cveCard
	 * @return
	 */
	ResponseEntity<Object> obtineCardbyID (Integer cveCard);
	
	/**
	 * Get all cards according  by topicName field
	 * @param topicName
	 * @return
	 */
	ResponseEntity<Object> obtineCardbyTopicName (String  topicName );

}
