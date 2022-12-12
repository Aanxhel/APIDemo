package com.shop.springboot.web.app.firstapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.springboot.web.app.firstapp.models.UserModel;

@Controller
@RequestMapping("/app")
public class IndexController {

	// muestra el index
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("title", "Document Spring!");
		return "index";
	}

	// crearemos la peticion del POJO
	@GetMapping("/profile")
	public String profile(Model model) {

		UserModel userModel = new UserModel();

		userModel.setName("Alpha");
		userModel.setLastName("Power");

		model.addAttribute("title", "Documento Spring!");
		model.addAttribute("user", userModel);

		return "profile";
	}

	@GetMapping("/listed")
	public String listed(Model model) {
		
		model.addAttribute("title", "Listener");
		
		return "listed";
	}

	@ModelAttribute("users")
	public List<UserModel> userListadd() {
		List<UserModel> users = new ArrayList<>();

		users.add(new UserModel("Alfa", "Bravo", "alfa@mail.com"));
		users.add(new UserModel("Coca", "Fox", "coca@mail.com"));
		users.add(new UserModel("Delta", "Golfo", "delta@mail.com"));

		return users;
	}
}
