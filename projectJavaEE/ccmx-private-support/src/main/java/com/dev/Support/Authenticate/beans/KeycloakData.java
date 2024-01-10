package com.dev.Support.Authenticate.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KeycloakData {

	@JsonProperty("apellidos")
	private String apellidos;
	@JsonProperty("numeroEmpleado")
	private String numeroEmpleado;
	@JsonProperty("otorganteCanal")
	private String otorganteCanal;
	@JsonProperty("fechaRegistro")
	private String fechaRegistro;
	@JsonProperty("correo")
	private String correo;
	@JsonProperty("fechaActualizacion")
	private String fechaActualizacion;
	@JsonProperty("usuario")
	private String usuario;
	@JsonProperty("nombre")
	private String nombre;
	@JsonProperty("otorgantePadre")
	private String otorgantePadre;
}
