/**
 * 
 */
package com.ikon.process.service;

import org.springframework.http.ResponseEntity;

/**
 * @author icb_ipsra
 *
 */
public interface IkActivityLogNeg {

	/***
	 * Registra la actividad
	 * @param requestActivityMonitoryJson
	 * @return
	 */
	ResponseEntity<Object> insertActivityLog (String requestActivityMonitoryJson );
	
	/***
	 * Obtiene una lista de regisros de acuerdo al totoal de registros
	 * @param totalRecords
	 * @return
	 */
	ResponseEntity<Object> getAllActivityLog (Integer totalRecords );
}
