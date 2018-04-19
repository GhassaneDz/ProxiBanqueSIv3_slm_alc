package fr.proxibanque.proxibanquesi.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe utilitaire permettant d'exploiter l'objet EntityManagerFactory (EMF)
 * offert par JPA. Il est ici présent sous forme d'une variable de classe
 * instanciée lors de l'appel à la classe (bloc statique).
 * 
 * L'EMF est fermé lors de l'arrêt du serveur.
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
	 * de données.
	 * 
	 * @return Objet EntityManager
	 */
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}
