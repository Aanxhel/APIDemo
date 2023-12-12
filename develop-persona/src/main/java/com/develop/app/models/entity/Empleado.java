package com.develop.app.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tbl_empleado")
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true , nullable = false)
	private Integer id;

	private String primerNombre;
	private String segundoNombre;
	private String apelliPat;
	private String apelliMat;
	private Integer edad;
	private String sexo;
	private Date fechaNacimiento;
	private String puesto;

}
