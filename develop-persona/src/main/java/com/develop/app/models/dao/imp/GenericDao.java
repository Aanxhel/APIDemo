package com.develop.app.models.dao.imp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class GenericDao extends JdbcDaoSupport{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostConstruct
	private void initialize() {
		setJdbcTemplate(jdbcTemplate);
	}


}
