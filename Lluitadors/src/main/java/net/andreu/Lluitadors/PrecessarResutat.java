package net.andreu.Lluitadors;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PrecessarResutat extends DefaultHandler {
	
	boolean resultat = false;
	boolean victoria = false;
	boolean derrota = false;
	
	String vensador;
	String perdedor;

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {
		
		switch (qName) {
		case "resultat":
			resultat = true;
			break;
		case "victoria":
			victoria = true;
			break;
		case "derrota":
			derrota = true;
			break;
		}
		
	}

	public void characters(char[] ch, int start, int length) {
		if (resultat){
			if (victoria){
				vensador = new String(ch, start, length);
				System.out.println("");
				System.out.println("La victoria a sigut per: " + vensador);
			}else if (derrota){
				perdedor = new String(ch, start, length);
				System.out.println("La derrota a sigut per: " + perdedor);
			}else{
				System.out.println("Els lluitadors en enpatat");
			}
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		switch (qName) {
		case "resultat":
			resultat = false;
			break;
		case "victoria":
			victoria = false;
			break;
		case "derrota":
			derrota = false;
			break;
		}
	}

	public void endDocument() {

		
	}
}
