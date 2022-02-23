package com.alessandrofare.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.alessandrofare.utils.*;
/*
 * Util per la creazione di componenti swing customizzati --> JButton, JLabel, JPanel, JTextField
 */
public class DesignUtils {
		
	public static JButton customButton(String text) {
		  Font font = new Font("TimesRoman", Font.BOLD, 14);
		  JButton button = new JButton(text);
		  button.setForeground(Color.WHITE);
		  button.setBackground(Color.BLACK);
		  button.setFocusPainted(false);
		  Border margin = new EmptyBorder(5, 15, 5, 15);
		  button.setFont(font);
		  return button;
	}

	public static JLabel customLabel(String text) {
		JLabel label = new JLabel(text);
		Font font = new Font("TimesRoman", Font.BOLD, 16);
		label.setFont(font);
		return label;
	}
	
	public static JPanel customPanel() {
		JPanel pannello = new JPanel();
		pannello.setBackground(new Color(245,225,253));
		return pannello;
	}
	
	// Utilizza la classe RoundJTextField per creare textfield con i round border
	public static JTextField customField(int d) {
		JTextField j = new RoundJTextField(d);
		Font font = new Font("Arial", Font.PLAIN, 20);
		j.setFont(font);
		j.setForeground(Color.gray.brighter());
		return j;
	}
	

}
