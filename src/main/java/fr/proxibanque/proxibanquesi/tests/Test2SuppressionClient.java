package fr.proxibanque.proxibanquesi.tests;

import fr.proxibanque.proxibanquesi.service.GestionClientService;
import fr.proxibanque.proxibanquesi.service.ServiceProxibanqueImpl;

public class Test2SuppressionClient {
	public static void main(String[] args) {
		ServiceProxibanqueImpl gcs = new ServiceProxibanqueImpl();
		gcs.supprimerClient(2L);
	}

}
