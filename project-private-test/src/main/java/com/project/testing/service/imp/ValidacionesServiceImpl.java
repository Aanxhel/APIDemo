package com.project.testing.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.testing.beans.RequestFormulaGeneral;
import com.project.testing.beans.ResponseEuler;

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

}
