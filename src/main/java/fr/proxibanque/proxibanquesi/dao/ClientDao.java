package fr.proxibanque.proxibanquesi.dao;

import fr.proxibanque.proxibanquesi.model.Client;

public interface ClientDao {

	void creerClient(Client client);
	Client updateClientById(long id, Client clientmodif);
	
}
