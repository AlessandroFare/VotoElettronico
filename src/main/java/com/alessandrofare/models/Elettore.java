package com.alessandrofare.models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import com.alessandrofare.utils.Utils;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/*
 * Classe per la creazione dell'elettore. Il codice fiscale viene generato in base ai campi nome,cognome,sesso,giorno,mese,anno,citta,stato
 * Viene controllato se l'elettore ha paese d'origine Italia o un altro paese estero e in base a ciò vengono creati i 4 caratteri di codice corrispondenti a citta e stato dell'elettore.
 * Nel database è identificato dalla chiave primaria code(codice fiscale). Se vota più di una volta countVoto viene aumentato di 1 e non può votare.
*/
public class Elettore extends Utente{

	// @ invariant code.length = 16
	private /*@ spec_public @*/ String code="";
	
	private /*@ spec_public @*/ String cognome="";
	private /*@ spec_public @*/ String nome="";
	private /*@ spec_public @*/ String sesso="";
	private /*@ spec_public @*/ int giorno;
	private /*@ spec_public @*/ int mese;
	private /*@ spec_public @*/ int anno;
	private /*@ spec_public @*/ String nascita;
	private /*@ spec_public @*/ String citta="";
	private /*@ spec_public @*/ String stato="";
	private /*@ spec_public @*/ int countVoto;
	private String consonanti_COGNOME="";
	private String vocali_COGNOME="";
	private String consonanti_NOME="";
	private String vocali_NOME="";
	
	//@public invariant nome != null && cognome !=null;
	
	
	/*@
	 @ requires sesso == 'M' || sesso == 'F';
	 @ requires (Calendar.getInstance().get(Calendar.YEAR) > anno) ||
	 @ 			(Calendar.getInstance().get(Calendar.YEAR) == anno && Calendar.getInstance().get(Calendar.MONTH) > mese ) ||
	 @			(Calendar.getInstance().get(Calendar.YEAR) == anno && Calendar.getInstance().get(Calendar.MONTH) == mese  && Calendar.getInstance().get(Calendar.DAY_OF_MONTH) >=giorno ) ;
	 @ requires stato.equalsIgnoreCase("Italia") ==> citta != null;
	 @ requires (Calendar.getInstance().get(Calendar.YEAR) - anno > 18) ||
	 @         (Calendar.getInstance().get(Calendar.YEAR) - anno == 18) && (Calendar.getInstance().get(Calendar.MONTH) - mese > 0 ) || 
	 @		   (Calendar.getInstance().get(Calendar.YEAR) - anno == 18) && (Calendar.getInstance().get(Calendar.MONTH) - mese == 0 ) && (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - giorno >0);	
 	@*/
	public Elettore(String username,String password, String nome, String cognome, String sesso, int giorno, int mese, int anno, String nascita, String citta, String stato) throws IOException{
		super(username, password);
		isAdmin = false;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
		this.nascita = nascita;
		this.citta = citta;
		this.stato = stato;
		popolazioneStringheConsonantiVocali();
		this.countVoto = 0;
	}
	
	// Costruttore elettore senza giorno,mese,anno ma con direttamente la stringa corrispondente alla data di nascita nel formato dd/MM/yyyy
	public Elettore(String nome, String cognome, String sesso, String nascita, String citta, String stato) throws IOException {
		isAdmin = false;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.nascita = nascita;
		String[] split = nascita.split("/");
		this.giorno = Integer.parseInt(split[0]);
		this.mese = Integer.parseInt(split[1]);
		this.anno = Integer.parseInt(split[2]);
		this.citta = citta;
		this.stato = stato;
		popolazioneStringheConsonantiVocali();
		this.countVoto = 0;
	}
	
	public Elettore() {
		
	}

	
	// SETTER
	
	public void setNome(String s){
		this.nome=s;
	}
	
	public void setCognome(String s){
		this.cognome=s;
	}
	
	public void setSesso(String s){
		this.sesso=s;
	}
	
	public void setGiorno(String d){
		this.giorno=Integer.parseInt(d);
	}
	
	public void setMese(String m){
		this.mese=Integer.parseInt(m);
	}
	
	public void setAnno(String y){
		this.anno=Integer.parseInt(y);
	}
	
	public void setNascita() {
		this.nascita = this.giorno + "/" + this.mese + "/" + this.anno;
	}
	
	public void setNascitaString(String s) {
		this.nascita = s;
	}
	
	public void setCitta(String s){
		this.citta=s;
	}
	
	public void setStato(String s){
		this.stato=s;
	}

	public void setCountVoto(int c) {
		this.countVoto = c;
	}
	
	// Controllo se il codice fiscale è valido o meno. Se non è valido viene ritornato un codice nullo.
	public void setCF(String s) {
		if(Utils.validate_regular(s).equals("CORRETTO")) {
			this.code = s;
		}
		else {
			this.code = "";
			System.out.println("CF non corretto, riprova");
		}
	}
	
	
	// GETTER
	
	public String getNome(){
		return nome;
	}
	
	public String getCognome(){
		return cognome;
	}
	
	public String getSesso(){
		return sesso;
	}
	
	public int getGiorno(){
		return giorno;
	}
	
	public int getMese(){
		return mese;
	}
	
	public int getAnno(){
		return anno;
	}
	
	public String getNascita() {	
		return nascita;
	}
	
	public String getCitta(){
		return citta;
	}
	
	public String getStato(){
		return stato;
	}

	public int getCountVoto() {
		return this.countVoto;
	}
	
	public String getCode(){
		return codiceCitta().equals(Utils.ERROR) ? "*#ERRORE DI CALCOLO#*" : code;
	}
	
	// @ ensures code.length == 16
	public String getCF() {
		return this.code;
	}
	
	
	// METODI
	
	// Genero il codice fiscale --> codiceCognome + codiceNome + codiceData + codiceCitta + codiceStato + controlCode
	public void generateCF() {
		code=codiceCognome()+codiceNome()+codiceData();
		if(stato.equals("Italia")) {
			code+= codiceCitta();
		}
		else {
			code+= codiceStato();
		}
		code+=controlCode(code);
	}

	public boolean checkMaggiorenne() {
		int a = this.anno;
		int m = this.mese;
		int g = this.giorno;
		LocalDate birthdate = LocalDate.of(a,m,g);
		LocalDate now = LocalDate.now();
		long years = ChronoUnit.YEARS.between(birthdate, now);
		
		if(years >= 18)
			return true;
		else return false;
	}
	
	private String controlCode(String s) {
		String c="";
		String char_posPari="";
		String char_posDispari="";
		int counter=0;
		for(int i=0;i<s.length();i++){
			if(i%2==0)
				char_posDispari+=s.charAt(i); //perchè per l'algoritmo la stringa comincia da 1 e non da 0 
			else
				char_posPari+=s.charAt(i);
		}
		for(int i=0;i<char_posDispari.length();i++){
			switch(char_posDispari.charAt(i)){
			case '0': counter+=1;break;
			case '1': counter+=0;break;
			case '2': counter+=5;break;
			case '3': counter+=7;break;
			case '4': counter+=9;break;
			case '5': counter+=13;break;
			case '6': counter+=15;break;
			case '7': counter+=17;break;
			case '8': counter+=19;break;
			case '9': counter+=21;break;
			case 'A': counter+=1;break;
			case 'B': counter+=0;break;
			case 'C': counter+=5;break;
			case 'D': counter+=7;break;
			case 'E': counter+=9;break;
			case 'F': counter+=13;break;
			case 'G': counter+=15;break;
			case 'H': counter+=17;break;
			case 'I': counter+=19;break;
			case 'J': counter+=21;break;
			case 'K': counter+=2;break;
			case 'L': counter+=4;break;
			case 'M': counter+=18;break;
			case 'N': counter+=20;break;
			case 'O': counter+=11;break;
			case 'P': counter+=3;break;
			case 'Q': counter+=6;break;
			case 'R': counter+=8;break;
			case 'S': counter+=12;break;
			case 'T': counter+=14;break;
			case 'U': counter+=16;break;
			case 'V': counter+=10;break;
			case 'W': counter+=22;break;
			case 'X': counter+=25;break;
			case 'Y': counter+=24;break;
			case 'Z': counter+=23;break;
			}
		}
		for(int i=0;i<char_posPari.length();i++){
			switch(char_posPari.charAt(i)){
			case '0': counter+=0;break;
			case '1': counter+=1;break;
			case '2': counter+=2;break;
			case '3': counter+=3;break;
			case '4': counter+=4;break;
			case '5': counter+=5;break;
			case '6': counter+=6;break;
			case '7': counter+=7;break;
			case '8': counter+=8;break;
			case '9': counter+=9;break;
			case 'A': counter+=0;break;
			case 'B': counter+=1;break;
			case 'C': counter+=2;break;
			case 'D': counter+=3;break;
			case 'E': counter+=4;break;
			case 'F': counter+=5;break;
			case 'G': counter+=6;break;
			case 'H': counter+=7;break;
			case 'I': counter+=8;break;
			case 'J': counter+=9;break;
			case 'K': counter+=10;break;
			case 'L': counter+=11;break;
			case 'M': counter+=12;break;
			case 'N': counter+=13;break;
			case 'O': counter+=14;break;
			case 'P': counter+=15;break;
			case 'Q': counter+=16;break;
			case 'R': counter+=17;break;
			case 'S': counter+=18;break;
			case 'T': counter+=19;break;
			case 'U': counter+=20;break;
			case 'V': counter+=21;break;
			case 'W': counter+=22;break;
			case 'X': counter+=23;break;
			case 'Y': counter+=24;break;
			case 'Z': counter+=25;break;
			}
		}
		switch(counter%26){
		case 0: c="A";break;
		case 1: c="B";break;
		case 2: c="C";break;
		case 3: c="D";break;
		case 4: c="E";break;
		case 5: c="F";break;
		case 6: c="G";break;
		case 7: c="H";break;
		case 8: c="I";break;
		case 9: c="J";break;
		case 10: c="K";break;
		case 11: c="L";break;
		case 12: c="M";break;
		case 13: c="N";break;
		case 14: c="O";break;
		case 15: c="P";break;
		case 16: c="Q";break;
		case 17: c="R";break;
		case 18: c="S";break;
		case 19: c="T";break;
		case 20: c="U";break;
		case 21: c="V";break;
		case 22: c="W";break;
		case 23: c="X";break;
		case 24: c="Y";break;
		case 25: c="Z";break;
		}
		return c;
	}
	
	private String codiceCitta() {
        return Utils.getCitiesCodes().getKey(citta);
    }
	
	private String codiceStato() {
		return Utils.getStateCodes().getKey(stato);
	}
	private String codiceData(){
		String s="";
		String annoS=anno+"";
		s=s+annoS.charAt(2)+annoS.charAt(3);
		switch(mese){
		case 1: s+="A";break;
		case 2: s+="B";break;
		case 3: s+="C";break;
		case 4: s+="D";break;
		case 5: s+="E";break;
		case 6: s+="H";break;
		case 7: s+="L";break;
		case 8: s+="M";break;
		case 9: s+="P";break;
		case 10: s+="R";break;
		case 11: s+="S";break;
		case 12: s+="T";break;
		}
		if(sesso=="M"){
			if(giorno<10)
				s+="0"+giorno;
			else
				s+=giorno;
		}
		else{
			s+=(giorno+40);
		}
		return s;
	}
	
	private String codiceNome() {
		String s="";
		if(consonanti_NOME.length()>3){
			s=s+consonanti_NOME.charAt(0)+consonanti_NOME.charAt(2)+consonanti_NOME.charAt(3);
			return s;
		}
		if(consonanti_NOME.length()==3){
			for(int i=0;i<3;i++)
				s=s+consonanti_NOME.charAt(i);
			return s;
		}
		if(consonanti_NOME.length()==2){
			s=s+consonanti_NOME.charAt(0)+consonanti_NOME.charAt(1)+vocali_NOME.charAt(0);
			return s;
		}
		if(consonanti_NOME.length()==1){
			s=s+consonanti_NOME.charAt(0)+vocali_NOME.charAt(0)+vocali_NOME.charAt(1);
			return s;
		}
		else{
			for(int i=0;i<3;i++)
				s=s+vocali_NOME.charAt(i);
			return s;
		}
	}
	
	private String codiceCognome() {
		String s="";
		if(consonanti_COGNOME.length()>=3){
			for(int i=0;i<3;i++)
				s=s+consonanti_COGNOME.charAt(i);
			return s;
		}
		if(consonanti_COGNOME.length()==2){
			s=s+consonanti_COGNOME.charAt(0)+consonanti_COGNOME.charAt(1)+vocali_COGNOME.charAt(0);
			return s;
		}
		if(consonanti_COGNOME.length()==1){
			s=s+consonanti_COGNOME.charAt(0)+vocali_COGNOME.charAt(0)+vocali_COGNOME.charAt(1);
			return s;
		}
		else{
			for(int i=0;i<3;i++)
				s=s+vocali_COGNOME.charAt(i);
			return s;
		}
	}

	private void popolazioneStringheConsonantiVocali() {
//		COGNOME
		for(int i=0;i<cognome.length();i++)
			if(isVocal(cognome.charAt(i)))
				vocali_COGNOME=vocali_COGNOME+cognome.charAt(i);
			else
				if(cognome.charAt(i)!=' ')
					consonanti_COGNOME=consonanti_COGNOME+cognome.charAt(i);
//		NOME
		for(int i=0;i<nome.length();i++)
			if(isVocal(nome.charAt(i)))
				vocali_NOME=vocali_NOME+nome.charAt(i);
			else
				if(nome.charAt(i)!=' ')
					consonanti_NOME=consonanti_NOME+nome.charAt(i);
	}

	private boolean isVocal(char c){
		if(c=='A' || c=='E' || c=='I' || c=='O' || c=='U')
			return true;
		return false;
	}

	
	public String toString() {
		return this.nome + " " + this.cognome + " " + this.sesso + " " + this.nascita + " "  + this.stato + " " + this.code;
	}	
	
}