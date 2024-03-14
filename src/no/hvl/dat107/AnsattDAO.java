package no.hvl.dat107;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

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

		EntityManager em = emf.createEntityManager();
		
		
		
		try {
			
			TypedQuery<Ansatt> query = em.createQuery(
					"select a from Ansatt a where a.brukernavn like :brukernavn", Ansatt.class);
			query.setParameter("brukernavn", brukernavn);
			
			return query.getSingleResult(); // NB! Exception hvis ingen eller flere resultater
					
			
		} finally {
			em.close();
		}
	}
	
	public List<Ansatt> listAlleAnsatte() {
		
		EntityManager em = emf.createEntityManager();
		List<Ansatt> ansatte = null;
		
		try {
			
			String queryString = "select a from Ansatt a order by a.ansatt_id";
			TypedQuery<Ansatt> query =em.createQuery(queryString, Ansatt.class);
			ansatte = query.getResultList();
			
		} finally {
			em.close();
		}
		
		return ansatte;
	}
	
	public void oppdaterAnsattLonn(int id, BigDecimal loonn) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			Ansatt a = em.find(Ansatt.class, id);
			a.setMaanedsloonn(loonn); // Blir merkelig at bruker skal måtte legge inn månedslønn. Sjekk om vi finner en smart måte å sette opp slik
			tx.commit();						  // at bruker skriver årslønn, og program regner om til månedslønn
			
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
	
	public void oppdaterAnsattStilling(int id, String stilling) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			Ansatt a = em.find(Ansatt.class, id);
			a.setStilling(stilling);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
	
	public void leggTilAnsatt(Ansatt a) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			em.persist(a);
			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			
		}
	}
	
	
	
	
}
