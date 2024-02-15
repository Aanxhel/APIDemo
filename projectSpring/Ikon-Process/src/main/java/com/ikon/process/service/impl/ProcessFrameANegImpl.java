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

import com.ikon.process.dao.ProcessFrameADao;
import com.ikon.process.dto.DatosFrameDto;
import com.ikon.process.dto.ResponseMessage;
import com.ikon.process.json.error.DatosErroresJson;
import com.ikon.process.json.error.ErroresJson;
import com.ikon.process.json.request.RequestDatosFameJson;
import com.ikon.process.json.request.RequestFrameAJson;
import com.ikon.process.service.ProcessFrameANeg;
import com.ikon.process.util.Constantes;
import com.ikon.process.util.ConvierteJsonObjeto;
import com.ikon.process.util.IkUtileria;
import com.ikon.process.util.ValidaRequest;
import com.ikon.process.util.ValidacionFrameA;

/**
 * Gestiona la firma de cada uno de los metodos
 * de la clase implementadora ProcessFrameAImpl 
 * @author icb_ipsra
 *
 */
@Service("processFrameANeg")
public class ProcessFrameANegImpl implements ProcessFrameANeg {

	private static final Logger LOGG = LoggerFactory.getLogger(ProcessFrameANegImpl.class);
	
	@Autowired
	private ProcessFrameADao processFrameADao;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.ProcessFrameANeg@frameBusqueda()
	 */
	@Override
	public ResponseEntity<Object> frameBusqueda(String requestBusquedaFrame) {
		ResponseEntity<Object> entity = null;
		RequestDatosFameJson request = new RequestDatosFameJson();
		DatosErroresJson errroJson = new DatosErroresJson();
		ValidacionFrameA validaError = new ValidacionFrameA();
		
		if ( !ValidaRequest.validaRequestbusquedaFrame(requestBusquedaFrame) ) {
			errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_5, Constantes.MENSAJE_400_5, "");
			entity = ResponseEntity.badRequest().body(errroJson);
			LOGG.error("[:::::::::::: { } :::::::::::]", "Error estructura JSON");
			
		} else {
			request = ConvierteJsonObjeto.convierteJsonBusquedaDatosFrame(requestBusquedaFrame);
			errroJson = validaError.validaErroresBusquedaFrame(request);
			
			if (errroJson.getErrores() != null && !errroJson.getErrores().isEmpty()) {
				entity = ResponseEntity.badRequest().body(errroJson);
				LOGG.error("[::::::::: {Tiene errores en el request }  :::::::::]");
			} else {
				LOGG.info("[::::::::: { Obtieniendo datos }  :::::::::]");
				
				entity = this.obtieneDatosFrame(request);
			}
		} 
		return entity;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ikon.process.service.ProcessFrameANeg@frameBusqueda()
	 */
	@Override
	public ResponseEntity<Object> agregaDatosFrameA (String requestFrameAJson ) {
		ResponseEntity<Object> entity = null;
		RequestFrameAJson request = new RequestFrameAJson();
		DatosErroresJson errroJson = new DatosErroresJson();
		ValidacionFrameA validaError = new ValidacionFrameA();
		
		if ( !ValidaRequest.validaRequestDatosFrame(requestFrameAJson) ) {
			errroJson = IkUtileria.generaSegmentoError(Constantes.CODIGO_400_5, Constantes.MENSAJE_400_5, "");
			entity = ResponseEntity.badRequest().body(errroJson);
			LOGG.error("[:::::::::::: { } :::::::::::]", "Error estructura JSON");
		} else {
			request = ConvierteJsonObjeto.convierteJsonFrameA(requestFrameAJson);
			errroJson = validaError.validaErroresFrameA(request);
			
			if (errroJson.getErrores() != null && !errroJson.getErrores().isEmpty()) {
				entity = ResponseEntity.badRequest().body(errroJson);
				LOGG.error("[::::::::: {Tiene errores en el request }  :::::::::]");
			} else {
				LOGG.info("[::::::::: { Obtieniendo datos }  :::::::::]");
				entity = this.agregaRegistros(request);
			}
		}
		
		return entity;
		
	}
	
	
	/***
	 * Encarado de ir por metodo de insertar registro
	 * @param request
	 * @return
	 */
	private  ResponseEntity<Object> agregaRegistros ( RequestFrameAJson request ) {
		int insertaRegistro = -1;
		
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		
		try {
			
			insertaRegistro = this.processFrameADao.agregaDatosFrameA(request);
		
			if ( insertaRegistro == Constantes.VALOR_INT_CERO ) {
				entity = ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(Constantes.SUCCESS_CODE, Constantes.SUCCES_RECORD));
			}
			
			if ( insertaRegistro == Constantes.VALOR_INT_UNO ) {
				entity = ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(Constantes.CODE_EXISTS, Constantes.RECORD_EXISTS.concat(" ").concat(request.getDesTxtSent())));
			}
			
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.badRequest().body(errroJson);
		}
		
		return entity;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Encargado de obtener los valores correspondientes a la busqueda
	 * @param request
	 * @return
	 */
	private ResponseEntity<Object> obtieneDatosFrame (RequestDatosFameJson request ){
	
		ResponseEntity<Object> entity = null;
		DatosErroresJson errroJson = new DatosErroresJson();
		ArrayList<ErroresJson> errores = new ArrayList<ErroresJson>();
		
		try {
			
			ArrayList<DatosFrameDto> datosgrid = this.processFrameADao.obtieneModulosFrame(request.getCveModulo(),
					request.getCveTopic());
			
			if ( datosgrid.isEmpty() ) {
				datosgrid.add(new DatosFrameDto(Constantes.VALOR_INT_CERO, ""));
			} 
			entity = ResponseEntity.status(HttpStatus.OK).body(datosgrid);
			
		} catch (Exception e) {
			errores.add(new ErroresJson(Constantes.ERROR_COD_500, Constantes.ERROR_COD_500.concat(e.getMessage())));
			errroJson.setErrores(errores);
			entity = ResponseEntity.badRequest().body(errroJson);
		}
		
		return entity;
	}
	
}
