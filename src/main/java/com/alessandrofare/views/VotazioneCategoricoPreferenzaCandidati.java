package com.alessandrofare.views;

import java.util.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;

import com.alessandrofare.models.AdminDao;
import com.alessandrofare.utils.DesignUtils;
import com.toedter.calendar.*;

/*
 * Classe che rappresenta l'interfaccia grafica relativa alla votazione categorico con preferenza per dare preferenza a 
 * uno o più candidati.
 */
public class VotazioneCategoricoPreferenzaCandidati extends JFrame{

	//attributi componenti della finestra
	private JLabel lbpartito = DesignUtils.customLabel("Candidato");
	
	private JButton btn_inviaCandidato = DesignUtils.customButton("insert");

	private JButton btn_invia = DesignUtils.customButton("conferma");
	
	private JComboBox<String> jList1 = new JComboBox<String>();
	
	
	//costruttore
	public VotazioneCategoricoPreferenzaCandidati() {
		AdminDao dao = new AdminDao();
		List<String> list1 = dao.listCandidati();
	
		for (int i = 0; i < list1.size(); i++)
		{
		    this.jList1.addItem(list1.get(i));
		}
		
		JPanel pannello = DesignUtils.customPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,200);
		this.setLocationRelativeTo(null);
		//aggiungo gli attributi componenti sul pannello
		pannello.add(lbpartito);
		pannello.add(jList1);
		pannello.add(btn_inviaCandidato);
		pannello.add(btn_invia);
		//aggiungo il pannello con i suoi componenti alla finestra
		this.add(pannello);
	}
	
	public String getTxtVoto(){
		return (String) this.jList1.getSelectedItem();
	}
	
	
	//metodo usato dal controller per intercettare il click sul bottone per confermare i dati inseriti.
	public void ascoltoInviaDatiCandidato(ActionListener bottoneInvia){
		btn_inviaCandidato.addActionListener(bottoneInvia);
	}
	
	//metodo usato dal controller per intercettare il click sul bottone per confermare i dati inseriti.
	public void ascoltoInviaDati(ActionListener bottoneInvia){
		btn_invia.addActionListener(bottoneInvia);
	}
	
}
