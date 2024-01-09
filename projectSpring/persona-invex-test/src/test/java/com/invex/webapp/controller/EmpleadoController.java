package com.invex.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invex.webapp.models.entity.Empleado;
import com.invex.webapp.models.service.EmpleadoService;

@RestController
public class EmpleadoController {

	@Autowired
	EmpleadoService empleadoService;
	
	@GetMapping("/listaempleado")
	public List<Empleado> index(){
		
		List<Empleado> listaEmpleado = null;
		listaEmpleado = empleadoService.listarEmpleado();
		
		return listaEmpleado;
	}
	
}
