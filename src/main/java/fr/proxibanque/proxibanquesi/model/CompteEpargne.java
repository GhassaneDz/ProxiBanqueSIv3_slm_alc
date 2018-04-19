package fr.proxibanque.proxibanquesi.model;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Cette classe décrit les caractéristiques d'un compte épargne ProxiBanque.
 * Chaque client peut disposer d'un compte épargne après ajout explicite par un
 * conseiller. Aucune carte bancaire n'est associée à un compte épargne.
 * 
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */

@Entity
@XmlRootElement
public class CompteEpargne extends Compte {

	/**
	 * Taux de rémunération (négociable de client à client)
	 */
	private double tauxRemun;

	public CompteEpargne() {

	}

	public CompteEpargne(long numeroCompte, double solde, String dateOuverture, double tauxremun) {
		this.numeroCompte = numeroCompte;
		this.solde = solde;
		this.dateOuverture = dateOuverture;
		this.tauxRemun = tauxremun;
	}

	public double getTauxremun() {
		return tauxRemun;
	}

	public void setTauxremun(double tauxremun) {
		this.tauxRemun = tauxremun;
	}

}
