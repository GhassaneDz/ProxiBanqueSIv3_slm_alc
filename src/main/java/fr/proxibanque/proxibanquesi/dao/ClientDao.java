package fr.proxibanque.proxibanquesi.dao;

import java.util.List;

import fr.proxibanque.proxibanquesi.model.Client;

public interface ClientDao {

	void creerClient(Client client);
	
	Client obtenirClient(long idClient);
	
	void modifierClient(Client client);
	
	void supprimerClient(long idClient);
	
	List<Client> obtenirTousLesClients();
	
}
