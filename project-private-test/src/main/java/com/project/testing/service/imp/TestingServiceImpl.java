package com.project.testing.service.imp;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.project.testing.beans.RequestFormulaGeneral;
import com.project.testing.beans.RequestPersona;
import com.project.testing.beans.ResponseEuler;
import com.project.testing.beans.ResponseRfc;
import com.project.testing.service.TestingService;

@SuppressWarnings("rawtypes")
@Service
public class TestingServiceImpl implements TestingService {

	Logger logger = LogManager.getLogger(TestingServiceImpl.class);

	public ResponseEuler getEuler(RequestFormulaGeneral request) throws Exception {
		logger.info("getEuler");
		ResponseEuler response = new ResponseEuler();
		// libreria de la logica
		int valorA, valorB, resultadoX1;
		

		// seteo de valores
		valorA = request.getFactora();
		valorB = request.getFactorb();

		resultadoX1 = valorA + valorB;

		response.setResultado(resultadoX1);

		return response;
	}
	
	public ResponseRfc getRfc(RequestPersona request)throws Exception{
		logger.info("getRfc");
		ResponseRfc response = new ResponseRfc();
		
		String nombre,
				apellPat,
				apellMat;
		
		Date FechNac;
				
		
		
		return response;
		
	}

}