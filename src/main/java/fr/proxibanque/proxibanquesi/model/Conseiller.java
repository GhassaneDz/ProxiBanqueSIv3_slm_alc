package fr.proxibanque.proxibanquesi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Cette classe décrit les caractéristiques des conseillers ProxiBanque.
 * 
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */

@Entity
public class Conseiller {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idConseiller;
	private String prenom;
	private String nom;
	private String login;
	private String password;

	// TODO Liste de clients en @OneToMany - prochaine itération

	// *** CONSTRUCTEURS ***

	public Conseiller() {
	}

	public Conseiller(String prenom, String nom, String login, String password) {
		this.prenom = prenom;
		this.nom = nom;
		this.login = login;
		this.password = password;
	}

	// *** GETTERS et SETTERS ***

	public long getIdConseiller() {
		return idConseiller;
	}

	public void setIdConseiller(long idConseiller) {
		this.idConseiller = idConseiller;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// *** OTHER METHODS ***

	@Override
	public String toString() {
		return "Conseiller [prenom=" + prenom + ", nom=" + nom + ", login=" + login + ", password=" + password + "]";
	}

}
