package fr.proxibanque.proxibanquesi.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import fr.proxibanque.proxibanquesi.model.Client;
import fr.proxibanque.proxibanquesi.util.UtilitaireLogger;

/**
 * Implémentation de l'interface ClientDAO pour une base de données SQL. Elle
 * contient des méthodes permettant de réaliser le CRUD à l'aide du standard ORM
 * JPA.
 * 
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */

public class ClientDaoImp implements ClientDao {

	@Override
	public void creerClient(Client client) {
		UtilitaireLogger.LOGGER.info("Sauvegarde d'un client dans la base de données");
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction txn = em.getTransaction();

		try {
			txn.begin();
			em.persist(client);
			txn.commit();
		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
			UtilitaireLogger.LOGGER.error("Erreur lors de l'écriture en base d'un nouveau client");
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Client obtenirClient(long idClient) {
		UtilitaireLogger.LOGGER.info("Appel du client " + idClient + " dans la base de données");
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction txn = em.getTransaction();
		Client client = null;

		try {
			txn.begin();
			client = em.find(Client.class, idClient);
		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
			UtilitaireLogger.LOGGER.error("Erreur lors la lecture en base du client " + idClient);
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return client;
	}

	@Override
	public void modifierClient(Client client) {
		UtilitaireLogger.LOGGER.info("Modification du client " + client.getIdClient() + "dans la base de données");
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction txn = em.getTransaction();
		try {
			txn.begin();
			em.merge(client);
			txn.commit();
		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
			UtilitaireLogger.LOGGER.error("Erreur lors l'écriture en base du client " + client.getIdClient());
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	@Override
	public void supprimerClient(long idClient) {
		UtilitaireLogger.LOGGER.info("Suppression du client " + idClient + " dans la base de données");
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction txn = em.getTransaction();
		try {
			txn.begin();
			em.remove(em.find(Client.class, idClient));
			txn.commit();
		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
			UtilitaireLogger.LOGGER.error("Erreur lors la suppression en base du client " + idClient);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public List<Client> obtenirTousLesClients() {
		UtilitaireLogger.LOGGER.info("Récupération de tous les clients dans la base de données");
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction txn = em.getTransaction();
		List<Client> listeClients = new ArrayList<>();
		try {
			TypedQuery<Client> tq = em.createQuery("SELECT c FROM Client c", Client.class);
			listeClients = (ArrayList<Client>) tq.getResultList();
		} catch (Exception e) {
			if (txn != null) {
				em.close();
				e.printStackTrace();
				UtilitaireLogger.LOGGER.error("Erreur lors la lecture en base de tous les clients");

			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
		return listeClients;
	}

}
