package com.develop.pokemon.app.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.pokemon.app.models.dao.PokedexDao;
import com.develop.pokemon.app.models.entity.Pokemon;
import com.develop.pokemon.app.models.service.PokedexService;

@Service
public class PokedexServiceImpl implements PokedexService {

	@Autowired
	private PokedexDao pokedexDao;

	// buscar pkm
	public List<Pokemon> findAll() {

		List<Pokemon> listPmk = null;
		listPmk = (List<Pokemon>) pokedexDao.findAll();

		return listPmk;
	}

	// guardar
	public void savePkm(Pokemon pokemon) {
		pokedexDao.save(pokemon);
	}

	// buscar por ID
	public Pokemon getPkmId(Long id) {
		return pokedexDao.findById(id).get();
	}

	// eliminar
	public void deletePkm(Long id) {
		pokedexDao.deleteById(id);
	}
	


}
