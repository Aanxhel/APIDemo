package com.develop.app.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

import com.develop.app.entity.Empleado;

public interface IEmpleadoService extends JpaRepository<Empleado,Integer> {
	
	Logger logger = LogManager.getLogger(IEmpleadoService.class);
	
	public List<Empleado> listarEmpleados() throws RuntimeException  ;
	public void guardarEmpleado(Empleado empleado)throws RuntimeException;
	public Empleado obtenerEmpleadoId(Integer id)throws RuntimeException;
	public void eliminarEmpleado(Integer id)throws RuntimeException;

}
