package com.develop.app.models.services.imp;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.develop.app.models.dao.IEmpleadoDao;
import com.develop.app.models.entity.Empleado;
import com.develop.app.models.services.IEmpleadoService;

@Service
public class EmpleadoServiceImp implements IEmpleadoService {
	
	@Autowired
	private IEmpleadoDao empleadoDao;
	
	Logger logger = LogManager.getLogger(EmpleadoServiceImp.class);
	
	@Override
	public List<Empleado> findAll(){
		logger.info("service list find all");
		
		List<Empleado> empleadoList = (List<Empleado>) empleadoDao.findAll();
		
		return empleadoList;
	}
}
