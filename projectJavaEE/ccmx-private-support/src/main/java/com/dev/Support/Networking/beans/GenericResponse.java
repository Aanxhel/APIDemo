package com.dev.Support.Networking.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dev.Support.beans.ErrorDTO;
import com.dev.Support.exceptions.GenericException;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GenericResponse<T> {

    @JsonProperty("httpStatus")
    private Integer httpStatus;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("message")
    private String message;
    @JsonProperty("errors")
    private List<ErrorDTO> errors;
    @JsonProperty("data")
    private T data;

    public void addError(ErrorDTO error) {
        if (errors == null) {
            errors = new ArrayList<ErrorDTO>();
        }
        errors.add(error);
    }

    public static GenericResponse getInternalServerErrorGenericResponse(){
        return new GenericResponse(500,false,"Error", Collections.singletonList(new ErrorDTO("Internal server error","500")),null);
    }

    public static GenericResponse getInternalServiceErrorGenericResponse(){
        return new GenericResponse(500,false,"Error", Collections.singletonList(new ErrorDTO("Internal service not working","500")),null);
    }

    public static GenericResponse getBadRequestGenericResponse(){
        return new GenericResponse(400,false,"Error", Collections.singletonList(new ErrorDTO("Bad request","400")),null);
    }

    public static GenericResponse getRelacionJuridicaNotEstablishedGenericResponse(String message,List<ErrorDTO> errores){
        return new GenericResponse(403,false,message, errores,null);
    }

    public static GenericResponse getForbiddenGenericResponse(String message,List<ErrorDTO> errores){
        return new GenericResponse(403,false,message, errores,null);
    }

    public static GenericResponse getBadRequestGenericResponse(String message,List<ErrorDTO> errores){
        return new GenericResponse(400,false,message, errores,null);
    }

    public static GenericResponse getMatchExceptionGenericResponse(String message,List<ErrorDTO> errores){
        return new GenericResponse(404,false,message,Collections.singletonList(errores) ,null);
    }

    public static GenericResponse getNotAuthenticatedResponse(String message){
        return new GenericResponse(401,false,message,Collections.singletonList(new ErrorDTO("UnAuthorized","401")) ,null);
    }

    public static GenericResponse getNotAuthenticatedResponse(GenericException exception){
        return new GenericResponse(401,false,exception.getMessage(),exception.getErrores() ,null);
    }

    public static GenericResponse getPersonNotFoundResponse(String message){
        return new GenericResponse(404,false,message,Collections.singletonList(new ErrorDTO("Not found, persona no encontrada","404")) ,null);
    }

    public static GenericResponse getNotFoundGenericResponse(String message,List<ErrorDTO> errores){
        return new GenericResponse(404,false,message,Collections.singletonList(errores) ,null);
    }

    public static GenericResponse getNoContentGenericResponse(String message,List<ErrorDTO> errores){
        return new GenericResponse(204,false,message,Collections.singletonList(errores) ,null);
    }

    public static GenericResponse getConflictGenericResponse(String message,List<ErrorDTO> errores){
        return new GenericResponse(409,false,message,Collections.singletonList(errores) ,null);
    }

    public static GenericResponse getDataAlreadyInGenericResponse(String message){
        return new GenericResponse(409,false,message,Collections.singletonList(new ErrorDTO("Data already inserted, la informacion a insertar ya esta registrada","409")) ,null);
    }
    
	@Override
	public String toString() {
		return "GenericResponse [httpStatus=" + httpStatus + ", success=" + success + ", message=" + message
				+ ", errors=" + errors + ", data=" + data + "]";
	}
}