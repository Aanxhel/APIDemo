package com.develop.pokemon.app.models.service;

import java.util.List;

import com.develop.pokemon.app.models.entity.Pokemon;

public interface PokedexService {

	// all
	public List<Pokemon> findAll();

	// obtener by id
	public Pokemon getPkmId(Long id);

	// guardar
	public void savePkm(Pokemon pokemon);

	// eliminar
	public void deletePkm(Long id);
}
