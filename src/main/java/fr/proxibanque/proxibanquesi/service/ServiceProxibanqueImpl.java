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
	 * R�gle m�tier : d�couvert maximal autoris� sur tout type de compte.
	 */
	private static final double DECOUVERT_MAX = -1000.0;

	private ClientDao clientDao = new ClientDaoImp();
	private CompteDao compteDao = new CompteDaoImp();
	private ConseillerDao conseillerDao = new ConseillerDaoImp();
	/**
	 * utilitaire pour logger les utilisations de services
	 */
	private Logger logger = UtilitaireLogger.LOGGER;
	/**
	 * regle m�tier : d�finition du seuil de l'audit
	 */
	private static double seuilAudit =-5000; 

	// *** CLIENTS ***

	@Override
	public Response creerClient(Client newclient) {
		logger.debug("creation d'un nouveau client depuis la couche service");
		CompteCourant cc = creerCompteCourant();
		newclient.setCompteCourant(cc);
		clientDao.creerClient(newclient);
		return Response.ok().build();
	}

	@Override
	public Client obtenirClient(long idClient) {
		logger.debug("appel du client " + idClient + " depuis la couche service");
		return clientDao.obtenirClient(idClient);
	}

	@Override
	public List<Client> afficherListeClient() {
		logger.debug("recuperation de la liste de tous les clients depuis la couche service");
		return clientDao.obtenirTousLesClients();
	}

	@Override
	public Response modifierClient(Client clientModif) {
		logger.debug("modification du client " + clientModif.getIdClient() + " depuis la couche service");
		clientDao.modifierClient(clientModif);
		return Response.ok().build();
	}

	@Override
	public Response supprimerClient(long idClient) {
		logger.debug("appel pour la suppression du client " + idClient + "depuis la couche service");
		clientDao.supprimerClient(idClient);
		return Response.ok().build();
	}

	// *** COMPTES ***

	/**
	 * Cr�e un objet compte courant avec un num�ro de compte al�atoire � neuf
	 * chiffres.
	 * 
	 * @return Object CompteCourant
	 */
	private CompteCourant creerCompteCourant() {
		long numero = genererNumero();
		String dateOuverture = today();
		CompteCourant compteCourant = new CompteCourant(numero, 0.0, dateOuverture);
		logger.debug("creation du Compte Courant n�" + numero);
		return compteCourant;
	}

	/**
	 * G�n�re un num�ro de compte al�atoire � neuf chiffres.
	 * 
	 * @return Num�ro al�atoire � neuf chiffres
	 */
	private long genererNumero() {
		long randomNumber = (long) (Math.random() * 1_000_000_000);
		return randomNumber;
	}

	/**
	 * G�n�re la date du jour sous forme d'une cha�ne de caract�res.
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
		logger.info("Tentative de virement du compte n�" + numeroCompteSrc + " vers le compte n�" + numeroCompteDest
				+ "d'un montant de " + montant);
		if (soldeSrc - montant > DECOUVERT_MAX) {
			compteSrc.setSolde(soldeSrc - montant);
			compteDest.setSolde(soldeDest + montant);
			logger.debug("Virement du compte n�" + numeroCompteSrc + " vers le compte n�" + numeroCompteDest
					+ "d'un montant de " + montant + " reussi");
		} else {
			// TODO Faire une erreur plus �labor�e - throw ?
			// System.err.println("D�passement du d�couvert autoris� !");
			logger.error("Virement refus�. D�passement du d�couvert autoris� pour le compte n�" + numeroCompteSrc);
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
		logger.debug("association du compte epargne n�" + compteepargne.getNumeroCompte() + " au client n�" + idClient);
		return Response.ok().build();
	}

	/**
	 * Cr�e un objet compte �pargne avec un num�ro de compte al�atoire � neuf
	 * chiffres.
	 * 
	 * @return Object CompteCourant
	 */
	private CompteEpargne creerCompteEpargne() {
		long numero = genererNumero();
		double tauxrenum = 0.03;
		String dateOuverture = today();
		CompteEpargne compteepargne = new CompteEpargne(numero, 0.0, dateOuverture, tauxrenum);
		logger.debug("creation du Compte Epargne n�" + numero);
		return compteepargne;
	}

	@Override
	public List<Compte> listerComptesClient(long idClient) {
		Client client = clientDao.obtenirClient(idClient);
		ArrayList<Compte> listeComptes = new ArrayList<>();
		CompteCourant compteCourant = client.getCompteCourant();
		CompteEpargne compteEpargne = client.getCompteEpargne();
		logger.debug("recuperation des ccomptes du client " + idClient);
		if (compteCourant != null) {
			listeComptes.add(compteCourant);
			logger.error("erreur de listerComteClient : le Client " + idClient + "n'a pas de compte courant");
		}
		if (compteEpargne != null) {
			listeComptes.add(compteEpargne);
			logger.error("erreur de listerComteClient : le Client " + idClient + "n'a pas de compte epargne");
		}
		return listeComptes;
	}

	@Override
	public Response crediterCompte(long numeroCompte, double montant) {
		logger.debug("Apport sur le compte n�" + numeroCompte);
		Compte compteCredite = compteDao.obtenirCompte(numeroCompte);
		compteCredite.setSolde(compteCredite.getSolde() + montant);
		compteDao.modifierCompte(compteCredite);
		return Response.ok().build();
	}

	/**G�n�ration d'un CB sans type avec un num�ro al�atoire � 9 chiffres
	 * @return CB : un objet carte bancaire
	 */
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
			logger.error("le compte n�" + numeroCompte + " a d�j� une carte associ�e");
			return Response.notModified("compte deja associe � une carte").build();
		}

	}
	
	//****AUDIT****
	
	@Override
	public List<Client> auditAgence() {
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
				if (soldecc < seuilAudit || soldece < seuilAudit) {
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
	 * @param Conseiller qui se connecte 
	 * @param password du conseiller
	 * @return true si identification conseiller ok, false si identification non ok
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
	 * Permet d'attribuer une DAO choisie � l'instance de la pr�sente classe. Utile
	 * pour associer une DAO simul�e par Mockito.
	 * 
	 * @param clientDao dao client associ� au service (mock�e ou non)
	 */
	public void setDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}


}
