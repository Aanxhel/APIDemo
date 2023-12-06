package com.dev.Support.beans;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class ErrorDTO implements Serializable {
    
    private static final long serialVersionUID = 2736758180940687922L;

    private String mensaje;
    private String codigo;

    public static List<ErrorDTO> getErrorList(String mensaje, String codigo) {
        List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
        errors.add(new ErrorDTO(mensaje, codigo));
        return errors;
    }

}
