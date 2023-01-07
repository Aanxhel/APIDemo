package app.java.local.flujos;

class Persona{
	private String name;

	public void mofifyName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

public class ReferenceClass {
	
	
	
	public static void main(String[] args) {

		int i = 10;
		Persona persona = new Persona();
		persona.mofifyName("Andres");
		
		System.out.println(persona.getName());

	}

	

}
