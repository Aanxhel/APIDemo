/**
 * 
 */
package com.ikon.process.dao;

import java.util.ArrayList;

import com.ikon.process.dto.CatHeaderDto;

/**
 * Gestiona la firma de cada uno de los metodos de la 
 * clase implementadora CatalogosDaoImpl
 *   
 * @author icb_ipsra
 * @since Julio 30, 2021
 *
 */
public interface CatHeaderDao {

	/***
	 * Obtiene toda la lista de registros con respecto a los headers
	 * 
	 * @return
	 * @throws Exception
	 */
	ArrayList<CatHeaderDto> obtieneHeader() throws Exception;
	
	/**
	 * Realiza la modificación al catalogo
	 * @param cveHeader
	 * @param desHeader
	 * @param movimiento
	 * 		movimiento == 0  (ALTA)
	 * 		movimiento == 2  (UPDATA)
	 * 		movimiento == 3  (DELETE)	
	 * @return
	 * @throws Exception
	 */
	Boolean movimientoHeader (Integer cveHeader, String desHeader, int movimiento  ) throws Exception;
	
	/****
	 * Valida la existencia de la descripción del header
	 * @param desHeader
	 * @return
	 * @throws Exception
	 */
	Boolean existeDesHeader ( String desHeader  ) throws Exception;
}
