package fr.proxibanque.proxibanquesi.dao;

import fr.proxibanque.proxibanquesi.model.Conseiller;

/**
 * Subset du CRUD pour les données conseillers. L'obtention par login permet au
 * système d'authentification de ProxiBanque de fonctionner.
 *
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */

public interface ConseillerDao {

	/**
	 * Crée un conseiller ProxiBanque.
	 * 
	 * @param conseiller
	 */
	// Utilisable par un futur gérant, utile ici pour des tests.
	void creerConseiller(Conseiller conseiller);

	/**
	 * Permet d'obtenir un objet conseiller par son login.
	 * 
	 * @param login
	 *            Login conseiller
	 * @return Objet conseiller
	 */
	Conseiller obtenirConseillerParLogin(String login);

}
