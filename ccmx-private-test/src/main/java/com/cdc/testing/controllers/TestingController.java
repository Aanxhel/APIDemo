package com.cdc.testing.controllers;

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

import com.cdc.Support.PrinterCore.getters.GetFormat;
import com.cdc.testing.beans.Request;
import com.cdc.testing.beans.Response;
import com.cdc.testing.services.TestingService;
import com.cdc.testing.services.imp.ValidacionesServiceImpl;

@RestController
@SuppressWarnings({"rawtypes"})
public class TestingController {
	
	private static final Logger logger = LogManager.getLogger(TestingController.class);
	
	@Autowired
	private TestingService testingService;
	
	@Autowired
	private ValidacionesServiceImpl validacionesService;
	
    @PostMapping(value = "/helloWorld", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity helloWorld() {
		ResponseEntity entity = null;
		
		entity = ResponseEntity.ok().body("Hola Mundo");
		
		return entity;
	}
    
	@PostMapping(value = "/obtenerRFC", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity obtenerRFC(@RequestHeader HttpHeaders headers, @Validated @RequestBody Request request, Errors errors) 
    		throws Exception{
		logger.info(GetFormat.controllerWithBody("TestingController",null));
		List<String> mensajesError;

		Response response = new Response();
		
		mensajesError = validacionesService.validarRequest(request);
		
		if(mensajesError.isEmpty()) {
			response = testingService.obtenerRFC(request);
		}else {
			response = validacionesService.responseRFC(mensajesError);
		}	
   
	return validacionesService.validarResponse(response);
	}
	
}
	
