package fr.proxibanque.proxibanquesi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

	/**
	 * numéro de carte bancaire FK dans la base Compte Courant
	 */
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "numero_carte", unique = true)
	private CarteBancaire carte;

	// *** CONSTRUCTEURS ***

	public CompteCourant() {
	}

	public CompteCourant(long numeroCompte, double solde, String dateOuverture) {
		this.numeroCompte = numeroCompte;
		this.solde = solde;
		this.dateOuverture = dateOuverture;
	}

	// *** GETTERS et SETTERS ***

	public CarteBancaire getCarte() {
		return carte;
	}

	public void setCarte(CarteBancaire carte) {
		this.carte = carte;
	}

}
