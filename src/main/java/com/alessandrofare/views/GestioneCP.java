package com.alessandrofare.views;

import java.util.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;

import com.alessandrofare.utils.DesignUtils;
import com.toedter.calendar.*;
/*
 * Classe che rappresenta l'interfaccia grafica relativa alla gestione del sistema per candidati/partiti
 */
public class GestioneCP extends JFrame{

	//attributi componenti della finestra
	private JLabel lbpartito = DesignUtils.customLabel("Nome Partito");
	
	private JLabel empty = new JLabel("                  																			");
	
	private JLabel empty1 = new JLabel("                  																			");
	
	private JLabel empty2 = new JLabel("                  																			");
	
	private JLabel lbcandidato = DesignUtils.customLabel("Nome Candidato");

	private JLabel lbcognome = DesignUtils.customLabel("Cognome");

	private JLabel lbnomePartito = DesignUtils.customLabel("Partito Candidato");
	
	private JLabel lbvoto = DesignUtils.customLabel("Tipo voto");	

	private JLabel lbvincita = DesignUtils.customLabel("Tipo vincita");


	private JTextField txtpartito = DesignUtils.customField(6);

	private JTextField txtcandidato = DesignUtils.customField(6);

	private JTextField txtcognome = DesignUtils.customField(6);
	
	private JTextField txtnomePartito = DesignUtils.customField(6);	

	private JButton btn_inviaPartito = DesignUtils.customButton("insert");
	
	private JButton btn_inviaCandidato = DesignUtils.customButton("insert");

	private JButton btn_invia = DesignUtils.customButton("conferma");
	
	private JComboBox<String> jList1 = new JComboBox<String>();
	
	private JComboBox<String> jList2 = new JComboBox<String>();
	
	//costruttore
	public GestioneCP() {
		
		List<String> list1 = new ArrayList<String>();
		list1.add("ordinale");
		list1.add("categorico");
		list1.add("categoricoPreferenze");

		for (int i = 0; i < list1.size(); i++)
		{
		    this.jList1.addItem(list1.get(i));
		}
		
		
		List<String> list2 = new ArrayList<String>();
		list2.add("maggioranza");
		list2.add("maggioranzaAssoluta");
		

		for (int i = 0; i < list2.size(); i++)
		{
		    this.jList2.addItem(list2.get(i));
		}
		
		jList1.setPrototypeDisplayValue("ordinale");
		jList2.setPrototypeDisplayValue("maggioranza");
		JPanel pannello = DesignUtils.customPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(140,580);
		this.setLocationRelativeTo(null);
		//aggiungo gli attributi componenti sul pannello
		pannello.add(lbpartito);
		pannello.add(txtpartito);
		pannello.add(btn_inviaPartito);
		pannello.add(empty);
		pannello.add(lbcandidato);
		pannello.add(txtcandidato);
		pannello.add(lbcognome);
		pannello.add(txtcognome);
		pannello.add(lbnomePartito);
		pannello.add(txtnomePartito);
		pannello.add(btn_inviaCandidato);
		pannello.add(empty1);
		pannello.add(lbvoto);
		pannello.add(jList1);
		pannello.add(lbvincita);
		pannello.add(jList2);
		pannello.add(empty2);
		pannello.add(btn_invia);
		//aggiungo il pannello con i suoi componenti alla finestra
		this.add(pannello);
	}
	
	
	public String getTxtPartito(){
		return this.txtpartito.getText();
	}
	
	public void setTxtPartito(String nome){
		this.txtpartito.setText(nome);
	}
	
	public String getTxtCandidato(){
		return this.txtcandidato.getText();
	}
	
	public void setTxtCandidato(String candidato){
		this.txtcandidato.setText(candidato);
	}
	
	public String getTxtCognome(){
		return this.txtcognome.getText();
	}
	
	public void setTxtCognome(String cognome){
		this.txtcognome.setText(cognome);
	}
	
	public String getTxtNomePartito(){
		return this.txtnomePartito.getText();
	}
	
	public void setTxtNomePartito(String nome){
		this.txtnomePartito.setText(nome);
	}
	
	public String getTxtVoto(){
		return (String) this.jList1.getSelectedItem();
	}
	
	public String getTxtVincita(){
		return (String) this.jList2.getSelectedItem();
	}
	
	//metodo usato dal controller per intercettare il click sul bottone per confermare i dati del partito inseriti.
	public void ascoltoInviaDatiPartito(ActionListener bottoneInvia){
		btn_inviaPartito.addActionListener(bottoneInvia);
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
