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
 * sert au service : virements, etc.
 * 
 * @author Sandrine Le Mentec et Anthony Le Cigne
 *
 */
public interface SIService {

	/**
	 * @return le compte courant cree
	 */
	CompteCourant creerCompteCourant();

	/**
	 * creer et associe un compte epargne à un compte client
	 * @param idClient : id du client auquel est attribué le compte épargne
	 * @return reponse 200
	 */
	@POST
	@Path("/associercompteepargne/{idClient}")
	Response associerCompteEpargne(@PathParam("idClient") long idClient);

	/**
	 * réalise un virement compte à compte à partir d'un numéro de compte
	 * @param numeroCompteSrc : numéro du compte créditeur
	 * @param numeroCompteDest : numéro du compte crédité
	 * @param montant : montant à transférer
	 * @return reponse 200
	 */
	@POST
	@Path("/virement/{numSrc}/{numDest}/{montant}")
	Response faireVirement(@PathParam("numSrc") long numeroCompteSrc, @PathParam("numDest") long numeroCompteDest,
			@PathParam("montant") double montant);
	
	/**
	 * Liste les comptes d'un client
	 * @param idClient id du client 
	 * @return : la liste des Comptes du client
	 */
	@GET
	@Path("/listercomptes/{idClient}")
	List<Compte> listerComptesClient(@PathParam("idClient") long idClient);
	
	/**
	 * Crediter un compte. permet d'encaisser les chèques ou un apport en liquide par exemple
	 * @param numerocompte : numéro du compte à créditer
	 * @param montant : montant à créditer
	 * @return la réponse de la data base
	 */
	@PUT
	@Path("/creditercompte/{numerocompte}/{montant}")
	Response crediterCompte(@PathParam("numerocompte") long numerocompte, @PathParam("montant") double montant);

}
