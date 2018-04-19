package fr.proxibanque.proxibanquesi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Classe abstraite factorisant les caractéristiques des comptes bancaires
 * ProxiBanque.
 * 
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */

@Entity
// Une table par classe, n'incluant que l'état défini dans la classe.
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
public abstract class Compte {

	/**
	 * Numéro de compte constituant la PK de la table
	 */
	@Id
	protected long numeroCompte;

	/**
	 * Solde du compte
	 */
	protected double solde;
	/**
	 * Date d'ouverture du compte
	 */
	protected String dateOuverture;

	// *** GETTERS et SETTERS ***

	public long getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(long numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public String getDateOuverture() {
		return dateOuverture;
	}

	public void setDateOuverture(String dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	// *** AUTRES METHODES ***

	@Override
	public String toString() {
		return "Compte [numeroCompte=" + numeroCompte + ", solde=" + solde + ", dateOuverture=" + dateOuverture + "]";
	}

}
