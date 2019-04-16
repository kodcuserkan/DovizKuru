package com.proje.dovizTCMB.com.proje.dovizTCMB;


import java.util.ArrayList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;


public class GetirKur extends DefaultHandler{
	
	private ArrayList<DovizKuru> kurlar ;
	private DovizKuru dovizKuru;
	private StringBuffer buffer;


	public GetirKur() {
		kurlar = new ArrayList<DovizKuru>(0);
		buffer = new StringBuffer();
	}
	 @Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		 buffer.setLength(0); 
		 if(qName.equals("Currency")){
			 dovizKuru = new DovizKuru(); 
			 dovizKuru.setCurrencyCode(attributes.getValue("CurrencyCode"));
		 }
	}

	 @Override
	public void endElement(String adres, String ad, String kurAdi)
			throws SAXException {
		// System.out.println("endElm: "+qName+" | "+qName+" Buffer: "+buffer);
		 if(kurAdi.equals("Currency")){
			 kurlar.add(dovizKuru);
		 }else if(kurAdi.equals("Unit")){
			 if(!buffer.toString().isEmpty())
				 dovizKuru.setUnit(Integer.parseInt(buffer.toString()));
		 }else if( kurAdi.equals("CurrencyName")){
			 if(!buffer.toString().isEmpty())
				 dovizKuru.setCurencyName(buffer.toString());
		 }else if( kurAdi.equals("ForexBuying")){
			 if(!buffer.toString().isEmpty())
				 dovizKuru.setForexBuying(Float.parseFloat(buffer.toString()));
		 }else if( kurAdi.equals("ForexSelling")){
			 if(!buffer.toString().isEmpty())
				 dovizKuru.setForexSelling(Float.parseFloat(buffer.toString()));
		 }else if( kurAdi.equals("BanknoteBuying")){
			 if(!buffer.toString().isEmpty())
				 dovizKuru.setBanknoteBuying(Float.parseFloat(buffer.toString()));
		 }else if( kurAdi.equals("BanknoteSelling")){
			 if(!buffer.toString().isEmpty())
				 dovizKuru.setBanknoteSelling(Float.parseFloat(buffer.toString()));
		 }else if( kurAdi.equals("CrossRateUSD")){
			 if(!buffer.toString().isEmpty())
				 dovizKuru.setCrossRateUSD(Float.parseFloat(buffer.toString()));
		 }

	}

	  @Override
	  public void characters(char[] ch, int start, int length) {
	  	  buffer.append(ch, start, length);
	  }

	  public ArrayList<DovizKuru> getCurrencies(){
		  return kurlar;
	  }

}