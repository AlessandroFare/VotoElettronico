package com.alessandrofare.views;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.alessandrofare.utils.DesignUtils;

/*
 * Classe che rappresenta l'interfaccia grafica relativa alla gestione del sistema per referendum
 */
public class GestioneReferendum extends JFrame{

	//attributi componenti della finestra
	private JLabel lbproposta = DesignUtils.customLabel("Proposta");

	private JTextField txtproposta = DesignUtils.customField(20);
	
	private JLabel lbquorum = DesignUtils.customLabel("Quorum");

	private JTextField txtquorum = DesignUtils.customField(5);

	private JButton btn_invia = DesignUtils.customButton("conferma");
	
	private JComboBox<String> jList1 = new JComboBox<String>();
	
	//costruttore
	public GestioneReferendum() {
		List<String> list1 = new ArrayList<String>();
		list1.add("referendumSenzaQuorum");
		list1.add("referendumConQuorum");

		for (int i = 0; i < list1.size(); i++)
		{
		    this.jList1.addItem(list1.get(i));
		}
		
		JPanel pannello = DesignUtils.customPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,120);
		this.setLocationRelativeTo(null);
		//aggiungo gli attributi componenti sul pannello
		pannello.add(lbproposta);
		pannello.add(txtproposta);
		pannello.add(jList1);
		pannello.add(lbquorum);
		pannello.add(txtquorum);
		pannello.add(btn_invia);
		//aggiungo il pannello con i suoi componenti alla finestra
		this.add(pannello);
	}
	
	public String getTxtProposta(){
		return this.txtproposta.getText();
	}
	
	public void setTxtProposta(String proposta){
		this.txtproposta.setText(proposta);
	}

	public String getQuorum() {
		return this.txtquorum.getText();
	}
	
	public void setTxtQuorum(String quorum){
		this.txtquorum.setText(quorum);
	}
	
	public String getTxtVincita(){
		return (String) this.jList1.getSelectedItem();
	}
	
	//metodo usato dal controller per intercettare il click sul bottone per confermare i dati inseriti.
	public void ascoltoInviaDati(ActionListener bottoneInvia){
		btn_invia.addActionListener(bottoneInvia);
	}
	
}
