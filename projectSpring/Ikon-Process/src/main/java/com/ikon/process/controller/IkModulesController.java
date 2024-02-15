/**
 * 
 */
package com.ikon.process.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ikon.process.service.IkModuleNeg;

/**
 * Implementta la fincionalidad de una session
 * @author icb_ipsra
 * @version 0.2.1
 */

@RestController
@RequestMapping(value="/v1/")
public class IkModulesController {

	private static final Logger LOGG = LoggerFactory.getLogger(IkModulesController.class);
	
	@Autowired
	private IkModuleNeg ikModuleNeg;
	
	
	
	
	/**
	 * Obtiene todos los modulos y sessiones asignadas
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "module/getall/",  method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllModules ( ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {} .::::::::]", "Getting all modules information ");
		
		entity = this.ikModuleNeg.obtieneModuleSessiones();
		
		return entity;
	}
	
	/**
	 * Obtiene todos los modulo by id
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "module/session/bymodule",  params = {"cveModule"}, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllModulesById (@RequestParam(value ="cveModule", required = true ) Integer cveModule) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {} .::::::::]", "Getting module information by id");
		
		entity = this.ikModuleNeg.obtieneModuleById(cveModule);
		
		return entity;
	}
	
	
	/** Permite insertar y/o Modificiar un modulo)
	 * @param cveTopic
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.POST})
	@RequestMapping(value = "module/",  method = RequestMethod.POST, consumes ="application/json", produces = "application/json")
	public ResponseEntity<Object> insertUpdateMOdule( @RequestBody String requestJson ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {} .::::::::]", "Insert and / or modify a module ");
		
		entity = this.ikModuleNeg.movimientoModule(requestJson);
		
		return entity;
	}
	
	
	
	/**
	 * Permiete obtener todas las información de un modulo y session.
	 * A través de esta información se generará la clase hacía el alumno 
	 * @param cveModule --> Clave del modulo 
	 * @param cveSession ---> Clave de la sessión
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "module/session",  params = {"cveModule","cveSession"}, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllModuleSession (@RequestParam(value ="cveModule", required = true ) Integer cveModule, @RequestParam( value = "cveSession", required = true ) Integer cveSession) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {}{}{}{}{} .::::::::]", "Getting response all data Module Session ", " cveModule::> ", cveModule, " cveSession::> " , cveSession );
		entity = this.ikModuleNeg.obtieneSentenCards(cveModule, cveSession);
		
		return entity;
	}
	
	
	/***
	 * Permite eliminar un module
	 * @param cveTopic
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "module/deleteId", params = {"cveModule"},  method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getCarbyTopic( @RequestParam(value="cveModule", required = true) Integer cveModule ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {},{},{} .::::::::]", "Deleting Module"," ", cveModule);
		entity = this.ikModuleNeg.eliminaModule(cveModule);
		
		return entity;
	}
	
}
