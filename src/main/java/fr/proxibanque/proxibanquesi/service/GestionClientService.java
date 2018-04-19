package fr.proxibanque.proxibanquesi.service;
import fr.proxibanque.proxibanquesi.model.Client;

public interface GestionClientService {
	public void creerClient();
	public Client afficherClient();
	public Client modifierClient(Client clientmodif);
	public void supprimerClient();
	
	
	
	
	

}
