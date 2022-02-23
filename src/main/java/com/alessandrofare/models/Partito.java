package com.alessandrofare.models;

import java.util.ArrayList;
import java.util.List;

/*
 * Classe per la creazione dei partiti. 
 * Ogni partito è identificato da un nome, una lista di candidati e l'id della scheda elettorale.
 */
public class Partito {

	private String nome;
	private List<Candidato> candidati = new ArrayList<Candidato>();
	// @ invariant posizione >= 0
	private int posizione;
	private boolean preferenza;
	private int idSchedaP;
	
	public Partito(String nome, List<Candidato> candidati) {
		this.nome = nome;
		this.candidati = candidati;
		// TODO Auto-generated constructor stub
		this.preferenza = false;
	}
	
	public Partito(String nome) {
		this.nome = nome;
		// TODO Auto-generated constructor stub
	}
	
	public Partito() {
		
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Candidato> getCandidati() {
		return candidati;
	}
	
	public void setCandidati(List<Candidato> candidati) {
		this.candidati = candidati;
	}
	
	public void addCandidato(Candidato c) {
		this.candidati.add(c);
	}
	
	// @ ensures posizione >= 0
	public int getPosizione() {
		return posizione;
	}
	
	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}
	
	public boolean getPreferenza() {
		return preferenza;
	}
	
	public void setPreferenza(boolean f) {
		if(f == true)
			this.preferenza = true;
		else 
			this.preferenza = false;
	}

	public int getIdSchedaP() {
		return idSchedaP;
	}

	public void setIdSchedaP(int idSchedaP) {
		this.idSchedaP = idSchedaP;
	}
}
