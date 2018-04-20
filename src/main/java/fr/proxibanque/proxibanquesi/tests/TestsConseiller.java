package fr.proxibanque.proxibanquesi.tests;

import fr.proxibanque.proxibanquesi.dao.ConseillerDao;
import fr.proxibanque.proxibanquesi.dao.ConseillerDaoImp;
import fr.proxibanque.proxibanquesi.model.Conseiller;

public class TestsConseiller {

	public static void main(String[] args) {

		ConseillerDao consDao = new ConseillerDaoImp();
		
		Conseiller cons = new Conseiller("Jacques", "Dupont", "jdupont", "1234");
		
		consDao.creerConseiller(cons);
		
//		Conseiller cons = consDao.obtenirConseillerParLogin("jdupont");
		
		System.out.println(cons);

	}

}
