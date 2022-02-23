package com.alessandrofare.models;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Classe per la creazione delle varie modalità di vincita.
 * Esistono 2 modalità di vincita, maggioranza e maggioranzaAssoluta, per candidati/partiti.
 * Esistono 2 modalità di vincita, referendumConQuorum e referendumSenzaQuorum, per i referendum.
 */
public class Vincita {
	
	private VincitaType vincitaType;
	private List<Partito> partito;
	private Referendum referendum;
	
	public Vincita() {
		
	}
	
	public Vincita(VincitaType vincitaType) {
		this.vincitaType = vincitaType;
	}
	
	public VincitaType getVincitaType() {
		return vincitaType;
	}
	
	public List<Partito> getPartiti() {
		return partito;
	}
	
	public Referendum getReferendum() {
		return referendum;
	}
	
	public void setVincitaType(VincitaType vincitaType) {
		this.vincitaType = vincitaType;
	}
	public void setPartiti(List<Partito> partito) {
		this.partito = partito;
	}
	
	public void setReferendum(Referendum r) {
		this.referendum = r;
	}
	
	public Candidato vincitore() {
		if(this.vincitaType.equals(VincitaType.maggioranza)) {
			int count = 0;
			int tempi = 0;
			int tempj = 0;
			for(int i=0; i<this.partito.size(); i++) {
				for(int j = 0; j< this.partito.get(i).getCandidati().size(); j++) {
					Candidato c = this.partito.get(i).getCandidati().get(j);
					if(c.getNumVoti() > count) {
						count = c.getNumVoti();
						tempi = i;
						tempj = j;
					}
				}
			}
			return this.partito.get(tempi).getCandidati().get(tempj);
		}
		else if(this.vincitaType.equals(VincitaType.maggioranzaAssoluta)) {
			int count = 0;
			for(int i=0; i<this.partito.size(); i++) {
				for(int j = 0; j< this.partito.get(i).getCandidati().size(); j++) {
					count += this.partito.get(i).getCandidati().get(j).getNumVoti();
				}
			}
			
			for(int i=0; i<this.partito.size(); i++) {
				for(int j = 0; j< this.partito.get(i).getCandidati().size(); j++) {
					Candidato c = this.partito.get(i).getCandidati().get(j);
					if(c.getNumVoti() >= (0.5 * count)) {
						count = c.getNumVoti();
						return c;
					}
				}
			}
			
		}
		
		return null;
	}
	
	public Map<String, Integer> vincitaReferendum() {
		Map<String, Integer> numberMapping = new HashMap<String, Integer>();
		if(this.vincitaType.equals(VincitaType.referendumSenzaQuorum)) {
			double totVotiPositivi = this.referendum.getNumVotiPositivi();
			double totVotiNegativi = this.referendum.getNumVotiNegativi();
			if(totVotiPositivi > totVotiNegativi)
				numberMapping.put("Passata " + totVotiPositivi + " " + totVotiNegativi + " " + this.referendum.getQuorum(), this.referendum.getNumVoti());
			else 
				numberMapping.put("Non passata " + totVotiPositivi + " " + totVotiNegativi + " " + this.referendum.getQuorum(), this.referendum.getNumVoti());
		}
		else if(this.vincitaType.equals(VincitaType.referendumConQuorum)) {
			double totVoti = this.referendum.getNumVoti();
			double totVotiPositivi = this.referendum.getNumVotiPositivi();
			double totVotiNegativi = this.referendum.getNumVotiNegativi();
			
			if((totVotiPositivi/totVoti) >= this.referendum.getQuorum()) {
				numberMapping.put("Passata " + totVotiPositivi + " " + totVotiNegativi + " " + this.referendum.getQuorum(), this.referendum.getNumVoti());
			}
			else {
				numberMapping.put("Non passata " + totVotiPositivi + " " + totVotiNegativi + " " + this.referendum.getQuorum(), this.referendum.getNumVoti());
			}
		}
		return numberMapping;
	}
	
}
