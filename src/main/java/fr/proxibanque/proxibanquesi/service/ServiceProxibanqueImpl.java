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
import fr.proxibanque.proxibanquesi.dao.ConseillerDao;
import fr.proxibanque.proxibanquesi.dao.ConseillerDaoImp;
import fr.proxibanque.proxibanquesi.model.CarteBancaire;
import fr.proxibanque.proxibanquesi.model.Client;
import fr.proxibanque.proxibanquesi.model.Compte;
import fr.proxibanque.proxibanquesi.model.CompteCourant;
import fr.proxibanque.proxibanquesi.model.CompteEpargne;
import fr.proxibanque.proxibanquesi.model.Conseiller;
import fr.proxibanque.proxibanquesi.util.UtilitaireLogger;

public class ServiceProxibanqueImpl implements GestionClientService, SIService, ConseillerService {

	/**
	 * Règle métier : découvert maximal autorisé sur tout type de compte.
	 */
	private static final double DECOUVERT_MAX = -1000.0;

	ClientDao clientDao = new ClientDaoImp();
	CompteDao compteDao = new CompteDaoImp();
	ConseillerDao conseillerDao = new ConseillerDaoImp();
	Logger LOGGER = UtilitaireLogger.LOGGER;

	// *** CLIENTS ***

	@Override
	public Response creerClient(Client newclient) {
		LOGGER.debug("creation d'un nouveau client depuis la couche service");
		CompteCourant cc = creerCompteCourant();
		newclient.setCompteCourant(cc);
		clientDao.creerClient(newclient);
		return Response.ok().build();
	}

	@Override
	public Client obtenirClient(long idClient) {
		LOGGER.debug("appel du client " + idClient + " depuis la couche service");
		return clientDao.obtenirClient(idClient);
	}

	@Override
	public List<Client> afficherListeClient() {
		LOGGER.debug("recuperation de la liste de tous les clients depuis la couche service");
		return clientDao.obtenirTousLesClients();
	}

	@Override
	public Response modifierClient(Client clientModif) {
		LOGGER.debug("modification du client " + clientModif.getIdClient() + " depuis la couche service");
		clientDao.modifierClient(clientModif);
		return Response.ok().build();
	}

	@Override
	public Response supprimerClient(long idClient) {
		LOGGER.debug("appel pour la suppression du client " + idClient + "depuis la couche service");
		clientDao.supprimerClient(idClient);
		return Response.ok().build();
	}

	// *** COMPTES ***

	/**
	 * Crée un objet compte courant avec un numéro de compte aléatoire à neuf
	 * chiffres.
	 * 
	 * @return Object CompteCourant
	 */
	private CompteCourant creerCompteCourant() {
		long numero = genererNumero();
		String dateOuverture = today();
		CompteCourant compteCourant = new CompteCourant(numero, 0.0, dateOuverture);
		LOGGER.debug("creation du Compte Courant n°" + numero);
		return compteCourant;
	}

	/**
	 * Génère un numéro de compte aléatoire à neuf chiffres.
	 * 
	 * @return Numéro aléatoire à neuf chiffres
	 */
	private long genererNumero() {
		long randomNumber = (long) (Math.random() * 1_000_000_000);
		return randomNumber;
	}

	/**
	 * Génère la date du jour sous forme d'une chaîne de caractères.
	 * 
	 * @return Date courant au format ISO8601
	 */
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
		LOGGER.info("Tentative de virement du compte n°" + numeroCompteSrc + " vers le compte n°" + numeroCompteDest
				+ "d'un montant de " + montant);
		if (soldeSrc - montant > DECOUVERT_MAX) {
			compteSrc.setSolde(soldeSrc - montant);
			compteDest.setSolde(soldeDest + montant);
			LOGGER.debug("Virement du compte n°" + numeroCompteSrc + " vers le compte n°" + numeroCompteDest
					+ "d'un montant de " + montant + " reussi");
		} else {
			// TODO Faire une erreur plus élaborée - throw ?
			// System.err.println("Dépassement du découvert autorisé !");
			LOGGER.error("Virement refusé. Dépassement du découvert autorisé pour le compte n°" + numeroCompteSrc);
		}

		compteDao.modifierCompte(compteSrc);
		compteDao.modifierCompte(compteDest);

		return Response.ok().build();

	}

	@Override
	public Response associerCompteEpargne(long idClient) {
		Client client = clientDao.obtenirClient(idClient);
		CompteEpargne compteepargne = creerCompteEpargne();
		client.setCompteEpargne(compteepargne);
		clientDao.modifierClient(client);
		LOGGER.debug("association du compte epargne n°" + compteepargne.getNumeroCompte() + " au client n°" + idClient);
		return Response.ok().build();
	}

	/**
	 * Crée un objet compte épargne avec un numéro de compte aléatoire à neuf
	 * chiffres.
	 * 
	 * @return Object CompteCourant
	 */
	private CompteEpargne creerCompteEpargne() {
		long numero = genererNumero();
		double tauxrenum = 0.03;
		String dateOuverture = today();
		CompteEpargne compteepargne = new CompteEpargne(numero, 0.0, dateOuverture, tauxrenum);
		LOGGER.debug("creation du Compte Epargne n°" + numero);
		return compteepargne;
	}

	@Override
	public List<Compte> listerComptesClient(long idClient) {
		Client client = clientDao.obtenirClient(idClient);
		ArrayList<Compte> listeComptes = new ArrayList<>();
		CompteCourant compteCourant = client.getCompteCourant();
		CompteEpargne compteEpargne = client.getCompteEpargne();
		LOGGER.debug("recuperation des ccomptes du client " + idClient);
		if (compteCourant != null) {
			listeComptes.add(compteCourant);
			LOGGER.error("erreur de listerComteClient : le Client " + idClient + "n'a pas de compte courant");
		}
		if (compteEpargne != null) {
			listeComptes.add(compteEpargne);
			LOGGER.error("erreur de listerComteClient : le Client " + idClient + "n'a pas de compte epargne");
		}
		return listeComptes;
	}

	@Override
	public Response crediterCompte(long numeroCompte, double montant) {
		LOGGER.debug("Apport sur le compte n°" + numeroCompte);
		Compte compteCredite = compteDao.obtenirCompte(numeroCompte);
		compteCredite.setSolde(compteCredite.getSolde() + montant);
		compteDao.modifierCompte(compteCredite);
		return Response.ok().build();
	}

	private CarteBancaire creerCarteBancaire() {
		long numero = genererNumero();
		String type = "";
		CarteBancaire cb = new CarteBancaire(numero, type);
		return cb;
	};

	@Override
	public Response associerCB(long numeroCompte, String type) {
		CompteCourant cc = (CompteCourant) compteDao.obtenirCompte(numeroCompte);
		CarteBancaire cb = cc.getCarte();
		if (cb.equals(null)) {
			cb = creerCarteBancaire();
			if (type.equals("e")) {
				cb.setType(CarteBancaire.ELECTRON);
				cc.setCarte(cb);
				compteDao.modifierCompte(cc);
			} else if (type.equals("v")) {
				cb.setType(CarteBancaire.VISA);
				cc.setCarte(cb);
				compteDao.modifierCompte(cc);
			}
			return Response.ok().build();
		} else {
			LOGGER.error("le compte n°" + numeroCompte + " a déjà une carte associée");
			return Response.notModified("compte deja associe à une carte").build();
		}

	}
	
	//****AUDIT****
	
	@Override
	public List<Client> AuditAgence() {
		// TODO Auto-generated method stub
		List<Client> listeTousClient=afficherListeClient();
		List<Client> listeaudit=new ArrayList<>();
		for (Client client : listeTousClient) {
			CompteCourant cc = client.getCompteCourant();
			CompteEpargne ce = client.getCompteEpargne();
			if (ce==(null)) {
				ce=new CompteEpargne();
				ce.setSolde(0.0);
			}
			double soldecc = cc.getSolde();
			double soldece = ce.getSolde();
				if (soldecc < -5000.0 || soldece < -5000.0) {
					listeaudit.add(client);
				}
		}
		
		return listeaudit;
	}


	// *** CONSEILLERS ***

	@Override
	public void creerConseiller(Conseiller conseiller) {
		if (conseiller != null) {
			conseillerDao.creerConseiller(conseiller);
		}
	}

	@Override
	public Conseiller obtenirConseillerParAuth(String login, String password) {
		Conseiller conseiller = obtenirConseillerParLogin(login);
		if (conseiller != null && pwdIsCorrect(conseiller, password)) {
			return conseiller;
		} else {
			return null;
		}
	}

	// Helpers

	private Conseiller obtenirConseillerParLogin(String login) {
		return conseillerDao.obtenirConseillerParLogin(login);
	}

	/**
	 * @param Conseiller
	 * @param password
	 * @return
	 */
	private boolean pwdIsCorrect(Conseiller Conseiller, String password) {
		boolean answer = false;
		if (Conseiller.getPassword().equals(password)) {
			answer = true;
		}
		return answer;
	}

	// *** MOCKITO ***

	/**
	 * Permet d'attribuer une DAO choisie à l'instance de la présente classe. Utile
	 * pour associer une DAO simulée par Mockito.
	 * 
	 * @param clientDao
	 */
	public void setDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}


}
