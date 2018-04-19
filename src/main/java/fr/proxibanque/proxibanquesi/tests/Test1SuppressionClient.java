package fr.proxibanque.proxibanquesi.tests;

import fr.proxibanque.proxibanquesi.dao.ClientDao;
import fr.proxibanque.proxibanquesi.dao.ClientDaoImp;

public class Test1SuppressionClient {

	public static void main(String[] args) {
		// TODO test suppression depuis le dao
		ClientDao dao = new ClientDaoImp();
		dao.supprimerClient(1L);

	}

}
