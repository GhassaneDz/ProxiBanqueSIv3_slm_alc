package fr.proxibanque.proxibanquesi.tests;

import fr.proxibanque.proxibanquesi.service.GestionClientService;
import fr.proxibanque.proxibanquesi.service.ServiceProxibanqueImpl;

/**
 * Test suppression client 
 * Test couche service
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */
public class Test2SuppressionClient {
	public static void main(String[] args) {
		ServiceProxibanqueImpl gcs = new ServiceProxibanqueImpl();
		gcs.supprimerClient(2L);
	}

}
