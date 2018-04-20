package fr.proxibanque.proxibanquesi.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import fr.proxibanque.proxibanquesi.model.Conseiller;
import fr.proxibanque.proxibanquesi.util.UtilitaireLogger;

public class ConseillerDaoImp implements ConseillerDao {

	@Override
	public void creerConseiller(Conseiller conseiller) {
		UtilitaireLogger.LOGGER.info("Sauvegarde d'un conseiller dans la base de données");
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction txn = em.getTransaction();

		try {
			txn.begin();
			em.persist(conseiller);
			txn.commit();
		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
			UtilitaireLogger.LOGGER.error("Erreur lors de l'écriture en base d'un nouveau conseiller");
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Conseiller obtenirConseillerParLogin(String login) {
		UtilitaireLogger.LOGGER.info("Vérification du login " + login + " dans la base de données");
		EntityManager em = JPAUtil.getEntityManager();
		TypedQuery<Conseiller> tq = em.createQuery("SELECT c FROM Conseiller c " + "WHERE c.login = :login",
				Conseiller.class);
		Conseiller c = null;
		tq.setParameter("login", login);
		try {
			c = tq.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			UtilitaireLogger.LOGGER.error("Erreur lors de l'obtention du conseiller");
		}
		return c;
	}

}
