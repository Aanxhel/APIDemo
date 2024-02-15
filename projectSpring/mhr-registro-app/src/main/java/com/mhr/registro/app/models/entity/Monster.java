package com.mhr.registro.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@Table(name= "mhr_tbl")
public class Monster implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("nombre")
    @Pattern(regexp = "^[A-Za-z0-9\\s]+$", message = "El campo Nombre no puede contener caracteres especiales" )
    @Size(min = 1, max = 50)
	private String nombre;
	
	@JsonProperty("ventaja")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "El campo ventaja no puede contener caracteres especiales" )
    @Size(min = 1, max = 50)
	private String ventaja;
	
	@JsonProperty("desventaja")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "El campo desventaja no puede contener caracteres especiales" )
    @Size(min = 1, max = 50)
	private String desventaja;
	
	@JsonProperty("dificultad")
	private Integer dificultad;
		
}
