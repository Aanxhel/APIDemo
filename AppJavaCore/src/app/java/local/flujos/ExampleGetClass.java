package app.java.local.flujos;

import java.lang.reflect.Method;

public class ExampleGetClass {

	public static void main(String[] args) {

		String wrait = "Hello wordl!";

		Class strClass = wrait.getClass();

		System.out.println(strClass.getName());
		System.out.println(strClass.getSimpleName());
		System.out.println(strClass.getPackage());
		
		for(Method metodo: strClass.getMethods()){
			System.out.println(metodo.getName());
		}
	}

}
