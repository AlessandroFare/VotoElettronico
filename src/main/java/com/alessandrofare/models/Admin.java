package com.alessandrofare.models;

import java.util.ArrayList;
import java.util.List;

/*
 * Classe per la creazione del gestore del sistema. Estende la classe astratta Utente e sarà admin solo quando username e password corrispondono
 * rispettivamente a "ale" e "00".
 */
public class Admin extends Utente{

	public Admin(String username, String password) {
		super(username, password);
	}

	public Admin() {
	}
	
	// Main per spiegare come fare il check dell'admin
	public static void main(String args[]) {
		String username = "ale";
		String password = "00";
		Utente u = new Admin(username, password);
		u.checkAdmin();
		System.out.println(u.getIsAdmin()); // TRUE
	}
	
}
