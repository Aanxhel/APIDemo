package com.cdc.testing.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cdc.testing.beans.*;

public interface TestingService {

	static final Logger logger = LogManager.getLogger(TestingService.class);
	
	Response obtenerRFC(Request request) throws Exception;

}
