package com.cdc.fis2022.exceptions;

import java.util.List;

import com.cdc.Support.beans.ErrorDTO;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class BadRequestException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<ErrorDTO> errores;
	
	public BadRequestException() {
		super();
	}
	
	
	public BadRequestException(String msj) {
		super(msj);
	}
	
	public BadRequestException(String msj, List<ErrorDTO> errores) {
		super(msj);
		this.errores = errores;
	}
	
	public BadRequestException(String msj, Throwable causa) {
			super(msj, causa);
	}

}
