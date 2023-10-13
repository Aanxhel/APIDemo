package com.project.testing.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.testing.service.TestingService;
import com.project.testing.service.imp.ValidacionesServiceImpl;

@RestController
@SuppressWarnings({ "rawtypes" })
public class TestingController {

	Logger logger = LogManager.getLogger(TestingController.class);

	@Autowired
	private TestingService testingService;

	@Autowired
	private ValidacionesServiceImpl validacionesService;

	@PostMapping(value = "/holamundo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity helloWorld() {
		ResponseEntity entity = null;

		logger.info(":::hola mundo:::");
		entity = ResponseEntity.ok().body("{\"status\":\"ok\"}");
		return entity;
	}

	@PostMapping(value = "/holamundo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getPass() {
		ResponseEntity entity = null;
		return entity;
	}
	
}
