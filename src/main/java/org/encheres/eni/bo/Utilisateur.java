package org.encheres.eni.bo;

/*
 * TODO créer la bo de la classe utilisateur
 */
public class Utilisateur {

	private int utilisateurId;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private int credit;
	private boolean administrateur;
	
	/**
	 * Constructeur de la classe utilisateur sans aucun paramètre
	 */
	public Utilisateur() {
		super();
	}
	
	/**
	 * Constructeur de la classe utilisateur avec paramètre sans id
	 * @param pseudo le pseudo de l'utilisateur
	 * @param nom le nom de l'utilisateur
	 * @param prenom le prénom de l'utilisateur
	 * @param email l'adresse mail de l'utilisateur
	 * @param telephone le numéro de téléphone de l'utilisateur
	 * @param rue la rue de l'adresse
	 * @param codePostal le code postal de l'adresse
	 * @param ville la ville de l'adresse
	 * @param motDePasse le mot de passe de l'utilisateur
	 * @param credit la quantité de crédit de l'utilisateur, par défaut 0
	 * @param administrateur le rôle de l'utilisateur par défaut false
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, int credit, boolean administrateur) {
		super();
		this.setPseudo(pseudo);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setEmail(email);
		this.setTelephone(telephone);
		this.setRue(rue);
		this.setCodePostal(codePostal);
		this.setVille(ville);
		this.setMotDePasse(motDePasse);
		this.setCredit(credit);
		this.setAdministrateur(administrateur);
	}
	
	/**
	 * Constructeur de la classe utilisateur
	 * @param utilisateurId l'id de l'utilisateur
	 * @param pseudo le pseudo de l'utilisateur
	 * @param nom le nom de l'utilisateur
	 * @param prenom le prénom de l'utilisateur
	 * @param email l'adresse mail de l'utilisateur
	 * @param telephone le numéro de téléphone de l'utilisateur
	 * @param rue la rue de l'adresse
	 * @param codePostal le code postal de l'adresse
	 * @param ville la ville de l'adresse
	 * @param motDePasse le mot de passe de l'utilisateur
	 * @param credit la quantité de crédit de l'utilisateur, par défaut 0
	 * @param administrateur le rôle de l'utilisateur par défaut false
	 */
	public Utilisateur(int utilisateurId, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur) {
		
		this(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
		this.setUtilisateurId(utilisateurId);
	}

	/**
	 * Constructeur pour créer un nouvel utilisateur
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
	}
	
	/**
	 * @return l'id de l'utilisateur
	 */
	public int getUtilisateurId() {
		return utilisateurId;
	}
	


	/**
	 * @param utilisateurId l'id de l'utilisateur
	 */
	public void setUtilisateurId(int utilisateurId) {
		this.utilisateurId = utilisateurId;
	}
	
	/**
	 * @return le pseudo utilisé pour se connecter
	 */
	public String getPseudo() {
		return pseudo;
	}
	
	/**
	 * @param pseudo le pseudo de l'utilisateur
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	/**
	 * @return le nom de l'utilisateur
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * @param nom le nom de l'utilisateur
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * @return le prénom de l'utilisateur
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * @param prenom le prénom de l'utilisateur
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * @return l'email de l'utilisateur
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email l'email de l'utilisateur
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return le numéro de téléphone de l'utilisateur
	 */
	public String getTelephone() {
		return telephone;
	}
	
	/**
	 * @param telephone le numéro de téléphone de l'utilisateur
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/**
	 * @return la rue de l'adresse de l'utilisateur
	 */
	public String getRue() {
		return rue;
	}
	
	/**
	 * @param rue la rue de l'adresse de l'utilisateur
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}
	
	/**
	 * @return le code postal de l'adresse de l'utilisateur
	 */
	public String getCodePostal() {
		return codePostal;
	}
	
	/**
	 * @param codePostal le code postal de l'adresse de l'utilisateur
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	/**
	 * @return la ville de l'adresse de l'utilisateur
	 */
	public String getVille() {
		return ville;
	}
	
	/**
	 * @param ville la ville de l'adresse de l'utilisateur
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	/**
	 * @return le mot de passe de l'utilisateur
	 */
	public String getMotDePasse() {
		return motDePasse;
	}
	
	/**
	 * @param motDePasse le mot de passe de l'utilisateur
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	/**
	 * @return la quantité de crédit que possède l'utilisateur par défaut 0
	 */
	public int getCredit() {
		return credit;
	}
	
	
	/**
	 * @param credit la quantité de crédit que possède l'utilisateur par de
	 */
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	/**
	 * @return le rôle de l'utilisateur
	 */
	public boolean isAdministrateur() {
		return administrateur;
	}
	
	/**
	 * @param administrateur le rôle de l'utilisateur
	 */
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	@Override
	public String toString() {
		return "Utilisateur [utilisateurId=" + utilisateurId + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal="
				+ codePostal + ", ville=" + ville + ", motDePasse=" + motDePasse + ", credit=" + credit
				+ ", administrateur=" + administrateur + "]";
	}
	

	
}
