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

import com.ikon.process.service.IkSentenceNeg;

/**
 * Implementa la funcionalidad de  una sentencia
 * @author icb_ipsra
 * @version 0.2.1	
 */
@RestController
@RequestMapping(value="/v1/")
public class IkSentenceController {
	
	private static final Logger LOGG = LoggerFactory.getLogger(IkSentenceController.class);
	
	@Autowired
	private IkSentenceNeg ikSentenceNeg;
	
	
	/***
	 * Permite obtener todas las sentencias no asociadas a una session
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "sentence/notassociated/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getSentencesNotAssociated ( ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {} .::::::::]", "Getting all the sentences not associated to the session ");
		entity = this.ikSentenceNeg.notassociatedSession();
		
		return entity;
	}
	
	
	/***
	 * Permite obtener todas las sentencias
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "sentence/getall/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllsentences ( ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {} .::::::::]", "Get all the sentences associated or not to a session ");
		
		entity = this.ikSentenceNeg.getCatAllSentencesCards();
		
		return entity;
	}
	
	/***
	 * Permite insertar y/o Modificiar una sentencia (Solo los recursos)
	 * @param cveTopic
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.POST})
	@RequestMapping(value = "sentences/",  method = RequestMethod.POST, consumes ="application/json", produces = "application/json")
	public ResponseEntity<Object> insertUpdateSentence( @RequestBody String requestJson ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {} .::::::::]", "Insert and / or modify a sentence ");
		
		entity = this.ikSentenceNeg.asignaCardSentences(requestJson);
		
		return entity;
	}
	
	/***
	 * Permite eliminar una Sentence
	 * @param cveSentence
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "sentence/deleteId", params = {"cveSentence"},  method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getCarbyTopic( @RequestParam(value="cveSentence", required = true) Integer cveSentence ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {},{},{} .::::::::]", "Deleting Sentence"," ", cveSentence);
		entity = this.ikSentenceNeg.eliminaSentencia(cveSentence);
		
		return entity;
	}
	
	/***
	 * Getting card information by ID 
	 * @param cveTopic
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "sentence/getSentence", params = {"cveSentence"},  method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getSentenceCarbyId( @RequestParam(value="cveSentence", required = true) Integer cveSentence ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {},{} .::::::::]", "Getting Sentence card information by id ", cveSentence);
		entity = this.ikSentenceNeg.getSentencesById(cveSentence);
		
		return entity;
	}
	
}
