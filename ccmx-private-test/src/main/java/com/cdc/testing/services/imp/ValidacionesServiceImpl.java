package com.cdc.testing.services.imp;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cdc.Support.beans.Errores;
import com.cdc.testing.beans.Request;
import com.cdc.testing.beans.Response;

@SuppressWarnings("rawtypes")
@Service
public class ValidacionesServiceImpl {
	
	private static final Logger logger = LogManager.getLogger("ValidacionesServiceImpl");
	
	public List<String> validarRequest(Request request) {
		List<String> error = new ArrayList<String>();
		String regexNom = "(^[A-Za-záéíóúüñÁÉÍÓÚÑÜ ]+)$";
		String regexFec = "(19|20)\\d{2}(\\/|-)(0[1-9,2]|1[0-2,2])(\\/|-)(0[1-9,2]|[12][0-9,2]|3[01,2])";
		
		if((request.getApellidoPaterno() == null || request.getApellidoPaterno().equals("")) 
				&& (request.getApellidoMaterno() == null || request.getApellidoMaterno().equals(""))) {
			error.add("Es obligatorio al menos 1 de los apellidos");
		}else {
			if(request.getApellidoPaterno() != null && !request.getApellidoPaterno().equals("")) {
				if(!validaExpresionReg(request.getApellidoPaterno(),regexNom))
					error.add("El campo apellidoPaterno no puede contener caracteres especiales");
			}
			if(request.getApellidoMaterno() != null && !request.getApellidoMaterno().equals("")) {
				if(!validaExpresionReg(request.getApellidoMaterno(),regexNom))
					error.add("El campo apellidoMaterno no puede contener caracteres especiales");
			}
		}
		
		if((request.getPrimerNombre() == null || request.getPrimerNombre().equals("")) 
				&& (request.getSegundoNombre() == null || request.getSegundoNombre().equals(""))) {
			error.add("Es obligatorio proporcionar un nombre");
		}else {
			if((request.getPrimerNombre() != null && !request.getPrimerNombre().equals(""))) {
				if(!validaExpresionReg(request.getPrimerNombre(),regexNom))
					error.add("El campo primerNombre no puede contener caracteres especiales");
			}
			if (request.getSegundoNombre() != null && !request.getSegundoNombre().equals("")) {
				if(!validaExpresionReg(request.getSegundoNombre(),regexNom))
					error.add("El campo segundoNombre no puede contener caracteres especiales");
			}
		}
		
		if(request.getFechaNacimiento() == null || request.getFechaNacimiento().equals("")) {
			error.add("Es obligatorio proporcionar la Fecha de Nacimiento");
		}else {
			if(!validaExpresionReg(request.getFechaNacimiento(), regexFec))
				error.add("El campo fechaNacimiento no cumple con el formato requerido: YYYY-MM-DD");
		}
		
		return error;
	}
	
	public boolean validaExpresionReg(String texto, String expReg) {
		Pattern p = Pattern.compile(expReg);
		Matcher m = p.matcher(texto);
		return m.matches();
	}
	
	public Response responseRFC(List<String> errores) {
		Response response = new Response();
		
		response.setErrorCode(400L);
		response.setErrorDescription(errores);
		
		return response;
	}
	
	public ResponseEntity validarResponse(Response response){
		ResponseEntity entity = null;
		
		if (response != null) {
			if (response.getErrorDescription() == null) {
				entity = ResponseEntity.ok().body(response);
			} else if (response.getErrorCode() != null && response.getErrorCode().equals(400L)) {
				entity = ResponseEntity.badRequest().body(response);
			} else {
				entity = ResponseEntity.status(response.getErrorCode().intValue()).body(response);
			}
		}else {
			String mensajeError = "Datos incorrectos";
			response = new Response();
			List<String> mensajesError = new ArrayList<String>();
			mensajesError.add(mensajeError);
			response.setErrorCode(400L);
			response.setErrorDescription(mensajesError);
			entity = ResponseEntity.badRequest().body(response);
		}
		
        return entity;
    }
}
