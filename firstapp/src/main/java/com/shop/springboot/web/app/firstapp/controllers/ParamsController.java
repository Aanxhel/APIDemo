package com.shop.springboot.web.app.firstapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/params")
public class ParamsController {

	@GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("title", "Set Get!");
		return "params/index";
	}
	
	
	@GetMapping("/string")
	public String param(@RequestParam(name = "text", required = false) String text, Model model) {

		model.addAttribute("title", "Example Get!");
		model.addAttribute("result","Result send: " + text);

		return "params/show";
	}

}
