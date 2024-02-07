package com.mhr.registro.app.models.entity.service;

import java.util.List;

import com.mhr.registro.app.models.entity.Monster;

public interface MhrService {

	public List<Monster> buscarMh();

	public void agragarMh(Monster mh);

	public Monster obtenerMhId(Long id);

	public void deleteMh(Long id);
}
