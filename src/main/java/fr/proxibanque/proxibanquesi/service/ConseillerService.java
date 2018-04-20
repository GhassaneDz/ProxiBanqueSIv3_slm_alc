package fr.proxibanque.proxibanquesi.service;

import fr.proxibanque.proxibanquesi.model.Conseiller;

/**
 * Interface regroupant les opérations impliquant les objets Conseiller.
 * 
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */

public interface ConseillerService {

	void creerConseiller(Conseiller conseiller);

	/**
	 * Permet d'obtenir un conseiller si son authentification est valide.
	 * 
	 * @param login
	 *            Login conseiller
	 * @param password
	 *            Mot de passe conseiller
	 * @return Un objet conseiller si l'authentification est valide, sinon null.
	 */
	Conseiller obtenirConseillerParAuth(String login, String password);

}
