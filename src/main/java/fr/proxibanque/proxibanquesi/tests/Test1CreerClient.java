package fr.proxibanque.proxibanquesi.tests;

import fr.proxibanque.proxibanquesi.dao.ClientDao;
import fr.proxibanque.proxibanquesi.dao.ClientDaoImp;
import fr.proxibanque.proxibanquesi.model.Client;
import fr.proxibanque.proxibanquesi.model.CompteCourant;
import fr.proxibanque.proxibanquesi.service.GestionClientService;
import fr.proxibanque.proxibanquesi.service.ServiceProxibanqueImpl;

/**
 * Test pour valider la création d'un client dans la BDD Test pour la couche DAO
 * 
 * @author Sandrine Le Mentec et Anthony Le Cigne
 *
 */
public class Test1CreerClient {

	public static void main(String[] args) {

		GestionClientService gcs = new ServiceProxibanqueImpl();

		Client client1 = new Client("Martin", "Michel", "1 rue de la Source", "75001", "Paris", "0100000001");

		gcs.creerClient(client1);

	}

}
