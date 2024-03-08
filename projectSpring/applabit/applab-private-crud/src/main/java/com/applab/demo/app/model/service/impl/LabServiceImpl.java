package com.applab.demo.app.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.applab.demo.app.model.dao.LabDao;
import com.applab.demo.app.model.entity.UsuarioRequest;
import com.applab.demo.app.model.service.LabService;

public class LabServiceImpl implements LabService{
	
	@Autowired
	LabDao userDao;
	
	
	//view lista de datos
	public List<UsuarioRequest> listaUsuarios(){
		
		List<UsuarioRequest> listUsuario = null;
		listUsuario = (List<UsuarioRequest>) userDao.findAll();

		return listUsuario;
	}

}
