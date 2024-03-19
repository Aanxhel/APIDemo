package com.demo.elektra.registro.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.elektra.registro.app.entity.Usuario;
import com.demo.elektra.registro.app.service.RegistroService;

@RestController
@RequestMapping("/usuarios")
@SuppressWarnings("unchecked")
public class RegistroController {

	@Autowired
	RegistroService registroService;

//	@Autowired
//	RegistroDto registroDto;

	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("hola mundo registro!");
	}

	@GetMapping("/lista")
	public List<Usuario> index() {
		List<Usuario> listUsuario = new ArrayList<>();
		listUsuario = registroService.findAll();
		return listUsuario;
	}


	@PostMapping("/registro")
	public ResponseEntity<String> insertUsuario(@Valid @RequestBody Usuario request, Errors error) {

		Usuario requestInsert = request;

		registroService.insertarUsuario(request);

	
		if (error.hasErrors()) {
			return (ResponseEntity<String>) ResponseEntity.badRequest();

		}
		return ResponseEntity.ok("{ \"status\": \" ok\", \"id\": " + requestInsert.getId() + "}");


	}

}
