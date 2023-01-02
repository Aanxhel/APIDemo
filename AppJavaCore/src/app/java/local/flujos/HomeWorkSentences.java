package app.java.local.flujos;

import java.util.Scanner;

public class HomeWorkSentences {
	public static void main(String args[]) {

		Scanner sc = new Scanner(System.in);

		int[] valuesIntegrers = new int[10];

		int valueIn;

		for (int i = 0; i < 10; i++) {
			System.out.println("add [" + i + "] value: ");
			valueIn = sc.nextInt();
			valuesIntegrers[i] = valueIn;
		}

		System.out.println("=========================for:==============================");

		for (int valuesIntegrer : valuesIntegrers) {
			if (valuesIntegrer < 10) {
				System.out.println("[" + valuesIntegrer + "]es menor que 10!");
			} else if(valuesIntegrer >= 10){
				System.out.println("[" + valuesIntegrer + "] es mayor o igual a 10!");
			}else {
				System.out.println("Valor no definido");
			}

		}
		
		System.out.println("======================for;;=================================");
		for(int i =0; i < valuesIntegrers.length; i++) {
			System.out.println("[" + valuesIntegrers[i] );
		}

	}
}
