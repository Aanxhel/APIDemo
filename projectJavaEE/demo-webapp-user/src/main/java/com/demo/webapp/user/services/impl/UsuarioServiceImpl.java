package com.demo.webapp.user.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.webapp.user.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	private static final Logger logger = LogManager.getLogger("UsuarioServiceImpl");
	
	public ResponseEntity<Object> getRequestUser(String request){
		logger.info("getRequestUser");
		
		
		return null;
		
	}
	
}
