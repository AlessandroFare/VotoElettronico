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
 * Classe che rappresenta l'interfaccia grafica relativa alla votazione ordinale per candidati/partiti
 */
public class VotazioneOrdinale extends JFrame{

	//attributi componenti della finestra
	private JLabel lbpartito = DesignUtils.customLabel("Candidato");
	
	private JLabel lbposizione = DesignUtils.customLabel("Posizione");
	
	private JTextField txtposizione = DesignUtils.customField(3);	
	
	private JButton btn_inviaCandidato = DesignUtils.customButton("insert");

	private JButton btn_invia = DesignUtils.customButton("conferma");
	
	private JComboBox<String> jList1 = new JComboBox<String>();
	
	
	//costruttore
	public VotazioneOrdinale() {
		AdminDao dao = new AdminDao();
		List<String> list1 = dao.listCandidati();
	
		for (int i = 0; i < list1.size(); i++)
		{
		    this.jList1.addItem(list1.get(i));
		}
		
		JPanel pannello = DesignUtils.customPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,200);
		this.setLocationRelativeTo(null);
		//aggiungo gli attributi componenti sul pannello
		pannello.add(lbpartito);
		pannello.add(jList1);
		pannello.add(lbposizione);
		pannello.add(txtposizione);
		pannello.add(btn_inviaCandidato);
		pannello.add(btn_invia);
		//aggiungo il pannello con i suoi componenti alla finestra
		this.add(pannello);
	}
	
	
	public String getTxtVoto(){
		return (String) this.jList1.getSelectedItem();
	}
	
	public String getTxtPosizione(){
		return this.txtposizione.getText();
	}
	
	public void setTxtPosizione(String posizione){
		this.txtposizione.setText(posizione);
	}
	
	//metodo usato dal controller per intercettare il click sul bottone per confermare i dati del candidato inseriti.
	public void ascoltoInviaDatiCandidato(ActionListener bottoneInvia){
		btn_inviaCandidato.addActionListener(bottoneInvia);
	}
	
	//metodo usato dal controller per intercettare il click sul bottone per confermare i dati inseriti.
	public void ascoltoInviaDati(ActionListener bottoneInvia){
		btn_invia.addActionListener(bottoneInvia);
	}
	
}
