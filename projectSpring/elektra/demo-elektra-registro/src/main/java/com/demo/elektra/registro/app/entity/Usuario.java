package com.demo.elektra.registro.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "usuario_tbl")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Debes especificar el nombre")
	@Column(unique = true)
	private String nombre;

	@NotNull(message = "Debes especificar el Apellido paterno")
	@Column(unique = true)
	private String apellidoPaterno;

	@Column(unique = true)
	private String apellidoMaterno;
	@Column(unique = true)
	private String fullName;
	private Date fechaNacimiento;// formato yyyy-mm-dd
	private String sexo;
	private String correo;
	private String telefono;
}
