package com.project.testing.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.testing.beans.RequestFormulaGeneral;
import com.project.testing.beans.RequestPersona;
import com.project.testing.beans.ResponseEuler;
import com.project.testing.beans.ResponseRfc;

@SuppressWarnings("rawtypes")
@Service
public class ValidacionesServiceImpl {

	Logger logger = LogManager.getLogger(ValidacionesServiceImpl.class);

	public List<String> validarRequest(RequestFormulaGeneral request) {

		List<String> error = new ArrayList<String>();

		if (request.getFactora() == null || request.getFactora().equals("")) {
			error.add("Es obligatorio el facto A");
		}
		if (request.getFactorb() == null || request.getFactorb().equals("")) {
			error.add("Es obligatorio el facto b");
		}
		return error;
	}

	public ResponseEuler responseEuler(List<String> errores) {
		ResponseEuler response = new ResponseEuler();

		response.setErrorCode(400L);
		response.setErrorDescription(errores);

		return response;
	}

	public ResponseEntity validarResponse(ResponseEuler response) {
		ResponseEntity entity = null;

		if (response != null) {
			if (response.getErrorDescription() == null) {
				entity = ResponseEntity.ok().body(response);
			} else if (response.getErrorCode() != null && response.getErrorCode().equals(400L)) {
				entity = ResponseEntity.badRequest().body(response);
			} else {
				entity = ResponseEntity.status(response.getErrorCode().intValue()).body(response);
			}
		} else {
			String mensajeError = "Datos incorrectos";

			response = new ResponseEuler();
			List<String> mensajesError = new ArrayList<String>();
			mensajesError.add(mensajeError);
			response.setErrorCode(400L);
			response.setErrorDescription(mensajesError);
			entity = ResponseEntity.badRequest().body(response);
		}

		return entity;
	}

	// validacion de servicio RFC
	public List<String> validarPersona(RequestPersona request) {

		List<String> error = new ArrayList<String>();

		if (request.getNombre() == null || request.getNombre().equals("")) {
			error.add("Es obligatorio el Nombre");
		}

		if (request.getApellidoPaterno() == null || request.getApellidoPaterno().equals("")) {
			error.add("Es obligatorio el Apellido Paterno");
		}

		if (request.getApellidoMaterno() == null || request.getApellidoMaterno().equals("")) {
			error.add("Es obligatorio el Apellido Materno");
		}

		if (request.getFechaNacimineto() == null || request.getFechaNacimineto().equals("")) {
			error.add("Es obligatorio el Fecha Nacimineto");
		}

		return error;
	}
	
	public ResponseRfc responseRfc(List<String> errores) {
		ResponseRfc response = new ResponseRfc();

//		response.setErrorCode(400L);
//		response.setErrorDescription(errores);

		return response;
	}
	
	public ResponseEntity validarResponseRfc(ResponseRfc response) {
		ResponseEntity entity = null;

		if (response != null) {
//			if (response.getErrorDescription() == null) {
//				entity = ResponseEntity.ok().body(response);
//			} else if (response.getErrorCode() != null && response.getErrorCode().equals(400L)) {
//				entity = ResponseEntity.badRequest().body(response);
//			} else {
//				entity = ResponseEntity.status(response.getErrorCode().intValue()).body(response);
//			}
		} else {
			String mensajeError = "Datos incorrectos";

			response = new ResponseRfc();
			List<String> mensajesError = new ArrayList<String>();
			mensajesError.add(mensajeError);
//			response.setErrorCode(400L);
//			response.setErrorDescription(mensajesError);
			entity = ResponseEntity.badRequest().body(response);
		}

		return entity;
	}
	
	// fin validacion de servicio RFC

}
