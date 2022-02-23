package com.alessandrofare.views;

import java.util.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.alessandrofare.utils.*;
/*
 * Classe che rappresenta l'interfaccia grafica relativa all'avvio del sistema.
 */
public class Avvio extends JFrame{

	//attributi componenti della finestra
	private JFrame finestra;

	private JLabel titolo = DesignUtils.customLabel("Sistema di voto elettronico");

	private JButton registrazione = DesignUtils.customButton("Registrazione");
	
	private JButton statistiche = DesignUtils.customButton("Statistiche");
	
	//Costruttore
	public Avvio() {
		JPanel pannello = DesignUtils.customPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,150);
		this.setLocationRelativeTo(null);
		//aggiungo gli attributi componenti sul pannello
		pannello.add(titolo);
		pannello.add(registrazione);
		pannello.add(statistiche);
		//aggiungo il pannello con i suoi componenti alla finestra
		this.add(pannello);
		
	}
	//metodo usato dal controller per intercettare il click sul bottone registrazione
	public void ascoltoRegistrazione(ActionListener bottoneRegistrazione){
		registrazione.addActionListener(bottoneRegistrazione);
	}
	//metodo usato dal controller per intercettare il click sul bottone statistiche.
	public void ascoltoStatistiche(ActionListener bottoneStatistiche){
		statistiche.addActionListener(bottoneStatistiche);
	}
	
}