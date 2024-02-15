/**
 * 
 */
package com.ikon.process.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ikon.process.service.TestInjectionService;


/**
 * @author icb_ipsra
 *
 */
@RestController
@RequestMapping (value = "/v1")
public class TestController {
	
	private static final Logger LOGG = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private TestInjectionService testInjectionService;
	
	@RequestMapping(value = "/saludo", method = RequestMethod.GET)
	public String hello () {
		LOGG.info("[::::::::: {}  :::::::::]", "Iniciando test");
		String valorBD = null;
		valorBD = testInjectionService.testInjection("castleipsra@gmail.com");
		return "Inforacion sensible ".concat(valorBD);
	}
	
	/*
	 * Método encargado de consumir el servicio de Encommer para
	 * realizar el cargo correspondiente.
	 */
	@RequestMapping(value = "/valida", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> registraCargoVPOS  (@RequestHeader(value = "Authorization", required = true) String authorization, @RequestBody String requestJson) {
		ResponseEntity<Object>  entity = null;
	
		
		
		return entity;
	}

}
