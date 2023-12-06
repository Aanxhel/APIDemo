package com.cdc.testing.beans;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
public class Request {
	
	@Valid
	@JsonProperty("primerNombre")
	@NotNull(message = "El campo primerNombre es obligatorio")
	@Pattern(regexp = "(^[A-Za-záéíóúüñÁÉÍÓÚÑÜ ]+)$", message = "El campo primerNombre no puede contener caracteres especiales")
    private String primerNombre;
	
	@Valid
	@JsonProperty("segundoNombre")
	@Pattern(regexp = "(^[A-Za-záéíóúüñÁÉÍÓÚÑÜ ]+)$", message = "El campo segundoNombre no puede contener caracteres especiales")
    private String segundoNombre;

	@Valid
	@JsonProperty("apellidoPaterno")
	@Pattern(regexp = "(^[A-Za-záéíóúüñÁÉÍÓÚÑÜ ]+)$", message = "El campo apellidoPaterno no puede contener caracteres especiales")
    private String apellidoPaterno;

	@Valid
	@JsonProperty("apellidoMaterno")
	@Pattern(regexp = "(^[A-Za-záéíóúüñÁÉÍÓÚÑÜ ]+)$", message = "El campo apellidoMaterno no puede contener caracteres especiales")
    private String apellidoMaterno;
	
	@Valid
	@JsonProperty("fechaNacimiento")
	@NotNull(message = "El campo fechaNacimiento es obligatorio")
    @Pattern(regexp = "(^[0-9\\/\\-]+)$", message = "El campo fechaNacimiento solo puede contener numeros los caracteres especiales '/' , '-'")
    private String fechaNacimiento;
	
}

