package com.demo.elektra.registro.app.service;

import java.util.List;

import com.demo.elektra.registro.app.entity.Usuario;

public interface RegistroService {

	// muestra usuarios registrados
	public List<Usuario> findAll();

	// agregar usuario nuevo
	public Usuario insertarUsuario(Usuario request);
	

	
	
}
