package com.cdc.fis2022.beans;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


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
public class Persona {

	
	    @JsonProperty("primerNombre")
	    @Pattern(
	        regexp = "(^[A-Za-z0-9]+)$",
	        message = "El campo Primer Nombre no puede contener caracteres especiales"
	    )
	    @Size(min = 3, max = 50)
	    private String primerNombre;
	    @JsonProperty("segundoNombre")
	    @Pattern(
	        regexp = "(^[A-Za-z0-9]+)$",
	        message = "El campo Segundo Nombre no puede contener caracteres especiales"
	    )
	    @Size(min = 3, max = 50)
	    private String segundoNombre;
	    @JsonProperty("apellidoPaterno")
	    @Pattern(
	        regexp = "(^[A-Za-z0-9]+)$",
	        message = "El campo Apellido Paterno no puede contener caracteres especiales"
	    )
	    @Size(min = 3, max = 50)
	    private String apellidoPaterno;
	    @JsonProperty("apellidoMaterno")
	    @NotBlank(
	        message = "El campo Apellido Materno es obligatorio"
	    )
	    @Pattern(
	        regexp = "(^[A-Za-z0-9]+)$",
	        message = "El campo Apellido Materno no puede contener caracteres especiales"
	    )
	    @Size(min = 3, max = 50)
	    private String apellidoMaterno;
	    @JsonProperty("fechaNacimiento")
	    @Pattern(
	        regexp = "(^[0-9\\/\\-]+)$",
	        message = "El campo Fecha Nacimiento solo puede contener numeros los caracteres especiales '/' , '-'"
	    )
	    private String fechaNacimiento;
	   
	    @JsonProperty("rfc")
	    @Pattern(
	        regexp = "(^[A-Za-z0-9]+)$",
	        message = "El campo RFC no puede contener caracteres especiales"
	    )
	    @Size(min = 10, max = 13)
	    private String rfc;
	    @JsonProperty("curp")
	    @Pattern(regexp = "(^[A-Za-z0-9]+)$",message = "El campo CURP no puede contener caracteres especiales")
	    @Size(min = 18,max = 18)
	    private String curp;

	  
	    
	  //CAMPOS OBLIGATORIOS
		@JsonProperty("pais")
		@NotBlank(message = "El campo Pais es obligatorio")
		@Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo Pais no puede contener caracteres especiales")
		@Size(min = 3, max = 15)
		public String pais;

		@JsonProperty("estado")
		@NotBlank(message = "El campo Estado es obligatorio")
		@Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo Estado no puede contener caracteres especiales")
		@Size(min = 3, max = 20)
		public String estado;

		@JsonProperty("delegacionMunicipio")
		@NotBlank(message = "El campo Delegacion Municipio es obligatorio")
		@Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo DelegacionMunicipio no puede contener caracteres especiales")
		@Size(min = 3, max = 20)
		public String municipio;

		@JsonProperty("ciudad")
		@NotBlank(message = "El campo Ciudad es obligatorio")
		@Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo Ciudad no puede contener caracteres especiales")
		@Size(min = 3, max = 20)
		public String ciudad;

		@JsonProperty("coloniaPoblacion")
		@NotBlank(message = "El campo Colonia Poblacion es obligatorio")
		@Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo ColoniaPoblacion no puede contener caracteres especiales")
		@Size(min = 3, max = 30)
		public String colonia;

		@JsonProperty("direccion")
		@NotBlank(message = "El campo Direccion es obligatorio")
		@Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El Direccion pais no puede contener caracteres especiales")
		@Size(min = 3, max = 50)
		public String calle;

		@JsonProperty("cp")
		@NotBlank(message = "El campo CP es obligatorio")
		@Pattern(regexp = "(^[0-9]+)$", message = "El campo CP solo puede contener numeros")
		@Size(min = 5,max = 5)
		public String cp;
		
}
	    
	    