/**
 * 
 */
package com.ikon.process.dao.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.ikon.process.dao.TestInjectionDao;

/**
 * @author icb_ipsra
 * @since Agosto 8, 2021
 *
 */
@Repository("testInjectionDao")
public class TestInjectionDaoImpl  extends GenericDao implements TestInjectionDao {

	private static final Logger LOGG = LoggerFactory.getLogger(TestInjectionDaoImpl.class);
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.dao.UsuarioDao#testInjectionBD( java.lang.String )
	 */
	public String testInjectionBD(String userName) throws Exception {
		LOGG.info("[::::::::::::  Busca usuario  :::::::::::.]");
		String passs = null;
		
		try {
			
			SimpleJdbcCall simpleJdbcCall	=	new SimpleJdbcCall(getJdbcTemplate())
							.withCatalogName("dbikon")
							.withProcedureName("sp_buscaUsuario");
							
			
			SqlParameterSource params = new MapSqlParameterSource()
	                							.addValue("in_username", userName); 
			
			Map<String, Object> mapResults = simpleJdbcCall.execute(params);
			
			if ( (String) mapResults.get("out_username") != null && !mapResults.get("out_username").toString().equals("") ) {
				passs = (String) mapResults.get("out_password");
			}
			
		} catch (Exception e) {
			LOGG.error("[:::::::::::: buscaUsuario {}  :::::::::]", e.getMessage());
			throw new Exception();
		}
		
		return passs;
		
	}
}
