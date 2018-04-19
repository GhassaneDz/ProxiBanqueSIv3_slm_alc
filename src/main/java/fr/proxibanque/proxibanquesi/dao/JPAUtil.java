package fr.proxibanque.proxibanquesi.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static final EntityManagerFactory emf;

	private JPAUtil() {
	}
	
	static {
		emf = Persistence.createEntityManagerFactory("my-pu");
	}

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
}
