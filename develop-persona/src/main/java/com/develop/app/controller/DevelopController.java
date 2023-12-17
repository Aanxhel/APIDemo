package com.develop.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.app.models.services.EmpleadoService;

@RestController
@SuppressWarnings({ "rawtypes" })
public class DevelopController {
	
	@Autowired
	EmpleadoService empleadoService;

	private static final Logger logger = LoggerFactory.getLogger(DevelopController.class);

	@PostMapping(value = "/holamundo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity helloWorldPost() {
		
		logger.info(":::hola mundo:::");
		
		ResponseEntity entity = null;
		entity = ResponseEntity.ok().body("{\"status\":\"ok\"}");
		return entity;
	}

	

	/*
	 * use database develop_dba; CREATE TABLE `tbl_empleado` ( `id` INT NOT NULL,
	 * `primerNombre` VARCHAR(20) NULL, `segundoNombre` VARCHAR(20) NULL,
	 * `apelliPat` VARCHAR(20) NULL, `apelliMat` VARCHAR(20) NULL, `edad` INT NULL,
	 * `sexo` VARCHAR(10) NULL, `fechaNacimiento` DATE NULL, `puesto` VARCHAR(60)
	 * NULL, PRIMARY KEY (`id`));
	 */
}
