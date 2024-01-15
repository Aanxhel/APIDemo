package com.develop.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings({ "rawtypes" })
public class DevelopController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(DevelopController.class);

	@PostMapping(value = "/holamundo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity helloWorldPost() {
		
		logger.info(":::hola mundo:::");
		
		ResponseEntity entity = null;
		entity = ResponseEntity.ok().body("{\"status\":\"ok\"}");
		return entity;
	}
	

	

}
