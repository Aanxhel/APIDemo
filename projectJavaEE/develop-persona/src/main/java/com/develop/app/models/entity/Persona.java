package com.develop.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "persona_tbl")
public class Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	@Column(name = "apellido_paterno")
	private String apellPat;
	
	@Column(name = "apellido_materno")
	private String apellMat;
	
	private Integer edad;
	
	private String sexo;
	
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;
	
	private String puesto;

}