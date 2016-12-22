package net.andreu.Lluitadors;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Processar extends DefaultHandler {

	List<Lluitadors> ListaLluitadors = new ArrayList<Lluitadors>();
	Lluitadors ll;

	boolean lluitador = false;
	boolean nom = false;
	boolean victories = false;
	boolean derrotes = false;
	boolean empats = false;

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {

		switch (qName) {
		case "lluitador":
			lluitador = true;
			break;
		case "nom":
			nom = true;
			break;
		case "victories":
			victories = true;
			break;
		case "derrotes":
			derrotes = true;
			break;
		case "empats":
			empats = true;
		}

	}

	public void characters(char[] ch, int start, int length) {

		if (lluitador){
			if (nom){
				ll = new Lluitadors();
				ll.setNom(new String(ch, start, length));	
			}else if (victories){
				ll.setVictories(Integer.parseInt(new String(ch, start, length)));
			}else if (derrotes){
				ll.setDerrotes(Integer.parseInt(new String(ch, start, length)));
			}else if (empats){
				ll.setEmpats(Integer.parseInt(new String(ch, start, length)));
			}
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		switch (qName) {
		case "lluitador":
			lluitador = false;
			ListaLluitadors.add(ll);
			break;
		case "nom":
			nom = false;
			break;
		case "victories":
			victories = false;
			break;
		case "derrotes":
			derrotes = false;
			break;
		case "empats":
			empats = false;
		}
		
	}

	public List<Lluitadors> getListaLluitadors() {
		return ListaLluitadors;
	}

	public void endDocument() {

		
	}
}
