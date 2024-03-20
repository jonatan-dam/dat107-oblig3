package no.hvl.dat107.dao;

import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;

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
	
	public void listAlleAnsatte(int avdelingID) {
		EntityManager em = emf.createEntityManager();
		
		try {
			Avdeling avdeling = em.find(Avdeling.class, avdelingID);
			
			for(Ansatt ansatt : avdeling.getAnsatte()) {
				if(ansatt.equals(avdeling.getSjef())) {
					System.out.println("* Sjef: " + ansatt.getFornavn() + " " +
				    ansatt.getEtternavn() + " (ID: " + ansatt.getAnsatt_id() + ")");
				}else {
					System.out.println("- Ansatt: " + ansatt.getFornavn() + " " +
						    ansatt.getEtternavn() + " (ID: " + ansatt.getAnsatt_id() + ")");
				} //End if else
			} // end for løkke	
		} finally {
			em.close();
		}
	}
	
	public void lagreNyAvdeling(String avdelingsnavn, int ansattID) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		AnsattDAO ansattDAO = new AnsattDAO();
		Ansatt sjef = new Ansatt();
		sjef = ansattDAO.finnAnsattMedId(ansattID);
		
		Avdeling nyAvdeling = new Avdeling(avdelingsnavn, sjef);
		
		
		
		
		try {
			tx.begin();
			em.persist(nyAvdeling);
			tx.commit();
			
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		int avdelingsID = nyAvdeling.getAvdeling_id();
		ansattDAO.endreAvdeling(ansattID, avdelingsID);

	}
	
}
