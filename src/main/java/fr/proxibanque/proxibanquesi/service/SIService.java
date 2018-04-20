package fr.proxibanque.proxibanquesi.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import fr.proxibanque.proxibanquesi.model.Compte;
import fr.proxibanque.proxibanquesi.model.CompteCourant;

/**
 * Interface regroupant des opérations plus élaborées sur les clients.
 * 
 * @author Sandrine Le Mentec et Anthony Le Cigne
 *
 */
public interface SIService {

	/**
	 * Crée et associe un compte épargne à un client.
	 * 
	 * @param idClient
	 *            ID du client auquel est attribué le compte épargne
	 * @return Réponse HTTP
	 */
	@POST
	@Path("/associercompteepargne/{idClient}")
	Response associerCompteEpargne(@PathParam("idClient") long idClient);

	/**
	 * Réalise un virement compte à compte à partir d'un numéro de compte.
	 * 
	 * @param numeroCompteSrc
	 *            Numéro du compte créditeur
	 * @param numeroCompteDest
	 *            Numéro du compte crédité
	 * @param montant
	 *            Montant à transférer
	 * @return Réponse HTTP
	 */
	@POST
	@Path("/virement/{numSrc}/{numDest}/{montant}")
	Response faireVirement(@PathParam("numSrc") long numeroCompteSrc, @PathParam("numDest") long numeroCompteDest,
			@PathParam("montant") double montant);

	/**
	 * Liste les comptes d'un client.
	 * 
	 * @param idClient
	 *            ID du client
	 * @return Liste des comptes du client
	 */
	@GET
	@Path("/listercomptes/{idClient}")
	List<Compte> listerComptesClient(@PathParam("idClient") long idClient);

	/**
	 * Credite un compte. Permet d'encaisser les chèques ou un apport en liquide par
	 * exemple.
	 * 
	 * @param numerocompte
	 *            Numéro du compte à créditer
	 * @param montant
	 *            Montant à créditer
	 * @return Réponse HTTP
	 */
	@PUT
	@Path("/creditercompte/{numerocompte}/{montant}")
	Response crediterCompte(@PathParam("numerocompte") long numerocompte, @PathParam("montant") double montant);
	
	@PUT
	@Path("/associerCB/{numerocompte}/{type}")
	Response associerCB(@PathParam("numerocompte") long numerocompte, @PathParam("type")String type);

}
