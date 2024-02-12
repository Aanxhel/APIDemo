/**
 * 
 */
package com.ikon.process.service;

import org.springframework.http.ResponseEntity;

/**
 * Gestiona la firma de cada uno de los
 * metodos de la clase implementadora ProcessFrameANegImpl
 * @author icb_ipsra
 * @since Septiembre 18, 2021
 *
 */
public interface ProcessFrameANeg {

	/**
	 * Obtiene los registros de acuerdo a la busqueda
	 * @param requestBusquedaFrame
	 * @return
	 */
	ResponseEntity<Object> frameBusqueda ( String requestBusquedaFrame);

	/***
	 * Inserta un registro
	 * @param requestFrameAJson
	 * @return
	 */
	ResponseEntity<Object> agregaDatosFrameA(String requestFrameAJson);
}
