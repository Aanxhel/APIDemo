/**
 * 
 */
package com.ikon.process.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ikon.process.service.IkTopicNeg;

/**
 * Encargada de implementar la funcionalidad de un topic ( Tema)
 * @author icb_ipsra
 * @since Octuber, 2021
 * @version 0.2.1
 *
 */
@RestController
@RequestMapping(value="/v1/")
public class IkTopicController {
	
	private static final Logger LOGG = LoggerFactory.getLogger(IkTopicController.class);
	
	@Autowired
	private IkTopicNeg ikTopicNeg;
	/***
	 * Permite obtener todos los topic`s
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600 , methods = {RequestMethod.GET} )
	@RequestMapping(value = "topic/getall/",  method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllTopic( ) {
		ResponseEntity<Object>  entity = null;
		LOGG.info("[::::::::::. { }  .::::::::]", "Getting all topic's Isra");
		
		entity =this.ikTopicNeg.getallRecords();
		
		return entity;
	}
	
}
