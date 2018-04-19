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
 * Interface regroupant les op�rations de gestion client (cr�ation, obtention,
 * modification et suppression) que peuvent effectuer les conseillers bancaires
 * de ProxiBanque.
 * 
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */

public interface GestionClientService {

	/**
	 * Cr�e un client ProxiBanque.
	 * 
	 * @param client
	 *            Un objet client
	 * @return R�ponse HTTP
	 */
	@POST
	@Path("/client/")
	public Response creerClient(Client client);

	/**
	 * Retourne un client � partir de son identifiant.
	 * 
	 * @param idClient
	 *            Identifiant client
	 * @return Client recherch�
	 */
	@GET
	@Path("/client/{idClient}")
	public Client obtenirClient(@PathParam("idClient") long idClient);

	/**
	 * Modifie les informations d'un client � partir d'un objet client similaire.
	 * 
	 * @param clientModif
	 *            L'objet client modifi�
	 * @return R�ponse HTTP
	 */
	@PUT
	@Path("/client/")
	public Response modifierClient(Client clientModif);

	/**
	 * Supprime un client � partir de son identifiant.
	 * 
	 * @param idClient
	 *            Identifiant client suppression du client
	 * @return R�ponse HTTP
	 */
	@DELETE
	@Path("/client/{idClient}")
	public Response supprimerClient(@PathParam("idClient") long idClient);

	/**
	 * Liste l'ensemble des clients pr�sents dans la base de donn�es.
	 * 
	 * @return Liste d'objets client
	 */
	@GET
	@Path("/client/all")
	public List<Client> afficherListeClient();

}
