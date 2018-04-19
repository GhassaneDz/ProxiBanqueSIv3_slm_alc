package fr.proxibanque.proxibanquesi.service;
import fr.proxibanque.proxibanquesi.model.Client;

/**
 * Gestion Client : CRUD 
 * @author Sandrine
 *
 */
public interface GestionClientService {
	/**
	 * creer un client en appelant l'interface DAO
	 * @param newclient
	 */
	public void creerClient(Client newclient);
	/**
	 * @return le client rechercher avec son id
	 */
	public Client afficherClient(long id);
	/**
	 * @param clientmodif
	 * @return
	 */
	public Client modifierClient(Client clientmodif);
	/**
	 * 
	 */
	public void supprimerClient();
	
	
	
	
	

}
