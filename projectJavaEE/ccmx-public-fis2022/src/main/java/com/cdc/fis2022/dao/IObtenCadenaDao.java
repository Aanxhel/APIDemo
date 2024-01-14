package com.cdc.fis2022.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface IObtenCadenaDao{
	
	
	String getCadenaDao(Long folioConsulta, Long npersCve);
	

}
