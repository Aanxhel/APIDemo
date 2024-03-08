package com.applab.demo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.applab.demo.app.model.entity.UsuarioRequest;
import com.applab.demo.app.model.service.LabService;


@RestController
public class LabController {
	
	@Autowired
	LabService appService;

	@GetMapping("/hello")
	public String hello() {
		return String.format("adios goku!");
	}
	
	
	@GetMapping("/lista")

	public List<UsuarioRequest> listaUsuarios(){
		
		List<UsuarioRequest> listUsuario = null;
		listUsuario = appService.listaUsuarios();
		
		return listUsuario;
	}

}
