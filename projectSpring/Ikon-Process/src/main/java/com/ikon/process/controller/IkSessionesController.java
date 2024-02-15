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

import com.ikon.process.service.IkSessionNeg;

/**
 * Implementta la fincionalidad de una session
 * @author icb_ipsra
 * @version 0.2.1
 */

@RestController
@RequestMapping(value="/v1/")
public class IkSessionesController {

	private static final Logger LOGG = LoggerFactory.getLogger(IkSessionesController.class);
	
	@Autowired
	private IkSessionNeg ikSessionNeg;
	
	
	/***
	 * Permite insertar y/o Modificiar una sesión (Clase)
	 * @param cveTopic
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.POST})
	@RequestMapping(value = "session/",  method = RequestMethod.POST, consumes ="application/json", produces = "application/json")
	public ResponseEntity<Object> insertUpdateSentence( @RequestBody String requestJson ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {} .::::::::]", "Insert and / or modify a session ");

		entity = this.ikSessionNeg.movimientoSentence(requestJson);
		
		return entity;
	}
	
	
	/***
	 * Obtiene todas las sessiones asociadas y no asociadas
	 * @param cveTopic
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "session/getall/",  method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllSession ( ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {} .::::::::]", "Getting all the session ");
		entity = this.ikSessionNeg.getCatAllSentencesCards();
		
		return entity;
	}
	
	/***
	 * Permite eliminar una tarjeta
	 * @param cveTopic
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "session/deleteId", params = {"cveSession"},  method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getCarbyTopic( @RequestParam(value="cveSession", required = true) Integer cveSession ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {},{},{} .::::::::]", "Deleting Session"," ", cveSession);
		entity = this.ikSessionNeg.eliminaSession(cveSession);
		
		return entity;
	}
	
	/***
	 * Obtiene todas las sessiones no asignadas
	 * @param cveTopic
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "session/getById", params = {"cveSession"}, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllSessionById ( @RequestParam(value="cveSession", required = true) Integer cveSession ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {} .::::::::]", "Getting session by Id");
		entity = this.ikSessionNeg.getSessionesById(cveSession);
		
		return entity;
	}
	
	
	/***
	 * Obtiene todas las sessiones No asociadas
	 * @param cveTopic
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "session/getNotAssociated/",  method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllSessionNotAssociated ( ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {} .::::::::]", "Getting all the session NOT ASSOCIATED ");
		entity = this.ikSessionNeg.getSessionesNotAssociated();
		
		return entity;
	}
	
}
