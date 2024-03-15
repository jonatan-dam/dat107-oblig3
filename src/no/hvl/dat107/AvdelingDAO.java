package no.hvl.dat107;

import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AvdelingDAO {

	private EntityManagerFactory emf;
	
	public AvdelingDAO() {
		emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit",
				Map.of("jakarta.persistence.jbdc.password", "passordTilOblig"));
	}
	
	public Avdeling finnAvdelingMedId(int id) {
		EntityManager em = emf.createEntityManager();
		Avdeling a = null;
		
		try {
			a = em.find(Avdeling.class, id);
		} finally {
			em.close();
		}
		
		return a;
	}
}
