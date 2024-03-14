package no.hvl.dat107;

import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AnsattDAO {

	private EntityManagerFactory emf;
	
	public AnsattDAO() {
		emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit", 
				Map.of("jakarta.persistence.jbdc.password", "passordTilOblig"));
	}
	
	public Ansatt finnAnsattMedId(int id) {
		
		EntityManager em = emf.createEntityManager(); // Oppretter en entity-manager
		
		Ansatt a = null; // Oppretter et tomt Ansatt-objekt som vi kan sette til å peke på riktig ansatt fra databasen
		
		try {
			a = em.find(Ansatt.class, id); // Leter etter ansatt med tilhørende ID, og forsøker å sette a lik denne ansatte
		} finally {
			em.close(); // Lukker entity-manageren
		}
		
		return a;
	}
	
	public Ansatt finnAnsattMedBrukernavn(String brukernavn) {
		// TO DO
		return null;
	}
	
	public void listAlleAnsatte() {
		// TO DO
	}
	
	public void oppdaterAnsattLonn(int id) {
		// TO DO
	}
	
	public void oppdaterAnsattStilling(int id) {
		// TO DO
	}
	
	public int leggTilAnsatt(Ansatt a) {
		// TO DO
		return 0;
	}
	
	
	
	
}
