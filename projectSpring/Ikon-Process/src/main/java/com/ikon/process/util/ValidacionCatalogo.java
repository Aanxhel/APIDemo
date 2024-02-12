/**
 * 
 */
package com.ikon.process.util;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ikon.process.dto.CatDato;
import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.json.request.RequestCatalogoJson;

/**
 * @author icb_ipsra
 * @since Julio 31, 2021
 *
 */
public class ValidacionCatalogo {

	private static final Logger LOGG = LoggerFactory.getLogger(ValidacionCatalogo.class);

	/**
	 * Valida si los parametros ingresados tienene eror
	 * 
	 * @param request --> Objeto transportador de datos.
	 * @return objetto de tipo DatosErroresJson
	 */
	public DatosErroresJson validaErroresCatalago(RequestCatalogoJson request) {
		DatosErroresJson datosErroresJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();

		LOGG.info("[:::::::::::: {Validando contenido de JSON  }:::::::::::]");

		if (request.getMovimiento() == null || request.getMovimiento().equals("")) {
			errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1.concat("version")));
		} else {
			
			switch (request.getMovimiento().toUpperCase()) {
			case Constantes.TIPO_MOV_ALTA:
				//TODO: Valor 0
				this.validaSeccion(errores, request, Constantes.TIPO_MOV_ALTA);
				break;
			case Constantes.TIPO_MOV_MODIFICA:
				//TODO: Valor 2
				this.validaSeccion(errores, request, Constantes.TIPO_MOV_MODIFICA);
				break;
			case Constantes.TIPO_MOV_ELIMINA:
				//TODO: Valor 3
				this.validaSeccion(errores, request, Constantes.TIPO_MOV_ELIMINA);
				break;
			case Constantes.TIPO_MOV_LISTA:
				//TODO: Valor 1
				break;	
			default:
				errores.add(new ErroresJson(Constantes.CODIGO_400_11, Constantes.MENSAJE_400_11));
				break;
			}
		}
		datosErroresJson.setErrores(errores);

		return datosErroresJson;
	}
	
	
	/***
	 * Valida Errores
	 * @param errores
	 * @param request
	 * @return
	 */
	private ArrayList<ErroresJson> validaSeccion ( ArrayList<ErroresJson> errores, RequestCatalogoJson request, String mov ){
		
		for ( CatDato dato : request.getDato( )) {
			
			if ( mov.equals(Constantes.TIPO_MOV_MODIFICA) || mov.equals(Constantes.TIPO_MOV_ELIMINA) ) {
				if (dato.getClave() <= 0 ) {
					errores.add(new ErroresJson(Constantes.CODIGO_400_11, Constantes.MENSAJE_400_11_1.concat("clave")));
				}
			}
			if ( dato.getDescripcion() == null && dato.getDescripcion().equals("") ) {
				errores.add(new ErroresJson(Constantes.CODIGO_400_11, Constantes.MENSAJE_400_11_2.concat("descripcion") ));
			} else {
				if (dato.getDescripcion().length() > Constantes.MAX_LENGTH_INT_100 ) {
					errores.add(new ErroresJson(Constantes.CODIGO_400_4, Constantes.MENSAJE_400_4.concat("descripción máximo 100 caracteres") ));
				}
			}
		}
		
		return errores;
	}

}
