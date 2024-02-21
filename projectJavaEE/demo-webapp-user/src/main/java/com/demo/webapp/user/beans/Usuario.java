package com.demo.webapp.user.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

	private String name;
	private String lastName;
	private String dateBorn;
	private Integer age;
}
