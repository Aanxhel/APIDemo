/**
 * 
 */
package com.ikon.process.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ikon.process.dao.CatHeaderDao;
import com.ikon.process.dto.CatDato;
import com.ikon.process.dto.CatHeaderDto;
import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.json.request.RequestCatalogoJson;
import com.ikon.process.service.CatHeaderNeg;
import com.ikon.process.util.Constantes;
import com.ikon.process.util.ConvierteJsonObjeto;
import com.ikon.process.util.IkUtileria;
import com.ikon.process.util.ValidaRequest;
import com.ikon.process.util.ValidacionCatalogo;

/**
 * Clase encargade de gestionar cada uno de los catalogos de este proceso
 * 
 * @author icb_ipsra
 * @since Julio 21, 2021
 *
 */
@Service("catHeaderNeg")
public class CatHeaderNegImpl implements CatHeaderNeg {

	private static final Logger LOGG = LoggerFactory.getLogger(CatHeaderNegImpl.class);

	@Autowired
	private CatHeaderDao catHeaderDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.CatalogosNeg@catHeader(java.lang.String)
	 */
	public ResponseEntity<Object> catHeader(String requestCatalogoJson) {
		ResponseEntity<Object> entity = null;
		RequestCatalogoJson request = new RequestCatalogoJson();
		DatosErroresJson errroJson = new DatosErroresJson();
		ValidacionCatalogo validaError = new ValidacionCatalogo();

		if (!ValidaRequest.validaRequestCatalogo(requestCatalogoJson)) {
			errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_5, Constantes.MENSAJE_400_5, "");
			entity = ResponseEntity.badRequest().body(errroJson);
			LOGG.error("[:::::::::::: { } :::::::::::]", "Error estructura JSON");
		} else {
			request = ConvierteJsonObjeto.convierteJsonObjectoCatalogo(requestCatalogoJson);
			errroJson = validaError.validaErroresCatalago(request);

			if (errroJson.getErrores() != null && !errroJson.getErrores().isEmpty()) {
				entity = ResponseEntity.badRequest().body(errroJson);
				LOGG.error("[::::::::: {Tiene errores en el request }  :::::::::]");
			} else {
				if ( request.getMovimiento().trim().toUpperCase().equals(Constantes.TIPO_MOV_LISTA) ) {
					entity = this.obtieneCatalogoHeader();
				} else {
					entity = this.movimientoHeader(request, request.getMovimiento().trim().toUpperCase());
				}
			}

		}
		return entity;
	}
	
	/****
	 * Genera el movimiento a cada para el catalogo de Headers
	 * @param request
	 * @param movimiento
	 *   movimiento == 0  (ALTA)
	 * 	 movimiento == 2  (UPDATA)
	 * 	movimiento == 3  (DELETE)	
	 * @return
	 */
	private ResponseEntity<Object> movimientoHeader ( RequestCatalogoJson request, String movimiento) {
		Boolean movimientoAplicado = false;
		Boolean existeHeader = true;
		
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		int tipoMovimiento = 0;
		
		
		switch (movimiento) {
		case Constantes.TIPO_MOV_MODIFICA:
			tipoMovimiento = 2;
			break;
		case Constantes.TIPO_MOV_ELIMINA:
			tipoMovimiento = 3;
			break;	
		default:
			break;
		}
		
		try {
			
			for (CatDato dato : request.getDato()) {
				String desHeader = dato.getDescripcion().trim().toUpperCase();
				
				//TODO: Validando la existencia de la descripcion
				if ( tipoMovimiento == 0 || tipoMovimiento == 2 ) {
				
					existeHeader = this.existeDescheader(desHeader);
					if ( !existeHeader ) {
						//TODO: Insertando/Modificando/eliminando registro
						movimientoAplicado = this.catHeaderDao.movimientoHeader(dato.getClave(), desHeader , tipoMovimiento);
					} else {
						errores.add(new ErroresJson(Constantes.CODIGO_400_1, Constantes.MENSAJE_400_1_5.concat(desHeader)));
						errroJson.setErrores(errores);
						entity = ResponseEntity.badRequest().body(errroJson);
					}
				}
				
				if ( tipoMovimiento == 3 ) {
					movimientoAplicado = this.catHeaderDao.movimientoHeader(dato.getClave(), desHeader , tipoMovimiento);
				}
				
				if ( movimientoAplicado ) {
					entity = this.obtieneCatalogoHeader();
				}
				
			}
			
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.badRequest().body(errroJson);
		}
		
		return entity;
	} 

	
	/***
	 * Valida la existencia de la descricpion
	 * @param desHeader
	 * @return
	 * 	true == Existe la descripcion
	 *  false == Disponible
	 * @throws Exception
	 */
	private Boolean existeDescheader ( String desHeader ) throws Exception {
		Boolean existeHeader = false;
		existeHeader = this.catHeaderDao.existeDesHeader(desHeader);
		return existeHeader;
	}
	
	
	/**
	 * Metodo encargado de obtener toda la lista de registros de la tabla para
	 * generar el catalog
	 *
	 * @return
	 */
	private ResponseEntity<Object> obtieneCatalogoHeader() {
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();

		try {

			ArrayList<CatHeaderDto> catHeader = this.catHeaderDao.obtieneHeader();
			entity = ResponseEntity.status(HttpStatus.OK).body(catHeader);

		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.badRequest().body(errroJson);
		}

		return entity;
	}

}
