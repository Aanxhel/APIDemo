package com.develop.app.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

	

	@JsonProperty("primerNombre")
	private String primerNombre;
	@JsonProperty("segundoNombre")
	private String segundoNombre;
	@JsonProperty("apelliPat")
	private String apelliPat;
	@JsonProperty("apelliMat")
	private String apelliMat;
	@JsonProperty("edad")
	private Integer edad;
	@JsonProperty("sexo")
	private String sexo;
	@JsonProperty("fechaNacimiento")
	private Date fechaNacimiento;
	@JsonProperty("puesto")
	private String puesto;

}
