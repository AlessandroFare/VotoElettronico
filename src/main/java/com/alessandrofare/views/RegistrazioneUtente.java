package com.alessandrofare.views;


import java.awt.event.ActionListener;
import javax.swing.*;

import com.alessandrofare.utils.DesignUtils;

/*
 * Classe che rappresenta l'interfaccia grafica relativa alla registrazione dell'utente
 */
public class RegistrazioneUtente extends JFrame{

	//attributi componenti della finestra
	private JLabel lbusername =  DesignUtils.customLabel("username");

	private JLabel lbpassword =  DesignUtils.customLabel("password");

	private JTextField txtUsername =  DesignUtils.customField(6);

	private JTextField txtpassword =  DesignUtils.customField(6);
	
	private JCheckBox checkAdmin = new JCheckBox("Admin?");
	
	private JCheckBox checkDistanza = new JCheckBox("Votazione a Distanza?");

	private JButton btn_invia =  DesignUtils.customButton("conferma");
	
	//costruttore
	public RegistrazioneUtente() {
		JPanel pannello =  DesignUtils.customPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(650,150);
		this.setLocationRelativeTo(null);
		//aggiungo gli attributi componenti sul pannello
		pannello.add(lbusername);
		pannello.add(txtUsername);
		pannello.add(lbpassword);
		pannello.add(txtpassword);
		pannello.add(checkAdmin);
		pannello.add(checkDistanza);
		pannello.add(btn_invia);
		//aggiungo il pannello con i suoi componenti alla finestra
		this.add(pannello);
	}
	
	public String getTxtUsername(){
		return this.txtUsername.getText();
	}
	
	public void setTxtUsername(String username){
		this.txtUsername.setText(username);
	}

	public String getTxtPassword(){
		return this.txtpassword.getText();
	}
	
	public void setTxtPassword(String password){
		this.txtpassword.setText(password);
	}
	
	public boolean getCheckAdmin(){
		return this.checkAdmin.isSelected();
	}
	
	public void setCheckAdmin(boolean selezione){
		this.checkAdmin.setSelected(selezione);
	}
	
	public boolean getCheckDistanza(){
		return this.checkDistanza.isSelected();
	}
	
	public void setCheckDistanza(boolean selezione){
		this.checkDistanza.setSelected(selezione);
	}
	//metodo usato dal controller per intercettare il click sul bottone per confermare i dati inseriti.
	public void ascoltoInviaDati(ActionListener bottoneInvia){
		btn_invia.addActionListener(bottoneInvia);
	}
	
}