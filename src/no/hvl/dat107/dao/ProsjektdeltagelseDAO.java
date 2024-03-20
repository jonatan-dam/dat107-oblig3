package no.hvl.dat107.dao;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Prosjekt;
import no.hvl.dat107.entity.Prosjektdeltagelse;

public class ProsjektdeltagelseDAO {

	
	private EntityManagerFactory emf;
	
	public ProsjektdeltagelseDAO() {
		emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit",
				Map.of("jakarta.persistence.jbdc.password", "passordTilOblig"));
	}
	
	
	
	public int beregnTotalTimetall(int prosjektID) {
		EntityManager em = emf.createEntityManager();
		Prosjekt p = null;
		int timer = 0;
		
		try {
			p = em.find(Prosjekt.class, prosjektID);
		} finally {
			em.close();
		}
		
		List<Prosjektdeltagelse> pd = p.getDeltagelser();
		
		for(Prosjektdeltagelse prd : pd) {
			timer = timer + prd.getTimer();
		}
		
		
		
		return timer;
		
	}
}
