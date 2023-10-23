package com.project.testing.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.project.testing.beans.RequestFormulaGeneral;
import com.project.testing.beans.RequestPersona;
import com.project.testing.beans.ResponseEuler;
import com.project.testing.beans.ResponseRfc;
import com.project.testing.service.imp.ValidacionesServiceImpl;

public interface TestingService {

	Logger logger = LogManager.getLogger(TestingService.class);
	
	ResponseEuler getEuler(RequestFormulaGeneral request)throws Exception;
	
	ResponseRfc getRfc(RequestPersona request)throws Exception;
}
