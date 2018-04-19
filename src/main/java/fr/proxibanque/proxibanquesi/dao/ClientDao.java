package fr.proxibanque.proxibanquesi.dao;

import fr.proxibanque.proxibanquesi.model.Client;

public interface ClientDao {

	void creerClient(Client client);
	
	Client obtenirClient(long idClient);
	
	void modifierClient(Client client);
	
	void supprimerClient(long idClient);
	
}
