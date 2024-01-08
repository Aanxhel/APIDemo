package com.develop.pokemon.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.develop.pokemon.app.models.entity.Pokemon;

public interface PokedexDao extends CrudRepository<Pokemon, Long>{

}
