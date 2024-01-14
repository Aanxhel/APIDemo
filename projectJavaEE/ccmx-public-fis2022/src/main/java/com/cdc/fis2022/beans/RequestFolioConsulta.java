package com.cdc.fis2022.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RequestFolioConsulta {
	
	@JsonProperty("folioOtorgante")
	@NotNull(message = "El atributo folioOtorgante es obligatorio")
	@Pattern(regexp = "(^[0-9]+)$", message = "El campo folioOtorgante solo puede contener numeros")
	@Size(min = 5, max = 25)
	private Long folioOtorgante;
	
	@JsonProperty("folioConsulta")
	@NotNull(message = "El atributo folioConsulta es obligatorio")
	@Pattern(regexp = "(^[0-9]+)$", message = "El campo folioConsulta solo puede contener numeros")
	@Size(min = 5, max = 25)
	private Long folioConsulta;

}
