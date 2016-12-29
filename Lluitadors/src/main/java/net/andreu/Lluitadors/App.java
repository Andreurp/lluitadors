package net.andreu.Lluitadors;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * Hello world!
 * 192.168.0.19:8080/Lluitadors
 * localhost:8080/Lluitadors
 */
public class App 
{
	static Processar p = new Processar();
	
	public static void main( String[] args ) throws IOException, ParserConfigurationException, SAXException
    {
    	CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
    	URL url;
		try {
			url = new URL("http://localhost:8080/Lluitadors/llista");
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			InputStream in = new BufferedInputStream(con.getInputStream());
			
			SAXParserFactory fabrica=SAXParserFactory.newInstance();
	        fabrica.setNamespaceAware(true);
	        SAXParser parser=fabrica.newSAXParser();
	        parser.parse(in,p);
	        
	        con.disconnect();
	        
	        boolean surt;
			do{
				System.out.println("--------------------");
				surt = menu();
			}while(surt == false);
			 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	private static boolean menu() throws IOException, ParserConfigurationException, SAXException {
		boolean sortir = false;
		Scanner llegir = new Scanner(System.in);
		
		System.out.println("1. Veure llista lluitadors");
		System.out.println("2. Lluita etre 2");
		System.out.println("3. El mes fort");
		System.out.println("0. Sortir");
		
		int opcio = llegir.nextInt();
		switch (opcio) {
		case 0:
			sortir = true;
			break;
		case 1:
			for(Lluitadors lluitador : p.getListaLluitadors()){
	        	System.out.println(lluitador);
			}
			break;
		case 2:
			lluita();
			break;
		case 3:
			mesFort();
			break;
		}
		return sortir;
	}

	private static void lluita() throws IOException, ParserConfigurationException, SAXException {
		PrecessarResutat pr = new PrecessarResutat();
		Scanner llegir = new Scanner(System.in);
		
		String lluitador1;
		String lluitador2;
		
		System.out.println("Entra el nom del primer lluitador");
		lluitador1 = llegir.nextLine();
		System.out.println("Entra el nom del segon lluitador");
		lluitador2 = llegir.nextLine();
		
		URL url = new URL("http://localhost:8080/Lluitadors/ring/lluita");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		String dades = "nom1=" + lluitador1 + "&nom2=" + lluitador2;
		//String dades = "nom1=AixafaGuitarres&nom2=Matagalls";
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(dades);
		out.flush();
		out.close();
		try{
			InputStream in = new BufferedInputStream(con.getInputStream());
			SAXParserFactory fabrica=SAXParserFactory.newInstance();
	        fabrica.setNamespaceAware(true);
	        SAXParser parser=fabrica.newSAXParser();
	        parser.parse(in,pr);
	        
	        System.out.println("");
	        if(pr.esEmpat()){
	        	System.out.println("Els lluitadors han enpatat");
	        }else{
	        	System.out.println("La victoria ha sigut per: " + pr.getVencedor());
	        	System.out.println("La derrota ha sigut per: " + pr.getPerdedor());
	        }
		}catch(IOException e){
			System.out.println("No existeix");
		}

		con.disconnect();
	}
	
	private static void mesFort() throws IOException, ParserConfigurationException, SAXException {
		
		PrecessarResutat pr = new PrecessarResutat();
		String fort = p.getListaLluitadors().get(0).getNom();
		System.out.println("");
		
		for (Lluitadors ll : p.getListaLluitadors()){
			URL url = new URL("http://localhost:8080/Lluitadors/ring/lluita");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			String dades = "nom1=" + fort + "&nom2=" + ll.getNom();
			//String dades = "nom1=AixafaGuitarres&nom2=Matagalls";
			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			out.writeBytes(dades);
			out.flush();
			out.close();
			
			InputStream in = new BufferedInputStream(con.getInputStream());
			SAXParserFactory fabrica=SAXParserFactory.newInstance();
	        fabrica.setNamespaceAware(true);
	        SAXParser parser=fabrica.newSAXParser();
	        parser.parse(in,pr);
	        
	        if(!pr.esEmpat()){
	        	fort = pr.getVencedor();
	        }			
		}
		System.out.println("El lluitador mes fort es: " + fort);
	}
}
