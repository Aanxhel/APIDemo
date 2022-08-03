package com.dev.backend.apirest.controller;

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
		//usuario.setEmail("corre@mail.com");
				
		modelMap.addAttribute("titulo", "Nombre: ".concat(usuario.getNombre()) );
		modelMap.addAttribute("usuario", usuario);
		
		
		return "perfil";
	}
		
}
