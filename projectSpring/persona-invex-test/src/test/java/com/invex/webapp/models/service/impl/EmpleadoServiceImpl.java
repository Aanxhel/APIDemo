package com.invex.webapp.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invex.webapp.models.dao.EmpleadoDao;
import com.invex.webapp.models.entity.Empleado;
import com.invex.webapp.models.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

	@Autowired
	private EmpleadoDao empleadoDao;
	
	public List<Empleado> listarEmpleado(){
		
		List<Empleado> listEmpleado = null;
		listEmpleado = (List<Empleado>) empleadoDao.findAll();

		return listEmpleado;
		
	}
}
