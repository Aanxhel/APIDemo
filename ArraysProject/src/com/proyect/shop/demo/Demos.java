package com.proyect.shop.demo;

import java.util.Scanner;

public class Demos {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		int t = in.nextInt();

		int aux = 0;
		int potencia = 0;
		for (int i = 0; i < t; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int n = in.nextInt();

			for (int j = 0; j < n; j++) {
				potencia = (int) (Math.pow(t, j));
				
				System.out.print(a+ potencia * b + aux + " ");
				
				aux = (int) ( Math.pow(t, j) * b) + aux;

			}

		}
		in.close();
		
		
		

	}

}
