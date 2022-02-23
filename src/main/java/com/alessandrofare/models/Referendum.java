package com.alessandrofare.models;

/*
 * Classe per la creazione dei referendum.
 * Ogni referendum è composto dalla domanda(proposta), dal quorum, dal numero di voti totale e dal numero di voti positivi/negativi.
 * Se viene dato un voto favorevole il numVoti totale aumenta di uno così come il numVotiPositivi.
 * Se viene dato un voto non favorevole il numVoti totale aumenta di uno così come il numVotiNegativi.
 */

public class Referendum {
	
	private String domanda;
	private boolean favorevole;
	private double quorum;
	private int numVotiPositivi;
	private int numVotiNegativi;
	private int numVoti;
	
	public Referendum(String domanda, double quorum) {
		this.domanda = domanda;
		this.quorum = quorum;
		this.favorevole = false;		
	}
	
	public Referendum(String domanda, boolean favorevole) {
		this.domanda = domanda;
		this.favorevole = favorevole;
	}
	public String getDomanda() {
		return domanda;
	}
	
	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}
	
	public int getNumVotiPositivi() {
		return numVotiPositivi;
	}

	public void setNumVotiPositivi(int numVotiPositivi) {
		this.numVotiPositivi = numVotiPositivi;
	}

	public int getNumVotiNegativi() {
		return numVotiNegativi;
	}

	public void setNumVotiNegativi(int numVotiNegativi) {
		this.numVotiNegativi = numVotiNegativi;
	}

	public int getNumVoti() {
		return numVoti;
	}

	public void setNumVoti(int numVoti) {
		this.numVoti = numVoti;
	}

	public double getQuorum() {
		return quorum;
	}

	public void setQuorum(double quorum) {
		this.quorum = quorum;
	}

	public boolean getFavorevole() {
		return favorevole;
	}
	
	public void setFavorevole(boolean f) {
		this.setNumVoti(this.getNumVoti() + 1);
		if(f == true) {
			this.favorevole = true;
			this.setNumVotiPositivi(this.getNumVotiPositivi() + 1);
		}
		else {
			this.favorevole = false;
			this.setNumVotiNegativi(this.getNumVotiNegativi() + 1);
		}
	}

}
