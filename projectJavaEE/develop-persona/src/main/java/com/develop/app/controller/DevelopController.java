package com.develop.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.app.models.entity.Persona;
import com.develop.app.models.services.PersonaService;

@RestController
@SuppressWarnings({ "rawtypes" })
public class DevelopController {
	
	@Autowired
	PersonaService personaService;
	
	private static final Logger logger = LoggerFactory.getLogger(DevelopController.class);

	@PostMapping(value = "/holamundo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity helloWorldPost() {
		
		logger.info(":::hola mundo:::");
		
		ResponseEntity entity = null;
		entity = ResponseEntity.ok().body("{\"status\":\"ok\"}");
		return entity;
	}
	
	@GetMapping("/listaempleado")
	public List<Persona> index(){
		
		List<Persona> listPersona = null;
		listPersona = personaService.findAll();
		
		return listPersona;
	}
	

}
