package fr.proxibanque.proxibanquesi.service;

import javax.ws.rs.GET;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import fr.proxibanque.proxibanquesi.model.Client;

/**
 * Interface regroupant les opérations de gestion client (création, obtention,
 * modification et suppression) que peuvent effectuer les conseillers bancaires
 * de ProxiBanque.
 * 
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */

public interface GestionClientService {

	/**
	 * Crée un client ProxiBanque.
	 * 
	 * @param client
	 *            Un objet client
	 * @return Réponse HTTP
	 */
	@POST
	@Path("/client/")
	public Response creerClient(Client client);

	/**
	 * Retourne un client à partir de son identifiant.
	 * 
	 * @param idClient
	 *            Identifiant client
	 * @return Client recherché
	 */
	@GET
	@Path("/client/{idClient}")
	public Client obtenirClient(@PathParam("idClient") long idClient);

	/**
	 * Modifie les informations d'un client à partir d'un objet client similaire.
	 * 
	 * @param clientModif
	 *            L'objet client modifié
	 * @return Réponse HTTP
	 */
	@PUT
	@Path("/client/")
	public Response modifierClient(Client clientModif);

	/**
	 * Supprime un client à partir de son identifiant.
	 * 
	 * @param idClient
	 *            Identifiant client suppression du client
	 * @return Réponse HTTP
	 */
	@DELETE
	@Path("/client/{idClient}")
	public Response supprimerClient(@PathParam("idClient") long idClient);

	/**
	 * Liste l'ensemble des clients présents dans la base de données.
	 * 
	 * @return Liste d'objets client
	 */
	@GET
	@Path("/client/all")
	public List<Client> afficherListeClient();

}
