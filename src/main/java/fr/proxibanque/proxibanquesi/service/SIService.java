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
 * Interface regroupant des op�rations plus �labor�es sur les clients.
 * 
 * @author Sandrine Le Mentec et Anthony Le Cigne
 *
 */
public interface SIService {

	/**
	 * Cr�e et associe un compte �pargne � un client.
	 * 
	 * @param idClient
	 *            ID du client auquel est attribu� le compte �pargne
	 * @return R�ponse HTTP
	 */
	@POST
	@Path("/associercompteepargne/{idClient}")
	Response associerCompteEpargne(@PathParam("idClient") long idClient);

	/**
	 * R�alise un virement compte � compte � partir d'un num�ro de compte.
	 * 
	 * @param numeroCompteSrc
	 *            Num�ro du compte cr�diteur
	 * @param numeroCompteDest
	 *            Num�ro du compte cr�dit�
	 * @param montant
	 *            Montant � transf�rer
	 * @return R�ponse HTTP
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
	 * Credite un compte. Permet d'encaisser les ch�ques ou un apport en liquide par
	 * exemple.
	 * 
	 * @param numerocompte
	 *            Num�ro du compte � cr�diter
	 * @param montant
	 *            Montant � cr�diter
	 * @return R�ponse HTTP
	 */
	@PUT
	@Path("/creditercompte/{numerocompte}/{montant}")
	Response crediterCompte(@PathParam("numerocompte") long numerocompte, @PathParam("montant") double montant);
	
	@PUT
	@Path("/associerCB/{numerocompte}/{type}")
	Response associerCB(@PathParam("numerocompte") long numerocompte, @PathParam("type")String type);

}
