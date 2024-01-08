package com.develop.app.models.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.app.models.dao.DevelopDao;
import com.develop.app.models.entity.Empleado;
import com.develop.app.models.services.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{
	
	private static final Logger logger = LoggerFactory.getLogger(EmpleadoServiceImpl.class);

	
	@Override
	@SuppressWarnings("null")
	public List<Empleado> findAll() {
		
		logger.info("buscar empleado");
		
		DevelopDao developDao = null;
		List<Empleado> lstEmpleado = null;
		lstEmpleado = (List<Empleado>) developDao.findAll();
		
		return lstEmpleado;
	}
	


}
