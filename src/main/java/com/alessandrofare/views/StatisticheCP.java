package com.alessandrofare.views;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.alessandrofare.models.AdminDao;
import com.alessandrofare.models.Referendum;
import com.alessandrofare.models.SchedaElettorale;
import com.alessandrofare.models.Vincita;
import com.alessandrofare.models.VincitaType;
import com.alessandrofare.models.Voto;
import com.alessandrofare.models.VotoType;
import com.alessandrofare.utils.DesignUtils;

/*
 * Classe che rappresenta l'interfaccia grafica relativa alle statistiche per candidati/partiti
 */
public class StatisticheCP extends JFrame{

	//attributi componenti della finestra
	private JLabel lbdomanda = DesignUtils.customLabel("Scheda id:");

	private JButton btn_invia = DesignUtils.customButton("conferma");
	
	private JComboBox<String> jList1 = new JComboBox<String>();
	
	//costruttore
	public StatisticheCP() throws SQLException {
		AdminDao dao = new AdminDao();

		List<String> list = dao.listIdScheda();
		Set<String> set = new HashSet<String>(list);
		list.clear();
		list.addAll(set);
		for (int i = 0; i < list.size(); i++)
		{
		    this.jList1.addItem(list.get(i));
		}
		JPanel pannello = new JPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(650,150);
		this.setLocationRelativeTo(null);
		//aggiungo gli attributi componenti sul pannello
		pannello.add(lbdomanda);
		pannello.add(jList1);
		pannello.add(btn_invia);
		//aggiungo il pannello con i suoi componenti alla finestra
		this.add(pannello);
	}
	

	public String getTxtDomanda(){
		return (String) this.jList1.getSelectedItem();
	}
	
	
	//metodo usato dal controller per intercettare il click sul bottone per confermare i dati inseriti.
	public void ascoltoInviaDatiCP(ActionListener bottoneInvia){
		btn_invia.addActionListener(bottoneInvia);
	}
	
}
