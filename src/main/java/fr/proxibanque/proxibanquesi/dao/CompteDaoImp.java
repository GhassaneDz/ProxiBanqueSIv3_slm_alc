package fr.proxibanque.proxibanquesi.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.proxibanque.proxibanquesi.model.Client;
import fr.proxibanque.proxibanquesi.model.Compte;

public class CompteDaoImp implements CompteDao {

	@Override
	public Compte obtenirCompte(long numeroCompte) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction txn = em.getTransaction();
		Compte compte = null;

		try {
			txn.begin();
			compte = em.find(Compte.class, numeroCompte);
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
		return compte;
	}

	/*
	 * Impl�mentation-d�pendant : JPA permet de modifier l'objet ad�quat dans la
	 * base de donn�es, � partir de l'ID contenu dans le nouvel objet, gr�ce � la
	 * m�thode merge.
	 */
	@Override
	public void modifierCompte(Compte compte) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction txn = em.getTransaction();
		try {
			txn.begin();
			em.merge(compte);
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

}
