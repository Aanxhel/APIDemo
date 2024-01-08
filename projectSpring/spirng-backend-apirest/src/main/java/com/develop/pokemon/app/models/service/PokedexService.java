package com.develop.pokemon.app.models.service;

import java.util.List;

import com.develop.pokemon.app.models.entity.Pokemon;

public interface PokedexService {
	
	public List<Pokemon> findAll();
}
