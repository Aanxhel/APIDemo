package com.dev.Support.Match.beans;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Domicilio {

	@JsonProperty("direccion")
	@NotBlank(message = "El campo Direccion es obligatorio")
	@Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo direccion no puede contener caracteres especiales")
	@Size(min = 3, max = 50)
    private String direccion;
	
	@JsonProperty("colonia")
	@NotBlank(message = "El campo Colonia Poblacion es obligatorio")
	@Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo colonia no puede contener caracteres especiales")
	@Size(min = 3, max = 30)
    private String colonia;
	
	@JsonProperty("ciudad")
	@NotBlank(message = "El campo Ciudad es obligatorio")
	@Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo ciudad no puede contener caracteres especiales")
	@Size(min = 3, max = 20)
    private String ciudad;
	
	@JsonProperty("estado")
	@NotBlank(message = "El campo Estado es obligatorio")
	@Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo estado no puede contener caracteres especiales")
	@Size(min = 3, max = 20)
    private String estado;
	
	@JsonProperty("municipio")
	@NotBlank(message = "El campo Delegacion Municipio es obligatorio")
	@Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo Municipio/Delegación no puede contener caracteres especiales")
	@Size(min = 3, max = 20)
    private String municipio;
	
	@JsonProperty("cp")
	@NotBlank(message = "El campo CP es obligatorio")
	@Pattern(regexp = "(^[0-9]+)$", message = "El campo CP solo puede contener numeros")
	@Size(min = 5,max = 5)
    private String cp;
	
	@JsonProperty("pais")
	@NotBlank(message = "El campo Pais es obligatorio")
	@Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo Pais no puede contener caracteres especiales")
	@Size(min = 3, max = 15)
    private String pais;
	
	@JsonProperty("numeroTelefono")
	@Pattern(regexp = "(^[0-9]+)$", message = "El campo Telefono solo puede contener numeros")
	@Size(min = 7,max = 10)
    private String numeroTelefono;
}
