package com.shop.springboot.web.app.firstapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.springboot.web.app.firstapp.models.UserModel;

@Controller
@RequestMapping("/app")
public class IndexController {

	//muestra el index
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("title", "Document Spring!");
		return "index";
	}
	
	
	//crearemos la peticion del POJO
	@GetMapping("/profile")
	public String profile(Model model) {
		
		UserModel userModel = new UserModel();
		
		userModel.setName("Alpha");
		userModel.setLastName("Power");
		
		model.addAttribute("title", "Documento Spring!");
		model.addAttribute("user", userModel);
		
		return "profile";
	}
}
