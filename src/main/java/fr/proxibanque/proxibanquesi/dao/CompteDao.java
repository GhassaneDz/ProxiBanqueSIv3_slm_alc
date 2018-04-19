package fr.proxibanque.proxibanquesi.dao;

import fr.proxibanque.proxibanquesi.model.Compte;

/**
 * Op�rations g�n�rales li�es � la persistance des donn�es compte (CRUD -
 * Create, Read, Update, Delete).
 *
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */

public interface CompteDao {

	/**
	 * Retourne un objet Compte � partir d'un num�ro de compte � 9 chiffres.
	 * 
	 * @param numeroCompte
	 *            Num�ro de compte
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
