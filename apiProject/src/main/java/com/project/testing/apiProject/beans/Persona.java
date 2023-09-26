package com.project.testing.apiProject.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Persona {
	@JsonProperty("nombre")
	public String nombre;
	
	@JsonProperty("edad")
	public Integer edad;
}
