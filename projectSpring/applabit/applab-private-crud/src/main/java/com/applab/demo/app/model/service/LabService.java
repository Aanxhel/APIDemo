package com.applab.demo.app.model.service;

import java.util.List;
import java.util.Optional;

import com.applab.demo.app.model.entity.UsuarioRequest;

public interface LabService {
	
	public List<UsuarioRequest> listar();
	public Optional<UsuarioRequest> listarId(int id);
	public int save(UsuarioRequest request);
	
	
	
}
