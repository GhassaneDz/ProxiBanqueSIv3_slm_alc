package fr.proxibanque.proxibanquesi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * JAVA Bean représentant une carte banquaire
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */
@Entity
@XmlRootElement
public class CarteBancaire {
	@Id
	private long numeroCarte;
	private String type;
	@Transient
	public static final String VISA="visa";
	@Transient
	public static final String ELECTRON = "electron";
	
	public CarteBancaire() {
		
	};
	
	public CarteBancaire(long numeroCarte, String type) {
		super();
		this.numeroCarte = numeroCarte;
		this.type = type;
	}
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
