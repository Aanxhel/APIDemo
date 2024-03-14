package com.applab.demo.app.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.applab.demo.app.model.entity.UsuarioRequest;

public interface LabDao extends JpaRepository<UsuarioRequest, Long>{

}
