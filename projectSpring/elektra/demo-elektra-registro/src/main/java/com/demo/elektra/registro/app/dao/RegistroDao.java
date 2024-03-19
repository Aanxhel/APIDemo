package com.demo.elektra.registro.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.elektra.registro.app.entity.Usuario;

public interface RegistroDao extends JpaRepository<Usuario, Long> {
	
	 boolean existsByNombre(String nombre);
	
	

}
