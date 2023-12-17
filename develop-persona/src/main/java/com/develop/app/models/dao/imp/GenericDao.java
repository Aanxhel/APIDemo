package com.develop.app.models.dao.imp;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class GenericDao extends JdbcDaoSupport{
	
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(GenericDao.class);
	
	@PostConstruct
	private void initialize() {
		logger.info("initialize");
//		setJdbcTemplate(jdbcTemplate);
	}

}
