/**
 * 
 */
package com.ikon.process.dao;

/**
 * @author icb_ipsra
 *
 */
public interface TestInjectionDao {

	/***
	 * Valida la conexion y consumo de Base de Datos
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	String testInjectionBD(String userName) throws Exception;
}
