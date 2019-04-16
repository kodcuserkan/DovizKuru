package com.proje.dovizTCMB.com.proje.dovizTCMB;


import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class TCMBGuncelKur {

	private static final String Guncel = "http://www.tcmb.gov.tr/kurlar/today.xml";
	
	

	public TCMBGuncelKur(){

	}

	public static void main(String[] args){
		ArrayList<DovizKuru> Kurlar = new TCMBGuncelKur().parseCurrencyResponse(Guncel);
		for (int kur = 0; kur < Kurlar.size(); kur++) {
			System.out.println(Kurlar.get(kur));
		}
	}

	
	private  XMLReader initializeReader(){
	  SAXParserFactory factory = SAXParserFactory.newInstance();
	  try {
		  SAXParser parser = factory.newSAXParser();
		  XMLReader xmlreader = parser.getXMLReader();
		  return xmlreader;
	  }
	  catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
	  }
	  return null;	 	  
    }

	public  ArrayList<DovizKuru> parseCurrencyResponse(String xml) {
		try {
			XMLReader xmlreader = initializeReader();
			GetirKur currencyHandler = new GetirKur();
			xmlreader.setContentHandler(currencyHandler);
			xmlreader.parse(xml);
			return currencyHandler.getCurrencies();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
	    } 
     }

}