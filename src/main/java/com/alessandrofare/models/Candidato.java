package com.alessandrofare.models;

/*
 * Classe per la creazione di Candidati. 
 * Il candidato ha nome,cognome, nomePartito, numVoti. Nel database è identificato dalle chiavi primarie nome,cognome
 */
public class Candidato {

	private String nome;
	private String cognome;
	private String nomePartito;
	private int numVoti;
	// @ invariant posizione >= 0
	private int posizione;
	private boolean preferenza;
	
	public Candidato(String nome, String cognome, String nomePartito, int numVoti) {
		this.nome = nome;
		this.cognome = cognome;
		this.nomePartito = nomePartito;
		this.numVoti = numVoti;
		this.preferenza = false;
	}
	
	// SETTER
	
	public Candidato() {

	}

	public void setNome(String s){
		this.nome=s;
	}
	
	public void setCognome(String s){
		this.cognome=s;
	}
	
	public void setNomePartito(String nomePartito) {
		this.nomePartito = nomePartito;
	}
	
	public void setNumVoti(int n) {
		this.numVoti = n;
	}
	
	public void setPosizione(int posizione) {
		this.posizione = posizione;
		
	}
	
	public void setPreferenza(boolean f) {
		if(f == true)
			this.preferenza = true;
		else 
			this.preferenza = false;
	}
	
	// GETTER
	
	public String getNome(){
		return nome;
	}
	
	public String getCognome(){
		return cognome;
	}
	
	public String getNomePartito() {
		return nomePartito;
	}

	public int getNumVoti() {
		return numVoti;
	}

	// @ ensures posizione >= 0
	public int getPosizione() {
		return posizione;		
	}
	
	public boolean getPreferenza() {
		return preferenza;
	}
	

}
