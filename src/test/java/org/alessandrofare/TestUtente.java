package org.alessandrofare;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alessandrofare.models.*;

/*
 * Classe per JUnit Test per testare la classe Utente --> CheckAdmin e test sui campi
 */
public class TestUtente {

	@Test
	public void testAdmin() {
		Utente u = new Admin("ale", "00");
		u.checkAdmin();
		assertTrue(u.getIsAdmin());
		
		Utente u1 = new Admin("Rossi","Bianchi");
		u1.checkAdmin();
		assertFalse(u1.getIsAdmin());
		
		assertEquals(u.getUsername(), "ale");
		assertEquals(u.getPassword(), "00");
		assertEquals(u1.getUsername(), "Rossi");
		assertEquals(u1.getPassword(), "Bianchi");
	}
	

}
