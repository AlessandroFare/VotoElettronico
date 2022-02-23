package com.alessandrofare;

import com.alessandrofare.views.*;
import com.alessandrofare.models.*;
import com.alessandrofare.controllers.*;

import java.io.IOException;
import java.sql.SQLException;
/*
 * Classe Main per l'avvio del sistema
 */
public class VotoElettronico{
	
	
	public static void main(String[] args) throws SQLException, IOException{
		Avvio avvio = new Avvio();
		RegistrazioneUtente registrazione = new RegistrazioneUtente();
		GestioneSistema gestione = new GestioneSistema();
		GestioneCP gestioneCP = new GestioneCP();
		GestioneReferendum gestioneReferendum = new GestioneReferendum();
		RegistrazioneElettore registrazioneElettore = new RegistrazioneElettore();
		Votazione votazione = new Votazione();
		VotazioneCP votazioneCP = new VotazioneCP();
		VotazioneReferendum votazioneReferendum = new VotazioneReferendum();
		VotazioneOrdinale votazioneOrdinale = new VotazioneOrdinale();
		VotazioneCategorico votazioneCategorico = new VotazioneCategorico();
		VotazioneCategoricoPreferenza votazioneCategoricoPreferenza = new VotazioneCategoricoPreferenza();
		VotazioneCategoricoPreferenzaCandidati votazioneCategoricoPreferenzaCandidati = new VotazioneCategoricoPreferenzaCandidati();		
		Statistiche statistiche = new Statistiche();
		StatisticheReferendum statisticheReferendum = new StatisticheReferendum();
		StatisticheCP statisticheCP = new StatisticheCP();
		Controller controller = new Controller(avvio, registrazione, gestione, registrazioneElettore, votazione, votazioneCP, votazioneReferendum,votazioneOrdinale,votazioneCategorico,votazioneCategoricoPreferenza, votazioneCategoricoPreferenzaCandidati, statistiche, statisticheReferendum, statisticheCP, gestioneCP, gestioneReferendum);

		avvio.setVisible(true);
		
	}
}