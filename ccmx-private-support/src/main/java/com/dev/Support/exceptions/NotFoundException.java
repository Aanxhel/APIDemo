package com.dev.Support.exceptions;

import java.util.List;

import com.dev.Support.beans.ErrorDTO;

@SuppressWarnings("serial")
public class NotFoundException extends GenericException{

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, List<ErrorDTO> errores) {
        super(message);
        this.errores = errores;
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
