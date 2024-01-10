package com.dev.Support.Validator.main;

import org.springframework.stereotype.Component;

@Component
public class Validaciones {

	@SuppressWarnings("unused")
	public boolean analizeCodigoPostal(String szip, String edocve) {
		String mensaje = null;
		boolean valid = false;
		
		if(szip != null && edocve != null){
			String _estado = edocve.trim();
			Integer zip;
			zip = new Integer(szip);
			int izip = zip.intValue();
			if ((_estado.equalsIgnoreCase("AGS")) && (izip > 19999) && (izip < 21000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("BC")) && (izip > 20999) && (izip < 23000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("BCS")) && (izip > 22999) && (izip < 24000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("CAMP")) && (izip > 23999) && (izip < 25000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("COAH")) && (izip > 24999) && (izip < 28000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("COL")) && (izip > 27999) && (izip < 29000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("CHIS")) && (izip > 28999) && (izip < 31000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("CHIH")) && (izip > 30999) && (izip < 34000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("DF")) && (izip > 999) && (izip < 17000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("CDMX")) && (izip > 999) && (izip < 17000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("DGO")) && (izip > 33999) && (izip < 36000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("GTO")) && (izip > 35999) && (izip < 39000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("GRO")) && (izip > 38999) && (izip < 42000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("HGO")) && (izip > 41999) && (izip < 44000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("JAL")) && (izip > 43999) && (izip < 50000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("MEX")) && (izip > 49999) && (izip < 58000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("MICH")) && (izip > 57999) && (izip < 62000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("MOR")) && (izip > 61999) && (izip < 63000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("NAY")) && (izip > 62999) && (izip < 64000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("NL")) && (izip > 63999) && (izip < 68000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("OAX")) && (izip > 67999) && (izip < 72000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("PUE")) && (izip > 71999) && (izip < 76000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("QRO")) && (izip > 75999) && (izip < 77000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("QROO")) && (izip > 76999) && (izip < 78000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("SLP")) && (izip > 77999) && (izip < 80000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("SIN")) && (izip > 79999) && (izip < 83000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("SON")) && (izip > 82999) && (izip < 86000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("TAB")) && (izip > 85999) && (izip < 87000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("TAMP")) && (izip > 86999) && (izip < 90000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("TLAX")) && (izip > 89999) && (izip < 91000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("VER")) && (izip > 90999) && (izip < 97000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("YUC")) && (izip > 96999) && (izip < 98000))
				valid = true;
			else if ((_estado.equalsIgnoreCase("ZAC")) && (izip > 97999) && (izip < 100000))
				valid = true;
			else
				valid = false;
		}
		return valid;
	}
}
