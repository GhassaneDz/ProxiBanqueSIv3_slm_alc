package fr.proxibanque.proxibanquesi.model;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objet compte �pargne
 * 
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */
@Entity
@XmlRootElement
public class CompteEpargne extends Compte {

	/**
	 * taux de r�mun�ration (n�gociable de client � client)
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
