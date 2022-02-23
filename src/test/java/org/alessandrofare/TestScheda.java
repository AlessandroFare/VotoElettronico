package org.alessandrofare;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import com.alessandrofare.models.*;

/*
 * Classe per JUnit Test per testare la classe SchedaElettorale --> test su votazione candidati/partiti | test su votazione referendum
 */
public class TestScheda {

	@Test
	public void testCP() {
		int id = 2;
		Vincita v = new Vincita(VincitaType.maggioranza);
		Voto vo = new Voto(VotoType.categorico);
		
		//Partito Lega
		Candidato c1 = new Candidato("Alessandro", "Bianchi", "Lega", 81);
		Candidato c2 = new Candidato("Giacomo", "Verdi", "Lega", 54);
		List<Candidato> candidati1 = new ArrayList<Candidato>();
		candidati1.add(c1);
		candidati1.add(c2);
		Partito lega = new Partito("Lega", candidati1);
		
		//Partito M5S
		Candidato c3 = new Candidato("Ubaldo", "Gialli", "M5S", 79);
		Candidato c4 = new Candidato("Francesca", "Galeazzi", "M5S", 81);
		List<Candidato> candidati2 = new ArrayList<Candidato>();
		candidati1.add(c3);
		candidati1.add(c4);
		Partito m5s = new Partito("M5S", candidati2);
		
		//Partiti + candidati
		List<Partito> partiti = new ArrayList<Partito>();
		partiti.add(lega);
		partiti.add(m5s);
		
		//Preferenza per c1 --> numVoti+1
		c1.setPreferenza(true);
		vo.setCandidato(c1);
		vo.votoCategorico(c1);
		if(c1.getPreferenza() == true) {
			c1.setNumVoti(c1.getNumVoti()+1);
		}
		
		v.setPartiti(partiti);
		Candidato vincitore = v.vincitore();
		
		assertEquals(vincitore.getNome(), "Alessandro");
		assertEquals(vincitore.getNomePartito(), "Lega");
		
	}
	
	@Test
	public void testReferendum() {
		Referendum r = new Referendum("Mascherine obbligatorie?", 0.7);
		r.setNumVoti(99);
		r.setNumVotiPositivi(69);
		r.setNumVotiNegativi(30);
		
		Vincita v = new Vincita(VincitaType.referendumConQuorum);
		v.setReferendum(r);
		Map<String, Integer> esito = v.vincitaReferendum();
		String result = "";
		for (Map.Entry<String,Integer> entry : esito.entrySet()) {
			result = entry.getKey();
		}
		String[] split = result.split(" ");
		
		assertEquals(split[0] + " " + split[1], "Non passata");
		
		//Ultimo voto: favorevole --> 70% voti positivi >= quorum --> ora passa
		
		r.setFavorevole(true);
		v.setReferendum(r);
		Map<String, Integer> esito1 = v.vincitaReferendum();
		String result1 = "";
		for (Map.Entry<String,Integer> entry : esito1.entrySet()) {
			result1 = entry.getKey();
		}
		String[] split1 = result1.split(" ");
		
		assertEquals(split1[0], "Passata");
	}
	
	

}
