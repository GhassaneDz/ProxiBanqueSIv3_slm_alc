package fr.proxibanque.proxibanquesi.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe utilitaire permettant d'exploiter l'objet EntityManagerFactory (EMF)
 * offert par JPA. Il est ici pr�sent sous forme d'une variable de classe
 * instanci�e lors de l'appel � la classe (bloc statique).
 * 
 * L'EMF est ferm� lors de l'arr�t du serveur.
 * 
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */
public class JPAUtil {

	private static final EntityManagerFactory emf;

	private JPAUtil() {
	}

	static {
		emf = Persistence.createEntityManagerFactory("my-pu");
	}

	/**
	 * Retourne un objet EntityManager afin d'initier une transaction avec la base
	 * de donn�es.
	 * 
	 * @return Objet EntityManager
	 */
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}
