package fr.proxibanque.proxibanquesi.tests;

import fr.proxibanque.proxibanquesi.dao.CompteDao;
import fr.proxibanque.proxibanquesi.dao.CompteDaoImp;
import fr.proxibanque.proxibanquesi.model.Compte;

public class TestObtenirCompteParDao {

	public static void main(String[] args) {

		CompteDao compteDao = new CompteDaoImp();
		
		Compte compte = compteDao.obtenirCompte(204228787);
		
		System.out.println(compte);
		
	}

}
