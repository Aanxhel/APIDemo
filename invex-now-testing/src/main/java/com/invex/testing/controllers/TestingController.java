package com.invex.testing.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@SuppressWarnings({ "rawtypes" })
public class TestingController {

	Logger logger = LogManager.getLogger(TestingController.class);



	@PostMapping(value = "/holamundo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity helloWorld() {
		ResponseEntity entity = null;

		logger.info(":::hola mundo:::");
		entity = ResponseEntity.ok().body("{\"status\":\"ok\"}");
		return entity;
	}

	@PostMapping(value = "/agregarPersona", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addPersona() {
		ResponseEntity entity = null;
		
		return entity;
	}
}
