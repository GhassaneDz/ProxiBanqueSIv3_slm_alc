package fr.proxibanque.proxibanquesi.tests;

import java.util.List;

import fr.proxibanque.proxibanquesi.model.Client;
import fr.proxibanque.proxibanquesi.service.GestionClientService;
import fr.proxibanque.proxibanquesi.service.ServiceProxibanqueImpl;

/**
 * Test de récupération de la liste complete des clients
 * Test de la partie service
 * @author Sandrine Le Mentec, Anthony Le Cigne
 * 
 *
 */
public class Test1RecuperationListeClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestionClientService service = new ServiceProxibanqueImpl();
		List<Client> a = service.afficherListeClient();
		System.out.println(a);

	}

}
