package app.java.local.flujos;

public class WrapperIntegrer {
	public static void main(String args[]) {
		
		int intPrimitive = 3245;
		Integer intObject = Integer.valueOf(124234); //input
		Integer intObjectFromPrimitive = intPrimitive;
		
		//llamar el objeto Integrer
		int numberInvouc = intObjectFromPrimitive.intValue();//output
		System.out.println(numberInvouc);
		
		
		//como pasar de un string a int
		String valueStrinToint = "86742";
		Integer valueCreate = Integer.valueOf(valueStrinToint);
		System.out.println(valueCreate);
	}
}
