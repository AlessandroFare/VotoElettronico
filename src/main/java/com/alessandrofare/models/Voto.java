package com.alessandrofare.models;

/*
 * Classe per la creazione delle varie modalità di voto.
 * Esistono 3 modalità di voto, ordinale,categorico e categoricoPreferenza, per candidati/partiti.
 * Esistono 1 modalità di voto, referendum, per i referendum.
 */
public class Voto {
	
	private VotoType votoType;
	private Partito partito;
	private Candidato candidato;
	private Referendum referendum;
	
	public Voto() {
		
	}
	
	public Voto(VotoType votoType) {
		this.votoType = votoType;
	}
	
	public VotoType getVotoType() {
		return votoType;
	}
	
	public void setVotoType(VotoType votoType) {
		this.votoType = votoType;
	}
	
	public Partito getPartito() {
		return partito;
	}
	
	public void setPartito(Partito p) {
		this.partito = p;
	}
	
	public Candidato getCandidato() {
		return candidato;
	}
	
	public void setCandidato(Candidato c) {
		this.candidato = c;
	}
	
	
	public void votoOrdinalePartito(int posizione) {
		
			this.partito.setPosizione(posizione);	
		
	}
	
	public void votoOrdinaleCandidato(int posizione) {
			this.candidato.setPosizione(posizione);			
	}
	
	public void votoCategorico(Partito p) {
		
			this.partito = p;
			this.partito.setPreferenza(p.getPreferenza());
		
	}
	
	public void votoCategorico(Candidato c) {
		
			this.candidato = c;
			this.candidato.setPreferenza(c.getPreferenza());	
	}
	
	public void votoCategoricoPreferenza(Candidato c, Partito p) {
		
			this.partito = p;
			this.partito.setPreferenza(this.partito.getPreferenza());
			
			for(Candidato ca : p.getCandidati()) {
				if(ca.equals(c)) {
					this.candidato = c;
					this.candidato.setPreferenza(this.partito.getPreferenza());
				}
			}
		
	}
	
	public void votoReferendum(Referendum r, boolean f) {
		this.referendum = r;
		this.referendum.setFavorevole(f);
	}
	
	
}
