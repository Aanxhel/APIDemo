/**
 * 
 */
package com.ikon.process.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikon.process.controller.TestController;
import com.ikon.process.dao.TestInjectionDao;
import com.ikon.process.service.TestInjectionService;

/**
 * @author icb_ipsra
 *
 */
@Service("testInjectionService")
public class TestInjectionServiceImpl implements TestInjectionService {
	private static final Logger LOGG = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private TestInjectionDao testInjectionDao;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.TestInjectionService#testInjection( java.lang.String )
	 */
	public String testInjection (String userName ) {
		LOGG.info("[:::::::: {} ::::::::]", " Injection Service");
		String valorPass = null;
		try {
			
			valorPass = this.testInjectionDao.testInjectionBD(userName);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valorPass;
	}
	
	
}
