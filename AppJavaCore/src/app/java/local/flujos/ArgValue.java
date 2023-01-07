package app.java.local.flujos;

public class ArgValue {

	public static void main(String[] args) {

		int i = 10;

		System.out.println("valor de main: " + i);
		test(i);
		System.out.println("finaliza con: " + i);

	}

	public static void test(int i) {
		System.out.println("Iniciamos el metodo test con i : "+ i);
		i = 35;
		System.out.println("Finaliza el metodo test con i : "+ i);
	}
}
