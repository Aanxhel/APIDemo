package com.demo.elektra.registro.app.model.service;

import java.util.List;

import com.demo.elektra.registro.app.model.entity.Usuario;

public interface RegistroService {

	// muestra usuarios registrados
	public List<Usuario> findAll();

	// agregar usuario nuevo
	public void insertarUsuario(Usuario request);
}
