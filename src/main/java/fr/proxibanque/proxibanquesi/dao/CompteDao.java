package fr.proxibanque.proxibanquesi.dao;

import fr.proxibanque.proxibanquesi.model.Compte;

/**
 * Opérations générales liées à la persistance des données compte (CRUD -
 * Create, Read, Update, Delete).
 *
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */

public interface CompteDao {

	/**
	 * Retourne un objet Compte à partir d'un numéro de compte à 9 chiffres.
	 * 
	 * @param numeroCompte
	 *            Numéro de compte
	 * @return Objet compte
	 */
	Compte obtenirCompte(long numeroCompte);

	/**
	 * Modifie un objet Compte existant.
	 * 
	 * @param compte
	 *            Nouveau compte
	 */
	void modifierCompte(Compte compte);

}
