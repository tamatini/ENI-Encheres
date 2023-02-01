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
	public Utilisateur creerUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse) throws BusinessException {
		BusinessException exception = new BusinessException();
		
		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone,
				rue, codePostal, ville, motDePasse);
		
//		this.validerNom(utilisateur,exception);
//
//		if(!exception.hasErreurs())
//		{
//			this.daoUtilisateur.insert(utilisateur);
//		}
		
		if(exception.hasErreurs())
		{
			throw exception;
		}
		return utilisateur;
	}
	
	// Mettre à jour un utilisateur
	
	// Supprimer un utilisateur
	
	// Voir un utilisateur
	public boolean seConnecter(String pseudo, String motDePasse) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Utilisateur utilisateur = utilisateurDAO.SelectByPseudo(pseudo);
		String utilisateurPseudo = utilisateur.getPseudo();
		String utilisateurMotDePasse = utilisateur.getMotDePasse();
		boolean response = false;
		
		if (pseudo.isEmpty() || motDePasse.isEmpty())  {
			System.out.println("identifiant ou mot de passe vide");
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_ERREUR);
		};
		
		
		if (utilisateurPseudo == null) {
			System.out.println("cet utilisateur n'existe pas");
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_ERREUR);
		}
		
		if (utilisateurMotDePasse == null) {
			System.out.println("cet utilisateur n'exite pas");
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_ERREUR);
		}
		
		if (utilisateurMotDePasse != null && !utilisateurMotDePasse.equals(motDePasse)) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_ERREUR);
		}
		
		if (!businessException.hasErreurs() 
				&& utilisateur.getPseudo().equals(pseudo) 
				&& utilisateur.getMotDePasse().equals(motDePasse)) {
			response = true;
		}
		
		return response;
	}

}
