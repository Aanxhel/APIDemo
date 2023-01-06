package com.proyect.shop.demo;

import java.util.Arrays;
import java.util.Collections;

public class ExampleArrays {


	public static void main(String[] args) {
		// primitive intsnce
		
		String[] products = {"Alfa","Fox", "Tango","Nectar"};

		Collections.reverse(Arrays.asList(products));
		
		for(int i = 0; i<products.length; i++) {
			System.out.println(products[i]);
		}
	}

}
