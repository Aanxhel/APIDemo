package com.develop.app.service.imp;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.develop.app.entity.Empleado;
import com.develop.app.service.IEmpleadoService;

@Service
public class EmpleadoServiceImp {

	@Autowired
	private IEmpleadoService service;

	Logger logger = LogManager.getLogger(EmpleadoServiceImp.class);
	
	public List<Empleado> listarEmpleados(){
		return service.findAll();
		
	}
	
	public void guardarEmpleado(Empleado empleado) {
		service.save(empleado);
	}
	
	public Empleado obtenerEmpleadoId(Integer id) {
		return service.findById(id).get();
	}
	
	public void eliminarEmpleado(Integer id) {
		service.deleteById(id);
	}
}
