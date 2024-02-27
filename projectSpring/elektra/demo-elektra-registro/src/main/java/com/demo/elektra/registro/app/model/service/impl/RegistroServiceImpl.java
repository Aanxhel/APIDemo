package com.demo.elektra.registro.app.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.elektra.registro.app.model.dao.RegistroDao;
import com.demo.elektra.registro.app.model.entity.Usuario;
import com.demo.elektra.registro.app.model.service.RegistroService;

@Service
public class RegistroServiceImpl implements RegistroService {

	@Autowired
	RegistroDao registroDao;

	public List<Usuario> findAll() {
		List<Usuario> listUsuarios = new ArrayList<>();
		listUsuarios = (List<Usuario>) registroDao.findAll();
		return listUsuarios;
	}

	
	// insertar
	public void insertarUsuario(Usuario request) {
		registroDao.save(request);
	}
	
}
