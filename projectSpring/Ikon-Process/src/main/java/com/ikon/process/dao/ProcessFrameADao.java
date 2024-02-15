/**
 * 
 */
package com.ikon.process.dao;

import java.util.ArrayList;

import com.ikon.process.dto.DatosFrameDto;
import com.ikon.process.json.request.RequestFrameAJson;

/**
 * Gestiona la firma de cada uno de los metodos
 * de la clase implementadora ProcessFrameAImpl 
 * @author icb_ipsra
 *
 */
public interface ProcessFrameADao {

	/**
	 * Obtiene una lista de registros para llenar el grid
	 * de la busqueda principal
	 * @param cveModulo ---> Clave del modulo a buscar 
	 * @param cveTopci ---> Clave del topic a buscar
	 * @return
	 * @throws Exception
	 */
	ArrayList<DatosFrameDto> obtieneModulosFrame (int cveModulo, int cveTopci) throws Exception;
	
	/**
	 * Inserta un nuevo registro
	 * @param request
	 * @return
	 * @throws Exception
	 */
	int agregaDatosFrameA ( RequestFrameAJson request ) throws Exception;
}
