package com.alessandrofare.models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Classe che rappresenta il punto di collegamento fra voto,vincita,referendum e partiti (con le relative liste di candidati).
 * Ogni scheda è identificata da un id.
 * Esiste un costruttore per il referendum e uno per i partiti e candidati.
 */
public class SchedaElettorale {

	private Voto voto;
	private Vincita vincita;
	private Partito partito;
	private Referendum referendum;
	private int idScheda;
	
	public SchedaElettorale(int idScheda, Voto voto, Vincita vincita, Partito partito) {
		this.idScheda = idScheda;
		this.setVoto(voto);
		this.setVincita(vincita);
		this.partito = partito;
	}
	
	public SchedaElettorale(Voto voto, Vincita vincita, Referendum referendum) {
		this.voto = voto;
		this.vincita = vincita;
		this.referendum = referendum;
	}
	
	public SchedaElettorale() {
		
	}

	public int getIdScheda() {
		return idScheda;
	}

	public void setIdScheda(int idScheda) {
		this.idScheda = idScheda;
	}

	public Voto getVoto() {
		return voto;
	}

	public void setVoto(Voto voto) {
		this.voto = voto;
	}

	public Vincita getVincita() {
		return vincita;
	}

	public void setVincita(Vincita vincita) {
		this.vincita = vincita;
	}

	public Referendum getReferendum() {
		return referendum;
	}

	public void setReferendum(Referendum referendum) {
		this.referendum = referendum;
	}

	public Partito getPartiti() {
		return partito;
	}
	
	public void setPartito(Partito partito) {
		this.partito = partito;
	}
	
	
}
