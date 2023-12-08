package com.develop.app.models.services.imp;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.develop.app.models.entity.Empleado;
import com.develop.app.models.services.IEmpleadoService;

@Service
public class EmpleadoServiceImp implements IEmpleadoService {
	
	
	Logger logger = LogManager.getLogger(EmpleadoServiceImp.class);
	
	@Override
	public List<Empleado> findAll(){
		logger.info("service list find all");
		
		List<Empleado> empleadoList = null;
		
		return empleadoList;
	}
}
