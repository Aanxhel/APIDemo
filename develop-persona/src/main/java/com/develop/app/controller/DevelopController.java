package com.develop.app.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.develop.app.models.entity.Empleado;
import com.develop.app.models.services.IEmpleadoService;

@RestController
@SuppressWarnings({ "rawtypes" })
public class DevelopController {

	@Autowired
	IEmpleadoService empleadoService;

//	Logger logger = LogManager.getLogger(DevelopController.class);

	@GetMapping(value = "/holamundo")
	public ResponseEntity helloWorld() {

//		logger.info(":::hola mundo:::");

		ResponseEntity entity = null;
		entity = ResponseEntity.ok().body("{\"status\":\"ok\"}");
		return entity;
	}
	
	@GetMapping(value = "/getuser")
	public ArrayList<Empleado> obteEmpleados(){
		return empleadoService.obtenerUsuario();
	}
	
	@PostMapping(value = "/saveuser")
	public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
		return this.guardarEmpleado(empleado);
		
	}


//	@PostMapping(value = "/holamundo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity helloWorld() {
//		
//		logger.info(":::hola mundo:::");
//		
//		ResponseEntity entity = null;
//		entity = ResponseEntity.ok().body("{\"status\":\"ok\"}");
//		return entity;
//	}

	/*
	 * use database develop_dba; CREATE TABLE `tbl_empleado` ( `id` INT NOT NULL,
	 * `primerNombre` VARCHAR(20) NULL, `segundoNombre` VARCHAR(20) NULL,
	 * `apelliPat` VARCHAR(20) NULL, `apelliMat` VARCHAR(20) NULL, `edad` INT NULL,
	 * `sexo` VARCHAR(10) NULL, `fechaNacimiento` DATE NULL, `puesto` VARCHAR(60)
	 * NULL, PRIMARY KEY (`id`));
	 */
}
