package com.develop.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Persona implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nombre")
	private String nombre;
	
	@JsonProperty("apellidoPaterno")
	private String apellPat;
	
	@JsonProperty("apellidoMaterno")
	private String apellMat;
	
	@JsonProperty("edad")
	private Integer edad;
	
	@JsonProperty("sexo")
	private String sexo;
	
	@JsonProperty("fechaNacimiento")
	private Date fechaNacimiento;
	
	@JsonProperty("puesto")
	private String puesto;

}
