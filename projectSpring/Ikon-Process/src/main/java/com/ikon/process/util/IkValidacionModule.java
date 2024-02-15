/**
 * 
 */
package com.ikon.process.util;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.json.request.IkRequestModuleJson;

/**
 * @author icb_ipsra
 * @since October, 2021
 *
 */
public class IkValidacionModule {

	private static final Logger LOGG = LoggerFactory.getLogger(IkValidacionModule.class);

	/**
	 * Valida si los parametros ingresados tienene eror
	 * 
	 * @param request --> Objeto transportador de datos.
	 * @return objetto de tipo DatosErroresJson
	 */
	public DatosErroresJson validaErroresModule(IkRequestModuleJson request) {
		DatosErroresJson datosErroresJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		LOGG.info("[:::::. {} .:::::]"," Validating Object IkRequestSentenceCard ");
		
		if (request.getCveModule() == null) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("cveModule")));
		} 
		if ( request.getModuleName() == null || request.getModuleName().equals("") ){
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("moduleName")));
		} else {
			if ( request.getModuleName().length() > Constantes.MAX_LENGTH_INT_150 ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("moduleName")));
			}
		}
		if ( request.getModuleDesc() != null && !request.getModuleDesc().equals("") ) {
			if ( request.getModuleDesc().length() > Constantes.MAX_LENGTH_INT_150 ) {
				 errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("sentences")));
			}
		}
		
		if (request.getCveModule() == Constantes.VALOR_INT_CERO ) {
			if ( request.getSesiones().isEmpty() || request.getSesiones().size() == Constantes.VALOR_INT_CERO ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_1.concat("Array sentences")));
			}
		}

		datosErroresJson.setErrores(errores);

		return datosErroresJson;
		
	}
	

}
