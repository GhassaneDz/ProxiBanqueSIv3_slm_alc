package fr.proxibanque.proxibanquesi.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import fr.proxibanque.proxibanquesi.dao.ClientDao;
import fr.proxibanque.proxibanquesi.dao.ClientDaoImp;
import fr.proxibanque.proxibanquesi.dao.CompteDao;
import fr.proxibanque.proxibanquesi.dao.CompteDaoImp;
import fr.proxibanque.proxibanquesi.model.Client;
import fr.proxibanque.proxibanquesi.model.Compte;
import fr.proxibanque.proxibanquesi.model.CompteCourant;
import fr.proxibanque.proxibanquesi.model.CompteEpargne;
import fr.proxibanque.proxibanquesi.util.UtilitaireLogger;


public class ServiceProxibanqueImpl implements GestionClientService, SIService {

	private static final double DECOUVERT_MAX = -1000.0;
	
	ClientDao clientDao = new ClientDaoImp();
	CompteDao compteDao = new CompteDaoImp();
	Logger LOGGER=UtilitaireLogger.LOGGER;

	// *** CLIENTS ***

	@Override
	public Response creerClient(Client newclient) {
		LOGGER.debug("creation dun nouveau client depuis la couche service");
		CompteCourant cc = creerCompteCourant();
		newclient.setCompteCourant(cc);
		clientDao.creerClient(newclient);
		return Response.ok().build();
	}

	@Override
	public Client obtenirClient(long idClient) {
		LOGGER.debug("appel du client "+idClient +" depuis la couche service");
		return clientDao.obtenirClient(idClient);
	}
	
	@Override
	public List<Client> afficherListeClient() {
		LOGGER.debug("recuperation de la liste de tous les clients depuis la couche service");
		return clientDao.obtenirTousLesClients();
	}

	@Override
	public Response modifierClient(Client clientModif) {
		LOGGER.debug("modification du client "+ clientModif.getIdClient()+" depuis la couche service");
		clientDao.modifierClient(clientModif);
		return Response.ok().build();
	}

	@Override
	public Response supprimerClient(long idClient) {
		LOGGER.debug("appel pour la suppression du client "+idClient+"depuis la couche service");
		clientDao.supprimerClient(idClient);
		return Response.ok().build();
	}

	// *** COMPTES ***

	@Override
	public CompteCourant creerCompteCourant() {
		long numero = genererNumero();
		String dateOuverture = today();
		CompteCourant compteCourant = new CompteCourant(numero, 0.0, dateOuverture);
		LOGGER.debug("creation du Compte Courant n°"+numero);
		return compteCourant;
	}

	private long genererNumero() {
		long randomNumber = (long) (Math.random() * 1_000_000_000);
		return randomNumber;
	}

	private String today() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return sdfDate.format(date);
	}

	@Override
	public Response faireVirement(long numeroCompteSrc, long numeroCompteDest, double montant) {
		Compte compteSrc = compteDao.obtenirCompte(numeroCompteSrc);
		Compte compteDest = compteDao.obtenirCompte(numeroCompteDest);
		
		double soldeSrc = compteSrc.getSolde();
		double soldeDest = compteDest.getSolde();
		LOGGER.info("tentative virement du compte n°"+numeroCompteSrc+" vers le compte n°"+numeroCompteDest+"d'un montant de "+montant);
		if (soldeSrc - montant > DECOUVERT_MAX) {
			compteSrc.setSolde(soldeSrc - montant);
			compteDest.setSolde(soldeDest + montant);
			LOGGER.debug("virement du compte n°"+numeroCompteSrc+" vers le compte n°"+numeroCompteDest+"d'un montant de "+montant+" reussi");
		} else {
			// TODO Faire une erreur plus élaborée - throw ?
			//System.err.println("Dépassement du découvert autorisé !");
			LOGGER.error("Virement refusé. Dépassement du découvert autorisé pour le compte n°"+numeroCompteSrc);
		}
		
		compteDao.modifierCompte(compteSrc);
		compteDao.modifierCompte(compteDest);
		
		return Response.ok().build();
		
	}
		
	 private CompteEpargne creerCompteEpargne() {
		long numero = genererNumero();
		double tauxrenum = 0.03;
		String dateOuverture = today();
		CompteEpargne compteepargne = new CompteEpargne(numero, 0.0, dateOuverture, tauxrenum);
		LOGGER.debug("creation du Compte Epargne n°"+numero);
		return compteepargne;
	}

	@Override
	public Response associerCompteEpargne(long idClient) {
		Client client = clientDao.obtenirClient(idClient);
		CompteEpargne compteepargne = creerCompteEpargne();
		client.setCompteEpargne(compteepargne);
		clientDao.modifierClient(client);
		LOGGER.debug("association du compte epargne n°"+ compteepargne.getNumeroCompte() + " au client n°"+idClient);
		return Response.ok().build();
	}
	
	@Override
	public List<Compte> listerComptesClient(long idClient) {
		Client client = clientDao.obtenirClient(idClient);
		ArrayList<Compte> listeComptes = new ArrayList<>();
		CompteCourant compteCourant = client.getCompteCourant();
		CompteEpargne compteEpargne = client.getCompteEpargne();
		LOGGER.debug("recuperation des ccomptes du client "+idClient);
		if (compteCourant != null) {
			listeComptes.add(compteCourant);
			LOGGER.error("erreur de listerComteClient : le Client "+idClient+"n'a pas de compte courant");
		}
		if (compteEpargne != null) {
			listeComptes.add(compteEpargne);
			LOGGER.error("erreur de listerComteClient : le Client "+idClient+"n'a pas de compte epargne");
		}
		return listeComptes;
	}
	
	@Override
	public Response crediterCompte(long numerocompte, double montant) {
		LOGGER.debug("apport sur le compte n°" + numerocompte);
		Compte comptecredite = compteDao.obtenirCompte(numerocompte);
		comptecredite.setSolde(comptecredite.getSolde()+montant);
		compteDao.modifierCompte(comptecredite);
		return Response.ok().build();
	}

	
	// *** MOCKITO ***
	
	public void setDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}


}
