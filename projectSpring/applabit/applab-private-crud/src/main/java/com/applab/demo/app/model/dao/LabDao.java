package com.applab.demo.app.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.applab.demo.app.model.entity.UsuarioRequest;

public interface LabDao extends CrudRepository<UsuarioRequest, Long>{

}
