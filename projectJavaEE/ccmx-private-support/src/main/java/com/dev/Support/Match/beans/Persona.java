package com.dev.Support.Match.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import javax.validation.constraints.*;

@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    @JsonProperty("primerNombre")
    @NotBlank(message = "El campo Apellido Paterno es obligatorio")
    @Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo Primer Nombre no puede contener caracteres especiales")
    @Size(min = 3, max = 50)
    private String primerNombre;
    
    @JsonProperty("segundoNombre")
    @Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo Segundo Nombre no puede contener caracteres especiales")
    @Size(min = 3, max = 50)
    private String segundoNombre;

    @JsonProperty("apellidoPaterno")
    @NotBlank(message = "El campo Apellido Paterno es obligatorio")
    @Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo Apellido Paterno no puede contener caracteres especiales")
    @Size(min = 3, max = 50)
    private String apellidoPaterno;

    @JsonProperty("apellidoMaterno")
    @NotBlank(message = "El campo Apellido Materno es obligatorio")
    @Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo Apellido Materno no puede contener caracteres especiales")
    @Size(min = 3, max = 50)
    private String apellidoMaterno;
    
    @JsonProperty("apellidoAdicional")
    @Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo Apellido Adicional no puede contener caracteres especiales")
    @Size(min = 3, max = 50)
    private String apellidoAdicional;

    @JsonProperty("fechaNacimiento")
    @Pattern(regexp = "(^[0-9\\/\\-]+)$", message = "El campo Fecha Nacimiento solo puede contener numeros los caracteres especiales '/' , '-'")
    private String fechaNacimiento;
    
	@JsonProperty("RFC")
    @Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo RFC no puede contener caracteres especiales")
    @Size(min = 10, max = 13)
    private String rfc;
	
	@JsonProperty("CURP")
    @Pattern(regexp = "(^[A-Za-z0-9]+)$", message = "El campo CURP no puede contener caracteres especiales")
    @Size(min = 18, max = 18)
    private String curp;
	
    @Email
    @Size(min = 6, max = 50)
    @Pattern(regexp = "(^[A-Za-z0-9-_\\@\\.]+)$", message = "El campo correo no puede contener caracteres especiales mas que @ y .")
	@JsonProperty("correo")
    private String correo;
    
    @JsonProperty("domicilio")
    private Domicilio domicilio;

}
