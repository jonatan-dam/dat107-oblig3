package no.hvl.dat107.dao;

import java.util.Map;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ProsjektdeltagelseDAO {

	
	private EntityManagerFactory emf;
	
	public ProsjektdeltagelseDAO() {
		emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit",
				Map.of("jakarta.persistence.jbdc.password", "passordTilOblig"));
	}
	
	public void registrerDeltagelse() {
		
	}
}
