package com.proyect.shop.demo;

public class Car {
	
	private String name;
	private int age;
	private String color;
	
	public static final Integer VELOCIDAD = 10;
	public static final int ACELERAICON = 5;
	
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public Car(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Car(String name, int age, String color) {
		this.name = name;
		this.age = age;
		this.color = color;
	}


	public String detail(String from) {
		
		return "name: "+ name+ " age: " +age + " Fabricnate: " + from;
	}

}
