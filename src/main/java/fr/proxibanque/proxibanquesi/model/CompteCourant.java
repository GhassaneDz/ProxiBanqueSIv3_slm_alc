package fr.proxibanque.proxibanquesi.model;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Cette classe décrit les caractéristiques d'un compte courant ProxiBanque.
 * Chaque client dispose d'un compte courant généré automatiquement à la
 * création client.
 * 
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */

@Entity
@XmlRootElement
public class CompteCourant extends Compte {

	// *** CONSTRUCTEURS ***
	
	public CompteCourant() {
	}

	public CompteCourant(long numeroCompte, double solde, String dateOuverture) {
		this.numeroCompte = numeroCompte;
		this.solde = solde;
		this.dateOuverture = dateOuverture;
	}

}
