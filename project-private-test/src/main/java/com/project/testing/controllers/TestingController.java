package com.project.testing.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.project.testing.beans.RequestFormulaGeneral;
import com.project.testing.beans.ResponseEuler;
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

	@PostMapping(value = "/euler", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getEuler(@RequestHeader HttpHeaders headers,
			@Validated @RequestBody RequestFormulaGeneral request, Errors errors) throws Exception {
		List<String> mensajesError;

		mensajesError = validacionesService.validarRequest(request);

		ResponseEuler response = new ResponseEuler();

		if (mensajesError.isEmpty()) {
			response = testingService.getEuler(request);
		} else {
			response = validacionesService.responseEuler(mensajesError);
		}
		return validacionesService.validarResponse(response);
	}
	
	@PostMapping(value = "/getDommy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getDommy(@RequestHeader HttpHeaders headers,
			@Validated @RequestBody RequestFormulaGeneral request, Errors errors) throws Exception {
		
		List<String> mensajesError;

		mensajesError = validacionesService.validarRequest(request);
		
		 
		return null;
	}

}
