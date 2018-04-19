package fr.proxibanque.proxibanquesi.dao;

import java.util.List;

import fr.proxibanque.proxibanquesi.model.Client;

/**
 * Opérations générales liées à la persistance des données clients (CRUD -
 * Create, Read, Update, Delete).
 *
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */

public interface ClientDao {

	/**
	 * Persiste un client.
	 * 
	 * @param client
	 *            Objet client à persister
	 */
	void creerClient(Client client);

	/**
	 * Retourne un objet Client à partir de son identifiant.
	 * 
	 * @param idClient
	 *            Identifiant du client
	 * @return Objet compte
	 */
	Client obtenirClient(long idClient);

	/**
	 * Modifie un objet Client existant.
	 * 
	 * @param client
	 *            Objet client modifié, à persister
	 */
	void modifierClient(Client client);

	/**
	 * Supprime un client du système de persistance.
	 * 
	 * @param idClient
	 *            Identifiant du client
	 */
	void supprimerClient(long idClient);

	/**
	 * Retourne un objet de type ArrayList contenant l'ensemble des clients.
	 * 
	 * @return ArrayList contenant les objets clients.
	 */
	List<Client> obtenirTousLesClients();

}
