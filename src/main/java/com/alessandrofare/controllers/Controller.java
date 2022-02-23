package com.alessandrofare.controllers;

import com.alessandrofare.views.*;



import com.alessandrofare.models.*;
import com.alessandrofare.utils.Logger;

import java.util.*;
import java.util.Map.Entry;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.sql.SQLException;

/*
 * Classe che rappresenta il controller nel pattern MVC. 
 */

public class Controller {

	//View 1
	private Avvio avvio;

	//View 2
	private RegistrazioneUtente registrazione;
	
	//View 3
	private GestioneSistema gestione;
	
	//View 4
	private RegistrazioneElettore registrazioneElettore;
	
	//View 5
	private Votazione votazione;
	
	//View 6
	private VotazioneCP votazioneCP;
	
	//View 7
	private VotazioneReferendum votazioneReferendum;
	
	//View 8
	private VotazioneOrdinale votazioneOrdinale;
	
	//View 9
	private VotazioneCategorico votazioneCategorico;
	
	//View 10
	private VotazioneCategoricoPreferenza votazioneCategoricoPreferenza;
	
	//View 11
	private VotazioneCategoricoPreferenzaCandidati votazioneCategoricoPreferenzaCandidati;
	
	//View 12
	private Statistiche statistiche;
	
	//View 13
	private StatisticheReferendum statisticheReferendum;
	
	//View 14
	private StatisticheCP statisticheCP;
	
	//View 15
	private GestioneCP gestioneCP;
	
	//View 16
	private GestioneReferendum gestioneReferendum;
	
	// Logger
	private Logger logger;
	
	// Costruttore
	public Controller(Avvio avvio, RegistrazioneUtente registrazione, GestioneSistema gestione,
			RegistrazioneElettore registrazioneElettore, Votazione votazione, VotazioneCP votazioneCP, 
			VotazioneReferendum votazioneReferendum,VotazioneOrdinale votazioneOrdinale, 
			VotazioneCategorico votazioneCategorico,VotazioneCategoricoPreferenza votazioneCategoricoPreferenza,
			VotazioneCategoricoPreferenzaCandidati votazioneCategoricoPreferenzaCandidati, 
			Statistiche statistiche, StatisticheReferendum statisticheReferendum,
			StatisticheCP statisticheCP, GestioneCP gestioneCP, GestioneReferendum gestioneReferendum){
		this.logger = new Logger();
		this.avvio = avvio;
		this.registrazione = registrazione;
		this.gestione = gestione;
		this.registrazioneElettore = registrazioneElettore;
		this.votazione = votazione;
		this.votazioneCP = votazioneCP;
		this.votazioneReferendum = votazioneReferendum;
		this.votazioneOrdinale = votazioneOrdinale;
		this.votazioneCategorico = votazioneCategorico;
		this.votazioneCategoricoPreferenza = votazioneCategoricoPreferenza;
		this.votazioneCategoricoPreferenzaCandidati = votazioneCategoricoPreferenzaCandidati;
		this.statistiche = statistiche;
		this.statisticheReferendum = statisticheReferendum;
		this.statisticheCP = statisticheCP;
		this.gestioneCP = gestioneCP;
		this.gestioneReferendum = gestioneReferendum;
		this.avvio.ascoltoRegistrazione(new RegistrazioneAscolto());
		this.avvio.ascoltoStatistiche(new StatisticheAscolto());
		this.registrazione.ascoltoInviaDati(new ConfermaDatiAscolto());
		this.registrazioneElettore.ascoltoInviaDati(new ConfermaDatiElettoreAscolto());
		this.votazione.ascoltoCP(new CPVotazioneAscolto());
		this.votazione.ascoltoReferendum(new ReferendumAscolto());
		this.votazioneReferendum.ascoltoInviaDatiReferendum(new ConfermaDatiReferendumAscolto());
		this.votazioneCP.ascoltoInviaDatiCP(new VotazioneElettoreAscolto());
		this.votazioneOrdinale.ascoltoInviaDati(new ConfermaDatiOrdinaleFine());
		this.votazioneOrdinale.ascoltoInviaDatiCandidato(new ConfermaDatiOrdinale());
		this.votazioneCategorico.ascoltoInviaDatiReferendum(new ConfermaDatiCategorico());
		this.votazioneCategoricoPreferenza.ascoltoInviaDati(new ConfermaDatiCategoricoPreferenza());
		this.votazioneCategoricoPreferenzaCandidati.ascoltoInviaDatiCandidato(new ConfermaDatiCategoricoPreferenzaCandidati());
		this.votazioneCategoricoPreferenzaCandidati.ascoltoInviaDati(new ConfermaDatiCategoricoPreferenzaFine());
		this.statistiche.ascoltoCP(new CPAscoltoStat());
		this.statistiche.ascoltoReferendum(new ReferendumAscoltoStat());
		this.statisticheReferendum.ascoltoInviaDatiReferendum(new ConfermaStatReferendumAscolto());
		this.statisticheCP.ascoltoInviaDatiCP(new CPAscolto());
		this.gestione.ascoltoCP(new CPAscoltoGestione());
		this.gestione.ascoltoReferendum(new ReferendumAscoltoGestione());
		this.gestioneCP.ascoltoInviaDatiPartito(new ConfermaDatiCPPartito());
		this.gestioneCP.ascoltoInviaDatiCandidato(new ConfermaDatiCPCandidato());
		this.gestioneCP.ascoltoInviaDati(new ConfermaDatiCP());
		this.gestioneReferendum.ascoltoInviaDati(new ConfermaDatiReferendum());
		
	}
	
	
	//listener che apre la finestra di registrazione di un utente.
	public class RegistrazioneAscolto implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			registrazione.setVisible(true);
			JOptionPane.showMessageDialog(null,"Inserire i dati richiesti per effettuare la registrazione.");
		}
	}
	
	//listener che apre la finestra di statistiche.
	public class StatisticheAscolto implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			statistiche.setVisible(true);
		}
	}
		
	//listener che gestisce la registrazione di un utente.
	public class ConfermaDatiAscolto implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			String username = registrazione.getTxtUsername();
			String password = registrazione.getTxtPassword();
			boolean isAdmin = registrazione.getCheckAdmin();
			boolean isDistanza = registrazione.getCheckDistanza();
			
			Utente u = new Admin(username, password);
			u.checkAdmin();
			
			AdminDao dao = new AdminDao();
			try {
				dao.registra(u);
				System.out.println(u.getIsAdmin());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if((u.getIsAdmin() == true) && (isAdmin == true)) {
				registrazione.setVisible(false);
				gestione.setVisible(true);
				logger.log("L'utente: " + username + " " + password + " si è registrato.\nE' l'admin.");
			}
			else if(isDistanza == true) {
				registrazione.setVisible(false);
				votazione.setVisible(true);
				logger.log("L'utente: " + username + " " + password + " si è registrato.\nNon è l'admin.\nVotazione a Distanza.");
			}
			else {
				registrazioneElettore.setVisible(true);
				registrazione.setVisible(false);
				logger.log("L'utente: " + username + " " + password + " si è registrato.\nNon è l'admin.");
			}
			
			
		}
	}
		
	//listener che gestisce la registrazione di un elettore.
	public class ConfermaDatiElettoreAscolto implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			String nome = registrazioneElettore.getTxtNome().toUpperCase();
			String cognome = registrazioneElettore.getTxtCognome().toUpperCase();
			String sesso = registrazioneElettore.getTxtSesso().toUpperCase();
			String nascita = registrazioneElettore.getTxtNascita();
			String citta = registrazioneElettore.getTxtCitta().toUpperCase();
			String stato = registrazioneElettore.getTxtStato();
			
			try {
				Elettore e = new Elettore(nome,cognome,sesso,nascita,citta,stato);
				e.generateCF();
				
				AdminDao dao = new AdminDao();

				dao.registra(e);
				
				
				
				logger.log("L'elettore si è registrato coi seguenti campi: " + nome + " " + cognome + " " + sesso + " " + nascita + " " + citta + " " + stato + " " + e.getCF());
				
				if(e.checkMaggiorenne() == false) {
					registrazione.setVisible(false);
					gestione.setVisible(false);
					registrazioneElettore.setVisible(false);
					avvio.setVisible(true);
					logger.log("L'elettore non è maggiorenne.");
				}
				
				else if(e.getCountVoto() > 0) {
					
					registrazione.setVisible(false);
					gestione.setVisible(false);
					registrazioneElettore.setVisible(false);
					avvio.setVisible(true);
					logger.log("L'elettore ha già votato.");
				}
				else {
					registrazione.setVisible(false);
					gestione.setVisible(false);
					registrazioneElettore.setVisible(false);
					avvio.setVisible(false);
					votazione.setVisible(true);
					logger.log("L'elettore è pronto a votare");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
	}
	
	//listener che gestisce le statisticheCP
	public class CPAscolto implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			AdminDao dao = new AdminDao();
			String idScheda = statisticheCP.getTxtDomanda();
			int n = 0;
			int count = 0;
			try {
				List<Partito> partiti = dao.allPartitiScheda(idScheda);
				for(Partito p : partiti) {
					List<Candidato> candidati = dao.allPartito(p.getNome());
					p.setCandidati(candidati);
				}
				for(Partito p : partiti) {
					String vincita = dao.selectScheda(p);
					if(vincita == null)
						continue;
					else {
						Vincita v = new Vincita(VincitaType.valueOf(vincita));
						v.setPartiti(partiti);
						Candidato c = v.vincitore();
						if((c!=null) && (count == 0)) {
							JOptionPane.showMessageDialog(null, "ID Scheda: " + p.getIdSchedaP() +"\n\n**** VINCITORE ****\n\n" + c.getNome() + " " + c.getCognome() + " " + c.getNomePartito() + " " + c.getNumVoti());
							n++;
							count++;
							logger.log("ID Scheda: " + p.getIdSchedaP() +"  **** VINCITORE ****  " + c.getNome() + " " + c.getCognome() + " " + c.getNomePartito() + " " + c.getNumVoti());
						}	
						else 
							continue;
						
					}
				}
				if(n==0) {
					JOptionPane.showMessageDialog(null, "NESSUN VINCITORE");
					logger.log("NESSUN VINCITORE.");
				}
				
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			statistiche.setVisible(false);
			registrazione.setVisible(false);
			gestione.setVisible(false);
			registrazioneElettore.setVisible(false);
			statistiche.setVisible(false);
			statisticheCP.setVisible(false);
			avvio.setVisible(true);						
		}
		
	}
		
	//listener che apre la finestra per la votazione del referendum
	public class ReferendumAscolto implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			votazioneReferendum.setVisible(true);
			
		}
		
	}
	
	//listener che apre la finestra per la votazione dei candidati/partiti
	public class CPVotazioneAscolto implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			votazioneCP.setVisible(true);						
		}
		
	}
	
	//listener che gestisce la votazione del referendum
	public class ConfermaDatiReferendumAscolto implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String domanda = votazioneReferendum.getTxtDomanda();
			boolean favorevole = votazioneReferendum.getCheckFavorevole();
			AdminDao dao = new AdminDao();
			Referendum r = new Referendum(domanda, favorevole);
			r.setFavorevole(favorevole);
			dao.updateReferendum(r);
			registrazione.setVisible(false);
			gestione.setVisible(false);
			registrazioneElettore.setVisible(false);
			votazione.setVisible(false);
			votazioneReferendum.setVisible(false);
			avvio.setVisible(true);
			if(favorevole)
				logger.log("Votazione referendum su: " + domanda + "\nFavorevole? SI");
			else
				logger.log("Votazione referendum su: " + domanda + "\nFavorevole? NO");
		}
	}
				
	//listener che apre la finestra per le statistiche del referendum		
	public class ReferendumAscoltoStat implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			statisticheReferendum.setVisible(true);
			
		}
		
	}
	
	//listener che apre la finestra per le statistiche dei candidati
	public class CPAscoltoStat implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			statisticheCP.setVisible(true);
			
		}
		
	}
		
	//listener che gestisce le statistiche del referendum
	public class ConfermaStatReferendumAscolto implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String domanda = statisticheReferendum.getTxtDomanda();
			AdminDao dao = new AdminDao();
			Map<String,Integer> result = dao.selectReferendum(domanda);
			String risultato = "";
			int n = 0;
			if(result == null) {
				JOptionPane.showMessageDialog(null, "Nessun referendum!");
				logger.log("NESSUN REFERENDUM!");
			}
			else {
				for (Entry<String, Integer> entry : result.entrySet()) {
					risultato = entry.getKey();
					n = entry.getValue();
				}
				String split[] = risultato.split(" ");
				if(split[0].length() == 3) {
					JOptionPane.showMessageDialog(null, split[0] + " " + split[1] + "\nTot voti: " + n + "\nPositivi: " + split[2] + " | Negativi: " + split[3] + "\nQuroum: " + split[4]);
					logger.log(split[0] + " " + split[1] + "\nTot voti: " + n + "\nPositivi: " + split[2] + " | Negativi: " + split[3] + "\nQuroum: " + split[4]);
				}
				else {
					JOptionPane.showMessageDialog(null, split[0] + "\nTot voti: " + n + "\nPositivi: " + split[1] + " | Negativi: " + split[2] + "\nQuroum: " + split[3]);
					logger.log(split[0] + "\nTot voti: " + n + "\nPositivi: " + split[1] + " | Negativi: " + split[2] + "\nQuroum: " + split[3]);
				}
			}
			statistiche.setVisible(false);
			statisticheReferendum.setVisible(false);
			registrazione.setVisible(false);
			gestione.setVisible(false);
			registrazioneElettore.setVisible(false);
			avvio.setVisible(true);
		}
	}
		
	//listener che apre la finestra per la gestione del referendum
	public class ReferendumAscoltoGestione implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			gestioneReferendum.setVisible(true);
			
		}
		
	}
	
	//listener che apre la finestra per la gestione dei candidati e partiti
	public class CPAscoltoGestione implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			gestioneCP.setVisible(true);
			
		}
		
	}
		
	//listener che gestisce l'inserimento dei partiti nel db
	public class ConfermaDatiCPPartito implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {

			try {
				String nomePartito = gestioneCP.getTxtPartito();
				AdminDao dao = new AdminDao();
				dao.addPartito(nomePartito);
				logger.log("Aggiunto partito: " + nomePartito);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
			
	//listener che gestisce l'inserimento dei candidati nel db
	public class ConfermaDatiCPCandidato implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {

			try {
				String nome = gestioneCP.getTxtCandidato();
				String cognome = gestioneCP.getTxtCognome();
				String nomePartito = gestioneCP.getTxtNomePartito();
				AdminDao dao = new AdminDao();
				dao.addCandidato(nome, cognome, nomePartito);
				logger.log("Aggiunto candidato: " + nome + " " + cognome + " " + nomePartito);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
			
	//listener che gestisce l'inserimento delle schede elettorali(con candidati e partiti) nel db
	public class ConfermaDatiCP implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {

			try {
				String voto = gestioneCP.getTxtVoto();
				String vincita = gestioneCP.getTxtVincita();
				AdminDao dao = new AdminDao();
				dao.addScheda(voto, vincita);
				logger.log("Aggiunta scheda:\nVoto: " + voto + " | Vincita: " + vincita);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			registrazione.setVisible(false);
			gestione.setVisible(false);
			gestioneCP.setVisible(false);
			registrazioneElettore.setVisible(false);
			avvio.setVisible(true);
		}
	}
			
	//listener che gestisce l'inserimento delle schede elettorali(con referendum) e del referendum nel db
	public class ConfermaDatiReferendum implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {

			try {
				String vincita = gestioneReferendum.getTxtVincita();
				String domanda = gestioneReferendum.getTxtProposta();
				String quorum = gestioneReferendum.getQuorum();
				AdminDao dao = new AdminDao();
				dao.addScheda(vincita, domanda, quorum);
				logger.log("Aggiunta scheda:\nDomanda: " + domanda + " | Vincita: " + vincita + " | Quorum: " + quorum);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			registrazione.setVisible(false);
			gestione.setVisible(false);
			gestioneReferendum.setVisible(false);
			registrazioneElettore.setVisible(false);
			avvio.setVisible(true);
		}
	}
			
	//listener che apre la finestra per le votazioni di candidati/partiti in base alla modalità di voto
	public class VotazioneElettoreAscolto implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				String idScheda = votazioneCP.getTxtDomanda();
				AdminDao dao = new AdminDao();
				String voto = dao.selectSchedaVoto(idScheda);
				if(voto.equals("ordinale")) {
					votazioneOrdinale.setVisible(true);
				}
				else if(voto.equals("categorico")) {
					votazioneCategorico.setVisible(true);
				}
				else
					votazioneCategoricoPreferenza.setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	
	//listener che gestisce la votazione ordinale			
	public class ConfermaDatiOrdinale implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String posizione = votazioneOrdinale.getTxtPosizione();
			String candidato = votazioneOrdinale.getTxtVoto();
			String[] split = candidato.split(" ");
			String nome = split[0];
			String cognome = split[1];
			AdminDao dao = new AdminDao();
			dao.updateCandidatoOrdinale(nome, cognome, posizione);
			logger.log("Votazione ordinale:\nScelto candidato: " + nome + " " + cognome + "\nCon posizione: " + posizione);
		}
		
	}
		
	//listener che apre la finestra di avvio e chiude tutte le altre aperte
	public class ConfermaDatiOrdinaleFine implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			registrazione.setVisible(false);
			gestione.setVisible(false);
			gestioneReferendum.setVisible(false);
			registrazioneElettore.setVisible(false);
			votazione.setVisible(false);
			votazioneOrdinale.setVisible(false);
			votazioneCP.setVisible(false);
			avvio.setVisible(true);
			
		}
		
	}
		
	//listener che gestisce la votazione categorico
	public class ConfermaDatiCategorico implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				//String idScheda = votazioneCP.getTxtDomanda();
				String candidato = votazioneCategorico.getTxtDomanda();
				String[] split = candidato.split(" ");
				String nome = split[0];
				String cognome = split[1];
				AdminDao dao = new AdminDao();
				dao.updateCandidato(nome, cognome);
				logger.log("Votazione categorico:\nScelto candidato: " + nome + " " + cognome);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			registrazione.setVisible(false);
			gestione.setVisible(false);
			gestioneReferendum.setVisible(false);
			registrazioneElettore.setVisible(false);
			votazione.setVisible(false);
			votazioneCategorico.setVisible(false);
			votazioneCP.setVisible(false);
			avvio.setVisible(true);
		}
		
	}
		
	//listener che apre la finestra di votazione categorico preferenza
	public class ConfermaDatiCategoricoPreferenza implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			votazioneCategoricoPreferenzaCandidati.setVisible(true);				
		}
		
	}
		
	//listener che gestisce la votazione categorico con preferenza per i candidati
	public class ConfermaDatiCategoricoPreferenzaCandidati implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				AdminDao dao = new AdminDao();
				String partito = votazioneCategoricoPreferenza.getTxtVoto();
				dao.updateCandidatoPartito(partito);
				String candidato = votazioneCategoricoPreferenzaCandidati.getTxtVoto();
				String[] split = candidato.split(" ");
				String nome = split[0];
				String cognome = split[1];
				dao.updateCandidato(nome, cognome);
				logger.log("Votazione categorico con preferenza:\nScelto candidato: " + nome + " " + cognome + "\nScelto partito: " + partito);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		}
					
	}
				
	//listener che apre la finestra di avvio e chiude tutte le altre aperte
	public class ConfermaDatiCategoricoPreferenzaFine implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			registrazione.setVisible(false);
			gestione.setVisible(false);
			gestioneReferendum.setVisible(true);
			registrazioneElettore.setVisible(false);
			avvio.setVisible(true);
	
		}
		
	}
				
				
}
