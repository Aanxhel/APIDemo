package com.demo.elektra.registro.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.elektra.registro.app.model.entity.Usuario;
import com.demo.elektra.registro.app.model.service.RegistroService;

@RestController
public class RegistroController {

	@Autowired
	RegistroService registroService;

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/listausuario")
	public List<Usuario> index() {
		List<Usuario> listUsuario = new ArrayList<>();
		listUsuario = registroService.findAll();
		return listUsuario;
	}
	
	@PostMapping("/insertusuario")
	public void insertUsuario(@RequestBody Usuario request) {
		registroService.insertarUsuario(request);
	}
}
