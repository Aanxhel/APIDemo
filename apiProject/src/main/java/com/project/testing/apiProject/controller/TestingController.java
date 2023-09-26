package com.project.testing.apiProject.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestingController {

	private static final Logger logger =  LogManager.getLogger(TestingController.class);  
	
	@PostMapping(value = "/holaMundo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity helloWorld() {
		logger.info("helloWorld");
		ResponseEntity response = null;
		return response;
	}
}
