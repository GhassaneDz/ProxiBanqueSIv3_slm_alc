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
 * Gestion Client : CRUD Gestion des comptes
 * 
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */

public interface GestionClientService {

	/**
	 * creer un client en appelant l'interface DAO
	 * 
	 * @param client
	 */
	@POST
	@Path("/client/")
	public Response creerClient(Client client);

	/**
	 * @return le client recherché à partir de son id
	 */
	@GET
	@Path("/client/{idClient}")
	public Client obtenirClient(@PathParam("idClient") long idClient);

	/**
	 * @param clientmodif
	 * @return
	 */
	@PUT
	@Path("/client/")
	public Response modifierClient(Client clientModif);

	/**
	 * suppression du client
	 */
	@DELETE
	@Path("/client/{idClient}")
	public Response supprimerClient(@PathParam("idClient") long idClient);

	@GET
	@Path("/client/all")
	public List<Client> afficherListeClient();


}
