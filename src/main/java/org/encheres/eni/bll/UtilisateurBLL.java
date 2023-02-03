package org.encheres.eni.bll;

import org.encheres.eni.BusinessException;
import org.encheres.eni.bo.Utilisateur;
import org.encheres.eni.dal.DAOFactory;
import org.encheres.eni.dal.UtilisateurDAO;

public class UtilisateurBLL {
		
	private UtilisateurDAO utilisateurDAO;
	
	/**
	 * Constructeur
	 * @param utilisateurDAO
	 */
	public UtilisateurBLL() {
		this.utilisateurDAO = (UtilisateurDAO) DAOFactory.getUtilisateurDAO();
	}

	// Créer un utilisateur
	/**
	 * Méthode qui vérifie les informations communiquées pour la création d'un compte utilisateur 
	 * @verif pseudo : non null, non vide, caractères alphanumériques, inf à 30 caractères et est unique
	 * @verif nom : non null, non vide, ne contient pas de métacaractères et inf à 30 caractères
	 * @verif prenom : non null, non vide, ne contient pas de métacaractères et inf à 30 caractères
	 * @verif email : non null, non vide, est unique et inf à 20 caractères
	 * @verif telephone : ne contient que des caractères numériques et inf à 15 caractères
	 * @verif rue : non null, non vide et inf à 30 caractères
	 * @verif codePostal : non null, non vide, ne contient que des caractères numériques et inf à 10 caractères
	 * @verif ville : non null, non vide et inf à 30 caractères
	 * @verif motDePasse : non null, non vide, entre 8 à 30 caractères dont 1 caractère spécial, 1 chiffre et une lettre majuscule
	 * @verif control_motDePasse : vérifie que les 2 mots de passes renseignées sont identiques
	 * @return un nouvel utilisateur
	 * @throws BusinessException
	 */
	//TODO envoyer mail de confirmation ?
	public Utilisateur creerUtilisateur(Utilisateur nouvelUtilisateur, String controle_motDePasse) throws BusinessException {
		BusinessException businessException = new BusinessException();
		
		if (nouvelUtilisateur.getPseudo() == null || nouvelUtilisateur.getPseudo().isBlank()) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_FIELD_NULL);
		} else if (!isAlphaNumeric(nouvelUtilisateur.getPseudo()) || nouvelUtilisateur.getPseudo().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_USER_PSEUDO_ERROR);
		}
		if (utilisateurDAO.SelectByPseudo(nouvelUtilisateur.getPseudo()).getPseudo() != null) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_USER_PSEUDO_OTHER);
		}
		if (nouvelUtilisateur.getNom() == null || nouvelUtilisateur.getNom().isBlank()) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_FIELD_NULL);
		}
		if (!isAlphabetic(nouvelUtilisateur.getNom()) || nouvelUtilisateur.getNom().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_USER_NAME_ERROR);
		}
		if (nouvelUtilisateur.getPrenom() == null || nouvelUtilisateur.getPrenom().isBlank()) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_FIELD_NULL);
		}
		if (!isAlphabetic(nouvelUtilisateur.getPrenom()) || nouvelUtilisateur.getPrenom().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_USER_FIRSTNAME_ERROR);
		}
		if (nouvelUtilisateur.getEmail() == null || nouvelUtilisateur.getEmail().isBlank()) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_FIELD_NULL);
		}
		if (!isEmailFormat(nouvelUtilisateur.getEmail()) || nouvelUtilisateur.getEmail().length() > 20) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_USER_EMAIL_ERROR);
		}
		// A modifier quand la méthode sera finalisée
		if (utilisateurDAO.selectByEmail(nouvelUtilisateur.getEmail()) != null) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_USER_EMAIL_OTHER);
		}
		if (nouvelUtilisateur.getTelephone() != null && !nouvelUtilisateur.getTelephone().isBlank()) {
			if (!isPhoneFormat(nouvelUtilisateur.getTelephone()) || nouvelUtilisateur.getTelephone().length() > 15) {
				businessException.ajouterErreur(CodesResultatBLL.INSERT_USER_PHONE_ERROR);
			}
		}
		if (nouvelUtilisateur.getRue() == null || nouvelUtilisateur.getRue().isBlank()) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_FIELD_NULL);
		}
		if (nouvelUtilisateur.getRue().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_USER_STREET_ERROR);
		}
		if (nouvelUtilisateur.getCodePostal() == null || nouvelUtilisateur.getCodePostal().isBlank()) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_FIELD_NULL);
		}
		if (!isNumeric(nouvelUtilisateur.getCodePostal()) || nouvelUtilisateur.getCodePostal().length() > 10) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_USER_ZIPCODE_ERROR);
		}
		if (nouvelUtilisateur.getVille() == null || nouvelUtilisateur.getVille().isBlank()) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_FIELD_NULL);
		}
		if (nouvelUtilisateur.getVille().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_USER_CITY_ERROR);
		}
		if (nouvelUtilisateur.getMotDePasse() == null || nouvelUtilisateur.getMotDePasse().isBlank()) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_FIELD_NULL);
		}
		if (!isValidPassword(nouvelUtilisateur.getMotDePasse()) || nouvelUtilisateur.getMotDePasse().length() < 8 || nouvelUtilisateur.getMotDePasse().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_USER_PASSWORD_ERROR);
		}
		if (!nouvelUtilisateur.getMotDePasse().equals(controle_motDePasse)) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_USER_PASSWORD_OTHER);
		}
		
		if(businessException.hasErreurs())
		{
			throw businessException;
		} else {
			this.utilisateurDAO.insert(nouvelUtilisateur);
		}
		return nouvelUtilisateur;
	}
	
	/**
	 * Méthode regex pour validation de la condition alphanumérique
	 * @param s : chaîne de caractères
	 * @return boolean
	 */
	public boolean isAlphaNumeric(String s) {
		return s != null && s.matches("^[a-zA-Z0-9]*$");
	}
	
	/**
	 * Méthode regex pour validation de la condition alphabétique
	 * @param s : chaîne de caractères
	 * @return boolean
	 */
	public boolean isAlphabetic(String s) {
		// TODO gérer les accents
		return s != null && s.matches("^[a-zA-Z]*$");
	}
	
	/**
	 * Méthode regex pour validation de la condition numérique
	 * @param s : chaîne de caractères
	 * @return boolean
	 */
	public boolean isNumeric(String s) {
		return s != null && s.matches("^[0-9]*$");
	}
	
	/**
	 * Méthode regex pour validation du format téléphonique et accepte les formats :
	 * 0123456789 /
	 * 01 23 45 67 89 /
	 * 01.23.45.67.89 /
	 * 01-23-45-67-89 /
	 * +33123456789
	 * @param s : chaîne de caractères
	 * @return boolean
	 */
	public boolean isPhoneFormat(String s) {
		return s != null && s.matches("^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$");
	}
	
	/**
	 * Méthode regex pour validation du format de l'e-mail
	 * @param s : chaîne de caractères
	 * @return boolean
	 */
	public boolean isEmailFormat(String s) {
		return s != null && s.matches("^[A-Za-z0-9+_.-]+@(.+)$");
	}
	
	/**
	 * Méthode regex pour validation du mot de passe
	 * @param s : chaîne de caractères
	 * @return boolean
	 */
	public boolean isValidPassword(String s) {
		// ^                 # start-of-string
		// (?=.*[0-9])       # a digit must occur at least once
		// (?=.*[a-z])       # a lower case letter must occur at least once
		// (?=.*[A-Z])       # an upper case letter must occur at least once
		// (?=.*[@#$%^&+=])  # a special character must occur at least once
		// (?=\S+$)          # no whitespace allowed in the entire string
		// $                 # end-of-string
		return s != null && s.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[@*#$%^&+=])(?=.*[A-Z]).*$");
	}
	
	// Mettre à jour un utilisateur
	
	// Supprimer un utilisateur
	
	// Voir un utilisateur
	public Utilisateur afficherProfil (int utilisateurId) {
		return utilisateurDAO.selectById(utilisateurId);
	}

	public Utilisateur seConnecter(String pseudo) throws BusinessException {
		if (pseudo.contains("@")) {
			return this.utilisateurDAO.selectByEmail(pseudo);
		} else {
			return this.utilisateurDAO.SelectByPseudo(pseudo);			
		}
	}
}
