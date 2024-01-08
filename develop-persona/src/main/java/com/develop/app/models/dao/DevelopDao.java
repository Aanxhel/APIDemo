package com.develop.app.models.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.develop.app.models.entity.Empleado;

@Repository
public interface DevelopDao extends CrudRepository<Empleado, Long>{

	
		
}
