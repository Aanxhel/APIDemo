package com.develop.app.models.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.develop.app.models.entity.Empleado;

@SuppressWarnings("rawtypes")
@Service
public class ValidacionesService {

	public List<String> validarEmpleado(Empleado request){
		List<String> error = new ArrayList<String>();
		String regexNom = "(^[A-Za-záéíóúüñÁÉÍÓÚÑÜ ]+)$";
		String regexFec = "(19|20)\\d{2}(\\/|-)(0[1-9,2]|1[0-2,2])(\\/|-)(0[1-9,2]|[12][0-9,2]|3[01,2])";
		
		if((request.getPrimerNombre() == null || request.getPrimerNombre().equals("")) 
				&& (request.getSegundoNombre() == null || request.getSegundoNombre().equals(""))) {
			error.add("Es obligatorio proporcionar un nombre");
		}else {
			if((request.getPrimerNombre() != null && !request.getPrimerNombre().equals(""))) {
				
					error.add("El campo primerNombre no puede contener caracteres especiales");
			}
			if (request.getSegundoNombre() != null && !request.getSegundoNombre().equals("")) {
				
					error.add("El campo segundoNombre no puede contener caracteres especiales");
			}
		}
		
		return error;
	}
	
	

}
