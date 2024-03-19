package com.demo.elektra.registro.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.elektra.registro.app.dao.RegistroDao;
import com.demo.elektra.registro.app.entity.Usuario;
import com.demo.elektra.registro.app.service.RegistroService;
import com.demo.elektra.registro.app.util.NombreDuplicadoException;
import com.mysql.cj.protocol.result.AbstractResultsetRow;

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
	public Usuario insertarUsuario(Usuario request) {
		
		String fullName = request.getNombre()+ " " + request.getApellidoPaterno();
		request.setFullName(fullName);

		if (registroDao.existsByNombre(request.getNombre())
				&& registroDao.existsByNombre(request.getApellidoPaterno())) {
			throw new NombreDuplicadoException("El nombre de usuario ya está en uso");
		} else {
			return registroDao.save(request);
		}
	}

}
