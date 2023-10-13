package com.project.testing.service.imp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.project.testing.service.TestingService;


@SuppressWarnings("rawtypes")
@Service
public class TestingServiceImpl implements TestingService{
	
	Logger logger = LogManager.getLogger(TestingServiceImpl.class);
	

}
