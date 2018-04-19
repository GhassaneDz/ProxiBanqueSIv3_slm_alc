package fr.proxibanque.proxibanquesi.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

	CompteCourant creerCompteCourant();

	@POST
	@Path("/associercompteepargne/{idClient}")
	Response associerCompteEpargne(@PathParam("idClient") long idClient);

	@POST
	@Path("/virement/{numSrc}/{numDest}/{montant}")
	Response faireVirement(@PathParam("numSrc") long numeroCompteSrc, @PathParam("numDest") long numeroCompteDest,
			@PathParam("montant") double montant);
	
	@GET
	@Path("/listercomptes/{idClient}")
	List<Compte> listerComptesClient(@PathParam("idClient") long idClient);

}
