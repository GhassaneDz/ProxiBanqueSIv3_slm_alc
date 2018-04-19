package fr.proxibanque.proxibanquesi.model;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class CompteCourant extends Compte {

	public CompteCourant() {
	}
	
	public CompteCourant(long numeroCompte, double solde, String dateOuverture) {
		this.numeroCompte = numeroCompte;
		this.solde = solde;
		this.dateOuverture = dateOuverture;
	}
		
}
