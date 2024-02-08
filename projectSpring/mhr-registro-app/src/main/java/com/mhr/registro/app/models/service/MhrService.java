package com.mhr.registro.app.models.service;

import java.util.List;

import com.mhr.registro.app.models.entity.Monster;

public interface MhrService {

	// muestra de lista
	public List<Monster> buscarMh();

	// busqueda por id
	public Monster obtenerMhId(Long id);

	// insertar postman
	public void agragarMh(Monster mh);

	// borrar
	public void deleteMh(Long id);
}
