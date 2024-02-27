package com.demo.elektra.registro.app.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.demo.elektra.registro.app.model.entity.Usuario;

public interface RegistroDao extends CrudRepository<Usuario, Long>{

}
