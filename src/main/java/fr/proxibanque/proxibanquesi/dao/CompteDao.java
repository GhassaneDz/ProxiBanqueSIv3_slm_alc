package fr.proxibanque.proxibanquesi.dao;

import fr.proxibanque.proxibanquesi.model.Compte;

public interface CompteDao {

	Compte obtenirCompte(long numeroCompte);
	
	void modifierCompte(Compte compte);
	
}
