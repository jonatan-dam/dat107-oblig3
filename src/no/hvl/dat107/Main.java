package no.hvl.dat107;

import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		Ansatt a = finnAnsatt(1);
		
		System.out.println(a.toString());
		

	}
	
	public static Ansatt finnAnsatt(int id) {
		
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit", Map.of("jakarta.persistence.jdbc.password", "passordTilOblig"));
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		Ansatt a = null;
		
		try {
			a = em.find(Ansatt.class, id);
			
		} finally {
			em.close();
		}
		
		return a;
		
	}

}
