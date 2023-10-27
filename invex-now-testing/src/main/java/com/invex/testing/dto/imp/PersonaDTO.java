package com.invex.testing.dto.imp;

import java.util.Date;

import com.invex.testing.dto.ValidacionConsultaDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PersonaDTO implements ValidacionConsultaDto{
	
	
	private String primerNombre;
	
	private String segundoNombre;
	
	private String apellidoPaterno;
	
	private String apellidoMaterno;
	
	private Integer edad;
	
	private String sexo;
	
	private Date fechaNacimiento; //dd-mm-aaaa
	
	private String puesto;

}
