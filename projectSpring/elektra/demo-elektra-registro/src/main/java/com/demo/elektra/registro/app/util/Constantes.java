package com.demo.elektra.registro.app.util;

import org.springframework.stereotype.Component;

@Component
public class Constantes {

	public static final String REGEX_EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	public static final String REGEX_NUMEROS = "[0-9]*";
	public static final String REGEX_FECHA = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
}
