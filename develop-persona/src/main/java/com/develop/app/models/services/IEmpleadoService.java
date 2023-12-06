package com.develop.app.models.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.develop.app.models.entity.Empleado;

public interface IEmpleadoService {
	
	Logger logger = LogManager.getLogger(IEmpleadoService.class);
	
	public List<Empleado> findAll();
}
