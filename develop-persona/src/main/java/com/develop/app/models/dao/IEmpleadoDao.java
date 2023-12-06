package com.develop.app.models.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.repository.CrudRepository;

import com.develop.app.models.entity.Empleado;

public interface IEmpleadoDao extends CrudRepository<Empleado, Long>{
	Logger logger = LogManager.getLogger(IEmpleadoDao.class);

	
}
