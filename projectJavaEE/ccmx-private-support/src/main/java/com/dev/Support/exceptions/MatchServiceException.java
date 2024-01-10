package com.dev.Support.exceptions;

import java.util.List;

import com.dev.Support.beans.ErrorDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchServiceException extends Exception{
	
	protected List<ErrorDTO> errores;

	
	public MatchServiceException() {
		super();
	}
	
	 public MatchServiceException(String message) {
	        super(message);
	    }
	
	public MatchServiceException(String mensaje, List<ErrorDTO> errorEs) {
		super(mensaje);
		this.errores = errorEs;
	}
	
	public MatchServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
