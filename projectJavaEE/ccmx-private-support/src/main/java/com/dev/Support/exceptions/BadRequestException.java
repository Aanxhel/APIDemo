package com.dev.Support.exceptions;

import java.util.List;

import com.dev.Support.beans.ErrorDTO;

@SuppressWarnings("serial")
public class BadRequestException extends GenericException{

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, List<ErrorDTO> errores) {
        super(message);
        this.errores = errores;
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
