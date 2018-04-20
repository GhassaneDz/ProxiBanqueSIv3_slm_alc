package fr.proxibanque.proxibanquesi.tests;

import fr.proxibanque.proxibanquesi.dao.ClientDao;
import fr.proxibanque.proxibanquesi.dao.ClientDaoImp;
import fr.proxibanque.proxibanquesi.model.CarteBancaire;
import fr.proxibanque.proxibanquesi.model.Client;
import fr.proxibanque.proxibanquesi.model.CompteCourant;

/**
 * @author Test de sauvegarde d'une CB (version bas niveau)
 *
 */
public class TestSauvegardeCB {

	public static void main(String[] args) {
		
		ClientDao clientdao = new ClientDaoImp();
		Client client = new Client("Dupont", "Michel", "1 rue de la Source", "75001", "Paris", "0100000001");
		CompteCourant cc = new CompteCourant(200L, 0.0, "20/04/2018");
		CarteBancaire cb= new CarteBancaire(1000L, CarteBancaire.VISA);
		cc.setCarte(cb);
		client.setCompteCourant(cc);
		clientdao.creerClient(client);

	}

}
