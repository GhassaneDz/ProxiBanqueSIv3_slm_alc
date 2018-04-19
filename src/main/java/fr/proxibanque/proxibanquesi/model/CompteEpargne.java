package fr.proxibanque.proxibanquesi.model;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objet compte épargne
 * 
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */
@Entity
@XmlRootElement
public class CompteEpargne extends Compte {

	/**
	 * taux de rémunération (négociable de client à client)
	 */
	private double tauxremun;

	public CompteEpargne() {

	}

	public CompteEpargne(long numeroCompte, double solde, String dateOuverture, double tauxremun) {
		this.numeroCompte = numeroCompte;
		this.solde = solde;
		this.dateOuverture = dateOuverture;
		this.tauxremun = tauxremun;
	}

	public double getTauxremun() {
		return tauxremun;
	}

	public void setTauxremun(double tauxremun) {
		this.tauxremun = tauxremun;
	}

}
