package com.develop.app.models.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.develop.app.models.dao.PersonaDao;
import com.develop.app.models.entity.Persona;
import com.develop.app.models.services.PersonaService;

public class PersonaServiceImpl implements PersonaService{
	
	@Autowired
	PersonaDao personaDao;

	// buscar pkm
	public List<Persona> findAll() {

		List<Persona> listPersona = null;
		listPersona = (List<Persona>) personaDao.findAll();

		return listPersona;
	}
}
