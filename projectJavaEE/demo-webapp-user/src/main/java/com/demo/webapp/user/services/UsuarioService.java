package com.demo.webapp.user.services;

import org.springframework.http.ResponseEntity;

public interface UsuarioService {

	public ResponseEntity<Object> getRequestUser(String request);
}
