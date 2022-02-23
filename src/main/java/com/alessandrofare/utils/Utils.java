package com.alessandrofare.utils;



import java.text.SimpleDateFormat;
import java.util.Date;

import com.alessandrofare.document.CitiesCodes;
import com.alessandrofare.document.StateCodes;
/*
 * Util utilizzata nella classe Elettore per creare il codice fiscale, per il parse delle date e per il controllo del codice fiscale creato.
 */
public class Utils {

	private static CitiesCodes cc = new CitiesCodes();
	public static StateCodes sc = new StateCodes();
	public static final String ERROR = "#EE#";

	public static CitiesCodes getCitiesCodes(){
		return cc;
	}
	
	public static StateCodes getStateCodes() {
		return sc;
	}
	
	
	public static String parseDate(String data){
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat to = new SimpleDateFormat("dd MMMM yyyy");
		try {
			Date dataDa = from.parse(data);
			return to.format(dataDa);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "!DATE_ERROR";
		}
	}

	public static String parseDate(String data, String da, String a){
		SimpleDateFormat from = new SimpleDateFormat(da);
		SimpleDateFormat to = new SimpleDateFormat(a);
		try {
			Date dataDa = from.parse(data);
			return to.format(dataDa);

		} catch (Exception e) {
			e.printStackTrace();
			return "!DATE_ERROR";
		}
	}
	
	public static String dateToParse(int day, int month, int year){
		return year + "-" + month + "-" + day;
	}
	
	public static String reverseParsing(String data){
		SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat from = new SimpleDateFormat("dd MMMM yyyy");
		try {
			Date dataDa = from.parse(data);
			return to.format(dataDa);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "!DATE_ERROR";
		}
	}
	
	public static String reverseParsing(String data, String formatFrom){
		SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat from = new SimpleDateFormat(formatFrom);
		try {
			Date dataDa = from.parse(data);
			return to.format(dataDa);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "!DATE_ERROR";
		}
	}
	
	public static String validate_regular(String cf)
	{
		if( ! cf.matches("^[0-9A-Z]{16}$") )
			return "Invalid characters.";
		int s = 0;
		String even_map = "BAFHJNPRTVCESULDGIMOQKWZYX";
		for(int i = 0; i < 15; i++){
			int c = cf.charAt(i);
			int n;
			if( '0' <= c && c <= '9' )
				n = c - '0';
			else
				n = c - 'A';
			if( (i & 1) == 0 )
				n = even_map.charAt(n) - 'A';
			s += n;
		}
		if( s%26 + 'A' != cf.charAt(15) )
			return "Invalid checksum.";
		return "CORRETTO";
	}

}
