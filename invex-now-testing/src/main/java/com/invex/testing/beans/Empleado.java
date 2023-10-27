package com.invex.testing.beans;

import java.io.Serializable;
import java.util.Date;

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
public class Empleado implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("primerNombre")
	private String primerNombre;
	
	@JsonProperty("segundoNombre")
	private String segundoNombre;
	
	@JsonProperty("apellidoPaterno")
	private String apellidoPaterno;
	
	@JsonProperty("apellidoMaterno")
	private String apellidoMaterno;
	
	@JsonProperty("edad")
	private Integer edad;
	
	@JsonProperty("sexo")
	private String sexo;
	
	@JsonProperty("fechaNacimiento")
	private Date fechaNacimiento; //dd-mm-aaaa
	
	@JsonProperty("puesto")
	private String puesto;
	
	
}
