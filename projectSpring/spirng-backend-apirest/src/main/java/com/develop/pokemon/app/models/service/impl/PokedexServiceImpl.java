package com.develop.pokemon.app.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.pokemon.app.models.dao.PokedexDao;
import com.develop.pokemon.app.models.entity.Pokemon;
import com.develop.pokemon.app.models.service.PokedexService;

@Service
public class PokedexServiceImpl implements PokedexService{

	@Autowired
	private PokedexDao pokedexDao;
	
	@Override
	public List<Pokemon> findAll() {
		
		List<Pokemon> listPmk = null;
		listPmk = (List<Pokemon>) pokedexDao.findAll();
		
		return listPmk;
	}

}
