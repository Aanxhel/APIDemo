package com.proyect.shop.demo;

public enum Colores {
	ROJO("rojo"),
	AZUL("azul"),
	VERDE("verde");

	
	private final String name;
	
	Colores(String nameColor) {
		// TODO Auto-generated constructor stub
		this.name = nameColor;
	}

	public String getName() {
		return name;
	}
	
	

}
