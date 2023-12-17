package com.develop.app.models.dao.imp;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.develop.app.models.dao.DevelopDao;
import com.develop.app.models.entity.Empleado;

@Repository("DevelopDao")
public class DevelopDaoImpl extends GenericDao implements DevelopDao{
	
	private static final Logger logger = LoggerFactory.getLogger(DevelopDaoImpl.class);
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Empleado> obtenerListaEmpleado( Integer cveTopic) throws Exception {
		
		ArrayList<Empleado> obtenerListaEmpleado = new ArrayList<>();
		
		logger.info("[:::::::::::: updateCardTopic {}  :::::::::]","obtenerListaEmpleado");
		
		return obtenerListaEmpleado;
	}
	

}
