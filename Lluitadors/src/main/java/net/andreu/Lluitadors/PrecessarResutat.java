package net.andreu.Lluitadors;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PrecessarResutat extends DefaultHandler {
	
	boolean resultat = false;
	boolean victoria = false;
	boolean derrota = false;
	boolean empat=true;

	String vensador;
	String perdedor;

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {

		switch (qName) {
		case "resultat":
			resultat = true;
			empat=true;
			break;
		case "victoria":
			victoria = true;
			empat=false;
			break;
		case "derrota":
			derrota = true;
			empat=false;
			break;
		}
		
	}

	public void characters(char[] ch, int start, int length) {
		if (resultat){
			if (victoria){
				vensador = new String(ch, start, length);
			}else if (derrota){
				perdedor = new String(ch, start, length);
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

	public String getVencedor(){
		return vensador;
	}
	public String getPerdedor(){
		return perdedor;
	}
	public boolean esEmpat(){
		return empat;
	}
	public void endDocument() {

		
	}
}
