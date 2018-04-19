package fr.proxibanque.proxibanquesi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe abstraite décrivant un compte 
 * @author Sandrine Le Mentec et Anthony Le Cigne
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@XmlRootElement
public abstract class Compte {
	
	/**
	 * numero de compte client PK de la table
	 * (Id regénérée à la création du compte)
	 */
	@Id
	private long numeroCompte;
	/**
	 * solde du compte
	 */
	private double solde;
	/**
	 * date d'ouverture du compte
	 */
	private String dateOuverture;

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

	@Override
	public String toString() {
		return "Compte [numeroCompte=" + numeroCompte + ", solde=" + solde + ", dateOuverture=" + dateOuverture + "]";
	}

}
