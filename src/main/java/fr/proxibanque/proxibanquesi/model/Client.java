package fr.proxibanque.proxibanquesi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Cette classe décrit les caractéristiques d'un client ProxiBanque.
 * 
 * @author Sandrine Le Mentec, Anthony Le Cigne
 *
 */

@Entity
@XmlRootElement
// Permet une correspondance entre le nom des variables d'instance et la
// configuration de JAXB.
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "idClient", "nom", "prenom", "adresse", "codePostal", "ville", "telephone", "compteCourant",
		"compteEpargne" })
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idClient;
	private String nom;
	private String prenom;
	private String adresse;
	private String codePostal;
	private String ville;
	private String telephone;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "compte_courant_id", unique = true)
	private CompteCourant compteCourant;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JoinColumn(name = "compte_epargne_id", unique = true)
	private CompteEpargne compteEpargne;

	// *** CONSTRUCTEURS ***

	public Client() {
	}

	public Client(String nom, String prenom, String adresse, String codePostal, String ville, String telephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.telephone = telephone;
	}

	// *** GETTERS et SETTERS ***

	public long getIdClient() {
		return idClient;
	}

	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public CompteCourant getCompteCourant() {
		return compteCourant;
	}

	public void setCompteCourant(CompteCourant compteCourant) {
		this.compteCourant = compteCourant;
	}

	public CompteEpargne getCompteEpargne() {
		return compteEpargne;
	}

	public void setCompteEpargne(CompteEpargne compteEpargne) {
		this.compteEpargne = compteEpargne;
	}

	// *** AUTRES METHODES ***

	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse
				+ ", codePostal=" + codePostal + ", ville=" + ville + ", telephone=" + telephone + "]";
	}

}
