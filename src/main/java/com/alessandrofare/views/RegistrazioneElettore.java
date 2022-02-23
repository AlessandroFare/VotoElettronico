package com.alessandrofare.views;

import java.util.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;

import com.alessandrofare.document.StateCodes;
import com.alessandrofare.utils.DesignUtils;
import com.toedter.calendar.*;

/*
 * Classe che rappresenta l'interfaccia grafica relativa alla registrazione dell'elettore
 */
public class RegistrazioneElettore extends JFrame{

	//attributi componenti della finestra
	private JLabel lbnome =  DesignUtils.customLabel("Nome");

	private JLabel lbcognome =  DesignUtils.customLabel("Cognome");

	private JLabel lbsesso =  DesignUtils.customLabel("Sesso");
	
	private JLabel lbchooser =  DesignUtils.customLabel("Nascita");	

	private JLabel lbcitta =  DesignUtils.customLabel("Città");
	
	private JLabel lbstato =  DesignUtils.customLabel("Stato");


	private JTextField txtnome =  DesignUtils.customField(6);

	private JTextField txtcognome =  DesignUtils.customField(6);

	private JTextField txtsesso =  DesignUtils.customField(6);
	
	private JDateChooser txtchooser = new JDateChooser();	

	private JTextField txtcitta =  DesignUtils.customField(6);

	private JTextField txtstato =  DesignUtils.customField(6);

	private JButton btn_invia =  DesignUtils.customButton("Conferma");
	
	private JComboBox<String> jList1 = new JComboBox<String>();
	

	//costruttore
	public RegistrazioneElettore() {
		StateCodes states = new StateCodes();
		List<String> list1 = states.getValue();
		list1.add(0,"Italia");

		for (int i = 0; i < list1.size(); i++)
		{
		    this.jList1.addItem(list1.get(i));
		}
		JPanel pannello =  DesignUtils.customPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(657,150);
		this.setLocationRelativeTo(null);
		//aggiungo gli attributi componenti sul pannello
		pannello.add(lbnome);
		pannello.add(txtnome);
		pannello.add(lbcognome);
		pannello.add(txtcognome);
		pannello.add(lbsesso);
		pannello.add(txtsesso);
		pannello.add(lbchooser);
		pannello.add(txtchooser);
		pannello.add(lbcitta);
		pannello.add(txtcitta);
		pannello.add(lbstato);
		pannello.add(jList1);
		pannello.add(btn_invia);
		//aggiungo il pannello con i suoi componenti alla finestra
		this.add(pannello);
	}
	
	public String getTxtNome(){
		return this.txtnome.getText();
	}
	
	public void setTxtNome(String nome){
		this.txtnome.setText(nome);
	}
	
	public String getTxtCognome(){
		return this.txtcognome.getText();
	}
	
	public void setTxtCognome(String cognome){
		this.txtcognome.setText(cognome);
	}
	
	public String getTxtSesso(){
		return this.txtsesso.getText();
	}
	
	public void setTxtSesso(String sesso){
		this.txtsesso.setText(sesso);
	}
	
	public String getTxtNascita(){
		SimpleDateFormat dcn = new SimpleDateFormat("dd/MM/yyyy");
	    String date = dcn.format(this.txtchooser.getDate() );
	    return date;
	}
	
	public void setTxtNascita(String nascita) throws ParseException{
		Date date=new SimpleDateFormat("dd/MM/yyyy").parse(nascita);
		this.txtchooser.setDate(date);
	}
	
	public String getTxtCitta(){
		return this.txtcitta.getText();
	}
	
	public void setTxtCitta(String citta){
		this.txtcitta.setText(citta);
	}
	
	public String getTxtStato(){
		return (String) this.jList1.getSelectedItem();
		
	}

	public void setTxtStato(String stato){
		this.txtstato.setText(stato);
	}
	
	//metodo usato dal controller per intercettare il click sul bottone per confermare i dati inseriti.
	public void ascoltoInviaDati(ActionListener bottoneInvia){
		btn_invia.addActionListener(bottoneInvia);
	}
	
}
