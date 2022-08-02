package com.dev.backend.apirest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//el controlador se encarga de administras las peticiones del usuario
@Controller
public class IndexController {
	
	//indica es de tipo GET
	@GetMapping({"/home","/","/index"})
	public String index() {
		return "index";
	}

}
