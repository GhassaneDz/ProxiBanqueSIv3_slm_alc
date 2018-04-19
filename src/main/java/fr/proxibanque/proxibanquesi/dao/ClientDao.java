package fr.proxibanque.proxibanquesi.dao;

import fr.proxibanque.proxibanquesi.model.Client;

public interface ClientDao {

	void creerClient(Client client);
	
	Client obtenirClient(long idClient);
	
	void supprimerClient(long idClient);
	
}
