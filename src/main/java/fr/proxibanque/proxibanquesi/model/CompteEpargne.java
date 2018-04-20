package fr.proxibanque.proxibanquesi.model;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Cette classe d�crit les caract�ristiques d'un compte �pargne ProxiBanque.
 * Chaque client peut disposer d'un compte �pargne apr�s ajout explicite par un
 * conseiller. Aucune carte bancaire n'est associ�e � un compte �pargne.
 * 
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */

@Entity
@XmlRootElement
public class CompteEpargne extends Compte {

	/**
	 * Taux de r�mun�ration (n�gociable de client � client)
	 */
	private double tauxRemun;
	
	//constructer

	// *** CONSTRUCTEURS *** 
	
	public CompteEpargne() {
	}

	public CompteEpargne(long numeroCompte, double solde, String dateOuverture, double tauxRemun) {
		this.numeroCompte = numeroCompte;
		this.solde = solde;
		this.dateOuverture = dateOuverture;
		this.tauxRemun = tauxRemun;
	}
	
	// *** GETTERS et SETTERS ***

	//getter et setter
	public double getTauxRemun() {
		return tauxRemun;
	}

	public void setTauxRemun(double tauxremun) {
		this.tauxRemun = tauxremun;
	}

}
