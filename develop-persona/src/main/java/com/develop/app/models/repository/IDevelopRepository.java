package com.develop.app.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.develop.app.models.entity.Empleado;

@Repository
public interface IDevelopRepository extends CrudRepository<Empleado, Integer>{
//public interface IDevelopRepository extends JpaRepository<Empleado, Integer>{
//	public interface IDevelopRepository{

	
}
