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

public class ProsjektDAO {

	private EntityManagerFactory emf;
	
	public ProsjektDAO() {
		emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit",
				Map.of("jakarta.persistence.jbdc.password", "passordTilOblig"));
	}
	
	public void leggTilProsjekt(Prosjekt p) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.persist(p);
			tx.commit();
			
		} catch (Throwable e) {
			e.printStackTrace();
			if(tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}
	
	public Prosjekt finnProsjektMedId(int id) {
		EntityManager em = emf.createEntityManager(); 
		
		Prosjekt p = null; 
		
		try {
			
			p = em.find(Prosjekt.class, id); 
			
		} finally {
			em.close(); 
		}
		
		return p;
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
	
	public void skrivUtAlt(int id) {
		Prosjekt p = finnProsjektMedId(id);
		
	    String prosjekt = "Prosjekt: \n" + 
						"Id: " + p.getId() + "\n" +
						"Navn: " + p.getNavn() + "\n" +
						"Beskrivelse: " + p.getBeskrivelse() + "\n";
	    
	    String deltagelse = "Deltakere: \n";
	    System.out.println(prosjekt);
	    System.out.println(deltagelse);
	    
	    for(Prosjektdeltagelse pd : p.getDeltagelser()) {
	    	pd.skrivUt();
	    }
	    	
	    
	    System.out.println("Totalt timetall i prosjektet: " + beregnTotalTimetall(id));
	    }
}
