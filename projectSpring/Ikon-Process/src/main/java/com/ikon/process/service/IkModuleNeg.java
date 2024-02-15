/**
 * 
 */
package com.ikon.process.service;

import org.springframework.http.ResponseEntity;

/**
 * Mantiene la firma de cada uno de los metodos
 * de la clase implementadora CompModuleNegImpl
 * @author icb_ipsra
 *
 */
public interface IkModuleNeg {

	/***
	 * Getting all modules information 
	 * @return
	 */
	ResponseEntity<Object> obtieneModuleSessiones ();
	
	/***
	 * Getting module information by Id
	 * @param cveModule
	 * @return
	 */
	ResponseEntity<Object> obtieneModuleById(Integer cveModule);
	/**
	 * Alta /o modificar un modulo
	 * @param requestModuleJson
	 * @return
	 */
	ResponseEntity<Object> movimientoModule(String requestModuleJson );
	/**
	 * Obtien todas las sentencias y tarjetas
	 * @param cveModule
	 * @param cveSession
	 * @return
	 */
	ResponseEntity<Object> obtieneSentenCards ( Integer cveModule, Integer cveSession);
	
	/***
	 * Elimina todo el modulo
	 * @param cveModule
	 * @return
	 */
	ResponseEntity<Object> eliminaModule (Integer cveModule);
}
