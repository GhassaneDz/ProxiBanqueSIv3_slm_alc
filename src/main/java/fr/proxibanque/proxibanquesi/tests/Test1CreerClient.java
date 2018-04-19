package fr.proxibanque.proxibanquesi.tests;

import fr.proxibanque.proxibanquesi.dao.ClientDao;
import fr.proxibanque.proxibanquesi.dao.ClientDaoImp;
import fr.proxibanque.proxibanquesi.model.Client;

public class Test1CreerClient {

	public static void main(String[] args) {

		ClientDao clientDao = new ClientDaoImp();

		Client client1 = new Client("Dupont", "Michel", "1 rue de la Source", "75001", "Paris", "0100000001");

		clientDao.creerClient(client1);

	}

}
