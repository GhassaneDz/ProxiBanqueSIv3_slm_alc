package fr.proxibanque.proxibanquesi.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.hibernate.dialect.DataDirectOracle9Dialect;

import fr.proxibanque.proxibanquesi.dao.ClientDao;
import fr.proxibanque.proxibanquesi.dao.ClientDaoImp;
import fr.proxibanque.proxibanquesi.model.Client;
import fr.proxibanque.proxibanquesi.model.CompteCourant;
import fr.proxibanque.proxibanquesi.model.CompteEpargne;


public class ServiceProxibanqueImpl implements GestionClientService, SIService {

	ClientDao clientDao = new ClientDaoImp();

	// *** CLIENTS ***
	
	@Override
	public Response creerClient(Client newclient) {
		CompteCourant cc = creerCompteCourant();
		newclient.setCompteCourant(cc);
		clientDao.creerClient(newclient);
		return Response.ok().build();
	}

	@Override
	public Client obtenirClient(long idClient) {
		return clientDao.obtenirClient(idClient);
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
	public List<Client> afficherListeClient() {
		return clientDao.obtenirTousLesClients();
	}

	
	 private CompteEpargne creerCompteEpargne() {
		long numero = genererNumero();
		double tauxrenum = 0.3;
		String dateOuverture = today();
		CompteEpargne compteepargne = new CompteEpargne(numero, 0.0, dateOuverture, tauxrenum);
		return compteepargne;
	}

	@Override
	public Response associerCompteEpargne(long idClient) {
		//Client client = obtenirClient(idClient);
		Client client = clientDao.obtenirClient(idClient);
		CompteEpargne compteepargne = creerCompteEpargne();
		client.setCompteEpargne(compteepargne);
		clientDao.modifierClient(client);
		//modifierClient(client);
		return Response.ok().build();
	}

}
