package fr.proxibanque.proxibanquesi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * JAVA Bean représentant une carte banquaire
 * 
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */
@Entity
@XmlRootElement
public class CarteBancaire {

	/**
	 * numéro de la carte. sert de PK dans la table carte banquaire et de FK dans la
	 * base compte courant
	 */

	@Id
	private long numeroCarte;
	/**
	 * type de la carte (Visa ou Electron)
	 */
	private String type;

	/**
	 * Permet de typer une carte en VISA (données non sauvegarder en base)
	 */
	@Transient
	public static final String VISA = "visa";

	/**
	 * Permet de typer une carte en VISA (données non sauvegarder en base)
	 */
	@Transient
	public static final String ELECTRON = "electron";

	// *** CONSTRUCTEURS ***

	public CarteBancaire() {
	}

	public CarteBancaire(long numeroCarte, String type) {
		this.numeroCarte = numeroCarte;
		this.type = type;
	}

	// *** GETTERS et SETTERS ***

	public long getNumeroCarte() {
		return numeroCarte;
	}

	public void setNumeroCarte(long numeroCarte) {
		this.numeroCarte = numeroCarte;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
