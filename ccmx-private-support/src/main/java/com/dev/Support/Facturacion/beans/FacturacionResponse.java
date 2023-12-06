package com.dev.Support.Facturacion.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturacionResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("isSuccess")
	private Boolean isSuccess;
	@JsonProperty("folioRespuesta")
	private Long folioRespuesta;
	@JsonProperty("errorCode")
	private Long errorCode;
	@JsonProperty("errorDescription")
	private String errorDescription;
	@JsonProperty("producto")
	private Long producto;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}