package com.develop.pokemon.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.pokemon.app.models.entity.Pokemon;
import com.develop.pokemon.app.models.service.PokedexService;

@RestController
public class pkmController {

	@Autowired
	PokedexService pkmService;
	
	@GetMapping("listapkm")
	public List<Pokemon> index(){
		
		List<Pokemon> listPmk = null;
		listPmk = pkmService.findAll();
		
		return listPmk;
	}
}
