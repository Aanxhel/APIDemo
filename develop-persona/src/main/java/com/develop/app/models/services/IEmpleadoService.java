package com.develop.app.models.services;

import java.util.ArrayList;

import com.develop.app.models.entity.Empleado;

public interface IEmpleadoService {

//	Logger logger = LogManager.getLogger(IEmpleadoService.class);

	ArrayList<Empleado> obtenerUsuario();

	Empleado guardarEmpleado(Empleado empleado);
}
