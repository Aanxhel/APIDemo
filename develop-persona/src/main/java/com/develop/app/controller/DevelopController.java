package com.develop.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.app.models.services.IEmpleadoService;



@RestController
@SuppressWarnings({ "rawtypes" })
public class DevelopController {

	@Autowired
	private IEmpleadoService empleadoService;
	
	Logger logger = LogManager.getLogger(DevelopController.class);

	@PostMapping(value = "/holamundo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity helloWorld() {
		
		logger.info(":::hola mundo:::");
		
		ResponseEntity entity = null;
		entity = ResponseEntity.ok().body("{\"status\":\"ok\"}");
		return entity;
	}

//	@GetMapping("/monstrarEmpleado")
//	public List<Empleado> getEmpleado(){
//		logger.info("get empleado");
//		
//		List<Empleado> empleadoList = null;
//		
//		try {
//			empleadoList = empleadoService.findAll();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
//		return empleadoList;
//	}


	// peticion get
//	@GetMapping("/obtenerEmpleado")
//	public List<Empleado> listaEmpleado() {
//		logger.info("obtenerEmpleado");
//		return service.listarEmpleados();
//	}

//	public void guardarEmpleado(Empleado empleado) {
//		service.guardarEmpleado(empleado);
//	}
//	
//	public Producto obtenerProductoId(Integer id) {
//		return service.obtenerEmpleadoId(id);
//	}

	/*
	 * use database develop_db;
	 * CREATE TABLE `tbl_empleado` ( `id` INT NOT NULL,
	 * `primerNombre` VARCHAR(20) NULL, `segundoNombre` VARCHAR(20) NULL,
	 * `apelliPat` VARCHAR(20) NULL, `apelliMat` VARCHAR(20) NULL, `edad` INT NULL,
	 * `sexo` VARCHAR(10) NULL, `fechaNacimiento` DATE NULL, `puesto` VARCHAR(60)
	 * NULL, PRIMARY KEY (`id`));
	 */
}
