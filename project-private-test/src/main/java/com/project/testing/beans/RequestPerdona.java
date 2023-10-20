package com.project.testing.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestPerdona {
	
	@JsonProperty("nombre")
    private String nombre;
	
	@JsonProperty("apellidoPaterno")
    private String apellidoPaterno;
	
	@JsonProperty("apellidoMaterno")
    private String apellidoMaterno;
	
	@JsonProperty("apellidoMaterno")
	private Datos datos;
}
