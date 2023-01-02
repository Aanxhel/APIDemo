package com.shop.springboot.web.app.firstapp.controllers;

import javax.servlet.http.HttpServletRequest;

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
	
	@GetMapping("/mix-params")
	public String param(@RequestParam String text,@RequestParam Integer number, Model model) {

		model.addAttribute("title", "Example Get!");
		model.addAttribute("result","Result send: " + text + " number is: " + number);

		return "params/show";
	}
	
	@GetMapping("/mix-params-request ")
	public String param(HttpServletRequest  request, Model model) {
		
		String text = request.getParameter("text");
		Integer number = null;
		try {
			number = Integer.parseInt(request.getParameter("number")) ;	
		}catch(NumberFormatException e) { 
			number = 0;
		}
		
		model.addAttribute("title", "Example Get!");
		

		return "params/show";
	}

}
