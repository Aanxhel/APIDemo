/**
 * 
 */
package com.ikon.process.service;

import org.springframework.http.ResponseEntity;

/**
 * @author icb_ipsra
 *
 */
public interface IkTopicNeg {
	
	/***
	 * Obtiene toda la lista de registros para armar el combo
	 * @return
	 */
	ResponseEntity<Object> getallRecords ( );

}
