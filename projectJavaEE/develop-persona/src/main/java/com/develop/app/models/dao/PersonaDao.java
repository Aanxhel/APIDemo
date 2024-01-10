package com.develop.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.develop.app.models.entity.Persona;

public interface PersonaDao extends CrudRepository<Persona, Long>{

}
