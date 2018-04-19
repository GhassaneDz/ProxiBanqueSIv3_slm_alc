package fr.proxibanque.proxibanquesi.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.proxibanque.proxibanquesi.model.Client;

public class ClientDaoImp implements ClientDao {

	@Override
	public void creerClient(Client client) {
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
		} finally {
			if (em != null) {
				em.close();
			}
		}		
	}

	@Override
	public Client updateClientById(long id, Client clientmodif) {
		// TODO Auto-generated method stub
		return null;
	}

}
