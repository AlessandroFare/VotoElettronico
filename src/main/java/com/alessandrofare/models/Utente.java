package com.alessandrofare.models;

/*
 * Classe per la creazione degli utenti (gestore sistema o elettore).
 * L'utente è identificato da username e password e tramite il metodo checkAdmin si identifica se l'utente è admin o meno.
 * E' usato per il login iniziale.
 */
public abstract class Utente {
	
	protected String username;
	protected String password;
	protected boolean isAdmin;
	
	
	public Utente(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Utente() {
		
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean getIsAdmin() {
		return this.isAdmin;
	}
	
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	public void checkAdmin() {
		if((this.username.equals("ale")) && (this.password.equals("00"))) {
			this.isAdmin = true;
		}
		else {
			this.isAdmin = false;
		}
	}
	
	@Override
	public String toString() {
		return "Utente [username=" + username + ", password=" + password + ", Admin=" + isAdmin + "]";
	}
}
