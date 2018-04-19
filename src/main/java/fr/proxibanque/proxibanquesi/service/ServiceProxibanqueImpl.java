package fr.proxibanque.proxibanquesi.service;

import fr.proxibanque.proxibanquesi.dao.ClientDao;
import fr.proxibanque.proxibanquesi.dao.ClientDaoImp;
import fr.proxibanque.proxibanquesi.model.Client;

public class ServiceProxibanqueImpl implements GestionClientService, SIService {

	ClientDao dao = new ClientDaoImp(); 
	@Override
	public void creerClient(Client newclient) {
		// TODO creer le client
		dao.creerClient(newclient);

	}

	@Override
	public Client afficherClient(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client modifierClient(Client clientModif) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerClient() {
		// TODO Auto-generated method stub

	}

}
