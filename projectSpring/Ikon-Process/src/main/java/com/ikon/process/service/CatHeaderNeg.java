/**
 * 
 */
package com.ikon.process.service;

import org.springframework.http.ResponseEntity;

/**
 * Gestiona la firma de cada uno de los
 * metodos de la clase implementadora CatalogosNegImpl
 * @author icb_ipsra
 * @since Julio 31, 2021
 *
 */
public interface CatHeaderNeg {

	/***
	 * Obtiene los registros de la tabla, para generar un listado de Header's
	 * @param requestCatalogoJson
	 * @return
	 */
	ResponseEntity<Object> catHeader (String requestCatalogoJson);
}
