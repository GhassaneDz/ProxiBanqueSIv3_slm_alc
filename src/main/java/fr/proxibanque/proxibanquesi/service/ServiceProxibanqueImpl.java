package fr.proxibanque.proxibanquesi.service;

import javax.ws.rs.core.Response;

import fr.proxibanque.proxibanquesi.dao.ClientDao;
import fr.proxibanque.proxibanquesi.dao.ClientDaoImp;
import fr.proxibanque.proxibanquesi.model.Client;

public class ServiceProxibanqueImpl implements GestionClientService, SIService {

	ClientDao clientDao = new ClientDaoImp();

	@Override
	public Response creerClient(Client newclient) {
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

}
