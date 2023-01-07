package app.java.local.flujos;

public class AutoboxingIntegrer {
	public static void main(String args[]) {

		Integer[] numersInts = { Integer.valueOf(1), 2, 3, 4, 5, 6 };

		int suma = 0;
		for (Integer i : numersInts) {
			if (i.intValue() % 2 == 0) {
				suma += i.intValue();
			}
		}
		System.out.println("suma =" + suma);
	}
}
