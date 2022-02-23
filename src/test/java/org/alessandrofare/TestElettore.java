package org.alessandrofare;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.alessandrofare.models.Elettore;

/*
 * Classe per JUnit Test per testare la classe Elettore --> CheckAdmin e test sui campi
 */
public class TestElettore {

	@Test
	public void testCodFiscale() throws IOException {
		Elettore el = new Elettore("ALESSANDRO", "FARE", "M", "14/07/1998", "MILANO", "Italia");
		el.generateCF();
		assertEquals(el.getCF(), "FRALSN98L14F205D");
		
	}
	
	@Test
	public void testAdmin() throws IOException {
		Elettore el = new Elettore("ALESSANDRO", "FARE", "M", "14/07/1998", "MILANO", "Italia");
		el.generateCF();
		el.setUsername("alessandro");
		el.setPassword("prova");
		el.checkAdmin();
		assertFalse(el.getIsAdmin());
		
		el.setUsername("ale");
		el.setPassword("00");
		el.checkAdmin();
		assertTrue(el.getIsAdmin());		
	}
	
	@Test
	public void testCampi() throws IOException {
		Elettore el = new Elettore("ALESSANDRO", "FARE", "M", "14/07/1998", "MILANO", "Italia");
		
		assertEquals(el.getGiorno(), 14);
		assertEquals(el.getMese(), 7);
		assertEquals(el.getAnno(), 1998);
		assertEquals(el.getCitta(), "MILANO");
		assertEquals(el.getStato(), "Italia");
	}

}
