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
		LOGGER.info("creation dun nouveau client depuis le service");
		CompteCourant cc = creerCompteCourant();
		newclient.setCompteCourant(cc);
		clientDao.creerClient(newclient);
		return Response.ok().build();
	}

	@Override
	public Client obtenirClient(long idClient) {
		LOGGER.info("recupération du client "+idClient +" depuis la couche service");
		return clientDao.obtenirClient(idClient);
	}
	
	@Override
	public List<Client> afficherListeClient() {
		return clientDao.obtenirTousLesClients();
	}

	@Override
	public Response modifierClient(Client clientModif) {
		clientDao.modifierClient(clientModif);
		return Response.ok().build();
	}

	@Override
	public Response supprimerClient(long idClient) {
		clientDao.supprimerClient(idClient);
		return Response.ok().build();
	}

	// *** COMPTES ***

	@Override
	public CompteCourant creerCompteCourant() {
		long numero = genererNumero();
		String dateOuverture = today();
		CompteCourant compteCourant = new CompteCourant(numero, 0.0, dateOuverture);
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

		if (soldeSrc - montant > DECOUVERT_MAX) {
			compteSrc.setSolde(soldeSrc - montant);
			compteDest.setSolde(soldeDest + montant);
		} else {
			// TODO Faire une erreur plus élaborée - throw ?
			System.err.println("Dépassement du découvert autorisé !");
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
		return compteepargne;
	}

	@Override
	public Response associerCompteEpargne(long idClient) {
		Client client = clientDao.obtenirClient(idClient);
		CompteEpargne compteepargne = creerCompteEpargne();
		client.setCompteEpargne(compteepargne);
		clientDao.modifierClient(client);
		return Response.ok().build();
	}
	
	@Override
	public List<Compte> listerComptesClient(long idClient) {
		Client client = clientDao.obtenirClient(idClient);
		ArrayList<Compte> listeComptes = new ArrayList<>();
		CompteCourant compteCourant = client.getCompteCourant();
		CompteEpargne compteEpargne = client.getCompteEpargne();
		if (compteCourant != null) {
			listeComptes.add(compteCourant);
		}
		if (compteEpargne != null) {
			listeComptes.add(compteEpargne);
		}
		return listeComptes;
	}
	
	// *** MOCKITO ***
	
	public void setDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

}
