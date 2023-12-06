package com.dev.Support.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import com.dev.Support.beans.ErrorDTO;

@Data
@AllArgsConstructor
public class GenericException extends Exception{

    protected List<ErrorDTO> errores;

    public GenericException() {
        super();
    }

    public GenericException(String message) {
        super(message);
    }

    public GenericException(String message, List<ErrorDTO> errores) {
        super(message);
        this.errores = errores;
    }

    public GenericException(String message, Throwable cause) {
        super(message, cause);
    }

}