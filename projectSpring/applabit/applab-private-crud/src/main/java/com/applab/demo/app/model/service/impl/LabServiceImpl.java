package com.applab.demo.app.model.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applab.demo.app.model.dao.LabDao;
import com.applab.demo.app.model.entity.UsuarioRequest;
import com.applab.demo.app.model.service.LabService;

@Service
public class LabServiceImpl implements LabService {

	@Autowired
	LabDao repository;

	@Override
	public List<UsuarioRequest> listar() {
		// TODO Auto-generated method stub
		return (List<UsuarioRequest>)repository.findAll();
	}

	@Override
	public Optional<UsuarioRequest> listarId(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public int save(UsuarioRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}



}
