package com.alessandrofare.views;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.alessandrofare.utils.DesignUtils;

/*
 * Classe che rappresenta l'interfaccia grafica relativa alle statistiche.
 */
public class Statistiche extends JFrame{

	private JLabel lbcp =  DesignUtils.customLabel("Candidato-Partito");

	private JLabel lbreferendum =  DesignUtils.customLabel("Referendum");

	private JButton btcp =  DesignUtils.customButton("Vedi");

	private JButton btreferendum =  DesignUtils.customButton("Vedi");

	//costruttore
	public Statistiche() {
		JPanel pannello =  DesignUtils.customPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,150);
		this.setLocationRelativeTo(null);
		//aggiungo gli attributi componenti sul pannello
		pannello.add(lbcp);
		pannello.add(btcp);
		pannello.add(lbreferendum);
		pannello.add(btreferendum);
		//aggiungo il pannello con i suoi componenti alla finestra
		this.add(pannello);
	}
	
	//metodo usato dal controller per intercettare il click sul bottone btcp.
	public void ascoltoCP(ActionListener bottoneCP){
		btcp.addActionListener(bottoneCP);
	}
	//metodo usato dal controller per intercettare il click sul bottone btreferendum.
	public void ascoltoReferendum(ActionListener bottoneReferendum){
		btreferendum.addActionListener(bottoneReferendum);
	}
	
}

