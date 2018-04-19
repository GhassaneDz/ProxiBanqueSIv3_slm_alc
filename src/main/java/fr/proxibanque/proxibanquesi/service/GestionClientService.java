package fr.proxibanque.proxibanquesi.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

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
	@POST
	@Path("/client/")
	public void creerClient(Client client);
	
	/**
	 * @return le client rechercher avec son id
	 */
	public Client afficherClient(long id);
	
	/**
	 * @param clientmodif
	 * @return
	 */
	public Client modifierClient(Client clientModif);
	
	/**
	 * 
	 */
	public void supprimerClient();

}
