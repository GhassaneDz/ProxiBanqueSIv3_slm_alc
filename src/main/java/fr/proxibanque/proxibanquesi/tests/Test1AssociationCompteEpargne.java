package fr.proxibanque.proxibanquesi.tests;

import fr.proxibanque.proxibanquesi.model.Client;
import fr.proxibanque.proxibanquesi.model.CompteEpargne;
import fr.proxibanque.proxibanquesi.service.GestionClientService;
import fr.proxibanque.proxibanquesi.service.SIService;
import fr.proxibanque.proxibanquesi.service.ServiceProxibanqueImpl;

/**
 * Test de l'association du compte epargne
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */
public class Test1AssociationCompteEpargne {
	public static void main(String[] args) {
		SIService si = new ServiceProxibanqueImpl();
		GestionClientService gsc = new ServiceProxibanqueImpl();
		si.associerCompteEpargne(7);
		Client client = gsc.obtenirClient(7);
		
		System.out.println(client.getCompteEpargne());
		
		
	}
	

}
