package com.alessandrofare.views;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.*;
import com.alessandrofare.models.*;
import com.alessandrofare.utils.DesignUtils;

/*
 * Classe che rappresenta l'interfaccia grafica relativa alla votazione del referendum
 */
public class VotazioneReferendum extends JFrame{

	//attributi componenti della finestra
	private JLabel lbdomanda = DesignUtils.customLabel("Proposta:");
	
	private JCheckBox checkFavorevole = new JCheckBox("Favorevole?");

	private JButton btn_invia = DesignUtils.customButton("conferma");
	
	private JComboBox<String> jList1 = new JComboBox<String>();
	
	//costruttore
	public VotazioneReferendum() throws SQLException {
		AdminDao dao = new AdminDao();

		List<String> list = dao.listReferendum();
		Set<String> set = new HashSet<String>(list);
		list.clear();
		list.addAll(set);
		for (int i = 0; i < list.size(); i++)
		{
		    this.jList1.addItem(list.get(i));
		}
		JPanel pannello = DesignUtils.customPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(650,150);
		this.setLocationRelativeTo(null);
		//aggiungo gli attributi componenti sul pannello
		pannello.add(lbdomanda);
		pannello.add(jList1);
		pannello.add(checkFavorevole);
		pannello.add(btn_invia);
		//aggiungo il pannello con i suoi componenti alla finestra
		this.add(pannello);
	}
	
	public String getTxtDomanda(){
		return (String) this.jList1.getSelectedItem();
	}
	
	public boolean getCheckFavorevole(){
		return this.checkFavorevole.isSelected();
	}
	
	public void setCheckAdmin(boolean selezione){
		this.checkFavorevole.setSelected(selezione);
	}
	//metodo usato dal controller per intercettare il click sul bottone per confermare i dati inseriti.
	public void ascoltoInviaDatiReferendum(ActionListener bottoneInvia){
		btn_invia.addActionListener(bottoneInvia);
	}
	
}
