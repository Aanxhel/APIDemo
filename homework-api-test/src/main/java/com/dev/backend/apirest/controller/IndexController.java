package com.dev.backend.apirest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.backend.apirest.model.Usuario;

//el controlador se encarga de administras las peticiones del usuario
@Controller
@RequestMapping("/app")
public class IndexController {

	// indica es de tipo GET
	@GetMapping({ "/home", "/index" })
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("titulo", "Hola spring desde index!");
		return "index";
	}

	@GetMapping("/perfil")
	public String perfil(ModelMap modelMap) {
		Usuario usuario = new Usuario();

		usuario.setNombre("Juan");
		usuario.setApellido("Pérez");
		usuario.setEmail("corre@mail.com");

		modelMap.addAttribute("titulo", "Nombre: ".concat(usuario.getNombre()));
		modelMap.addAttribute("usuario", usuario);

		return "perfil";
	}

	@GetMapping("/listar")
	public String listar(ModelMap modelMap) {

		List<Usuario> usuarios = new ArrayList<>();

		usuarios.add(new Usuario("Angel", "Montiel", "correo@mail.com"));
		usuarios.add(new Usuario("Juan", "Perez", "correo2@mail.com"));
		usuarios.add(new Usuario("Pedro", "Gonzalez", "correo3@mail.com"));
		usuarios.add(new Usuario("Tornado", "Roe", "correo4@mail.com"));

		modelMap.addAttribute("titulo", "Lista de usuarios");
		modelMap.addAttribute("usuarios", usuarios);

		return "listar";
	}

	/*
	 * si queremos hacer globarl una lista podemos usar un
	 * @ModelAttribute
	 * 
	 * */
		
	

}
