package com.develop.app.models.services.imp;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.app.models.entity.Empleado;
import com.develop.app.models.repository.IDevelopRepository;
import com.develop.app.models.services.IEmpleadoService;

@Service
public class EmpleadoServiceImp implements IEmpleadoService{
	
	@Autowired
	IDevelopRepository developRepository;
	
//	Logger logger = LogManager.getLogger(EmpleadoServiceImp.class);
	

	public ArrayList<Empleado> obtenerUsuario() {
		
		ArrayList<Empleado>empleadoList = null;
		
		 empleadoList = (ArrayList<Empleado>)developRepository.findAll();
		
		
		 
		return empleadoList;
	}
	
	public Empleado guardarEmpleado(Empleado empleado) {
		Empleado saveEmpleado = this.guardarEmpleado(empleado);
		 
		return saveEmpleado;
	}
	
}
