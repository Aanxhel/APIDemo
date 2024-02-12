/**
 * 
 */
package com.ikon.process.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ikon.process.service.IkCardNeg;
import com.ikon.process.util.Constantes;

/**
 * 
 * Encargada de implementar la funcionalidad de una tarjeta
 * @author icb_ipsra
 * @since Octubre, 2021
 * @version 0.2.1
 *
 */
@RestController
@RequestMapping(value="/v1/")
public class IkCardController {
	
	private static final Logger LOGG = LoggerFactory.getLogger(IkCardController.class);
	
	@Autowired
	private IkCardNeg ikCardNeg;
	
	/***
	 * Permite insertar/modicar una tarjeta
	 * @param requestJson
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.POST})
	@RequestMapping(value = "card/process/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> insertUpdateCard ( @RequestBody String requestCardTopic ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. { }  .::::::::]", "Insert/Update card`s");
		
		entity = this.ikCardNeg.insertCardTopic(requestCardTopic);
		
		return entity;
	}
	
	/***
	 * Permite obtener todas las tarjetas pertenecientes a un tema
	 * @param cveTopic
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "card/sentence/bytopic", params = {"cveTopic"},  method = RequestMethod.GET, produces = "application/json")

	public ResponseEntity<Object> getCarbyTopic( @RequestParam(value="cveTopic", required = true) int cveTopic ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {},{},{} .::::::::]", "Getting all cards by Topic"," ", cveTopic);
		
		entity = this.ikCardNeg.obtineCardbyTopic(cveTopic);
		
		return entity;
	}
	
	
	/***
	 * Permite eliminar una tarjeta
	 * @param cveTopic
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "card/deleteId", params = {"cveCard"},  method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getCarbyTopic( @RequestParam(value="cveCard", required = true) Integer cveCard ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {},{},{} .::::::::]", "Deleting card"," ", cveCard);
		entity = this.ikCardNeg.eliminaCard(cveCard);
		
		return entity;
	}
	
	
	/***
	 * Getting card information by ID 
	 * @param cveTopic
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "card/bycard", params = {"cveCard"},  method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getCarbyId( @RequestParam(value="cveCard", required = true) Integer cveCard ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {},{} .::::::::]", "Getting card information by id_B ", cveCard);
		entity = this.ikCardNeg.obtineCardbyID(cveCard);
		
		return entity;
	}
	
	
	/**
	 * Getting all cards information
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "card/getall/",  method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllCards ( ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {} .::::::::]", "Getting all modules information B ");
		entity = this.ikCardNeg.obtineCardbyID(new Integer(Constantes.VALOR_INT_CERO));
		
		return entity;
	}
	
	
	/***
	 * Getting card information by ID {topicName:[a-zA-Z0-9,-.\\s]{1,100}} {topicName:[a-zA-Z0-9]{1,100}}
	 * @param cveTopic
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "card/getCardsByTopic/{topicName:[a-zA-Z0-9,-.\\s]{2,100}}", 
			method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getCarByTpicname( @PathVariable(value="topicName", required = true) String topicName ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. {},{} .::::::::]", "Getting card information by Topic Name:  ", topicName);
		entity = this.ikCardNeg.obtineCardbyTopicName(topicName);
		
		return entity;
	}
	
	

}
