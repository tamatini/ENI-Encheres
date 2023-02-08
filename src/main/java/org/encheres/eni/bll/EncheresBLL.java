package org.encheres.eni.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.encheres.eni.BusinessException;
import org.encheres.eni.bo.Article;
import org.encheres.eni.bo.Enchere;
import org.encheres.eni.bo.Retrait;
import org.encheres.eni.bo.Utilisateur;
import org.encheres.eni.dal.DAO;
import org.encheres.eni.dal.DAOFactory;
import org.encheres.eni.dal.EnchereDAO;
import org.encheres.eni.dal.UtilisateurDAO;

public class EncheresBLL {
	
	private DAO<Article> articleDAO;
	private DAO<Retrait> retraitDAO;
	private EnchereDAO enchereDAO;
	private UtilisateurDAO utilisateurDAO;

	public EncheresBLL() {
		this.articleDAO = DAOFactory.getArticleDAO();
		this.retraitDAO = DAOFactory.getRetraitDAO();
		this.enchereDAO = (EnchereDAO) DAOFactory.getEnchereDAO();
		this.utilisateurDAO = (UtilisateurDAO) DAOFactory.getUtilisateurDAO();
	}
	
	/**
	 * Ajoute un nouvel article associé à un utilisateur et une catégorie
	 * @param nomArticle le nom de la vente
	 * @param description la description
	 * @param dateDebut la date de début
	 * @param dateFin la date de fin
	 * @param prixInitial le prix initial
	 * @param prixVente le prix de vente
	 * @param vendeurId l'id du vendeur
	 * @param categoryId l'id de la catégorie
	 * @return 
	 * @throws BusinessException
	 */
	public Article creerArticle(String nomArticle, String description, LocalDate dateDebut, LocalDate dateFin, String prixInitial, 
			int vendeurId, String categoryId, String rue, String ville, String codePostal, String imageURL ) throws BusinessException {
		BusinessException businessException = new BusinessException();
		LocalDate dateDebutEnchere = null, dateFinEnchere = null;
		int vendeur, categorie, prix;
		Article article;
		Retrait retrait;
		
		dateDebutEnchere = this.dateDebutNotNull(dateDebut, businessException);
		dateFinEnchere = this.dateFinNotNull(dateFin, businessException);
		vendeur = this.vendeurId(vendeurId, businessException);
		categorie = this.categorieId(categoryId, businessException);
		prix = this.prix(prixInitial, businessException);

		this.validerNomArticle(nomArticle, businessException);
		this.validerDescription(description, businessException);
		this.validerDateEnchere(dateDebutEnchere, dateFinEnchere, businessException);
		this.validerPrix(prix, businessException);
		this.validerRue(rue, businessException);
		this.validerVille(ville, businessException);
		this.validerCodePostal(codePostal, businessException);
		
		if (!businessException.hasErreurs()) {
			article = new Article(nomArticle, description, dateDebut, dateFin, prix, prix, vendeur, categorie, imageURL);
			this.articleDAO.insert(article);
			retrait = new Retrait(rue, ville, codePostal, article.getArticleId());
			this.retraitDAO.insert(retrait);
			
		} else {
			throw businessException;
		}
		return article;
	}
	
	// Mettre à jour une vente
	
	// Supprimer/Suspendre vente (il ne doit y avoir d'enchère en cours sur cette vente)
	
	// Enchérir une vente
	
	/**
	 * Vérifie si le prix saisie n'est pas nul
	 * @param prixInitial le prix 
	 * @param businessException
	 * @return
	 */
	private int prix(String prixInitial, BusinessException businessException) {
		int prix = 0;
		try {
			if (prixInitial != null) {
				prix = Integer.parseInt(prixInitial);
			}
		} catch (NumberFormatException e) {
			businessException.ajouterErreur(CodesResultatBLL.PRIX_INITIAL_OBLIGATOIRE);
		}
		return prix;
	}
	
	/**
	 * Vérifie si la date saisie n'est pas nulle
	 * @param dateDebut la date de début 
	 * @param businessException
	 * @return la date de début
	 */
	private LocalDate dateDebutNotNull(LocalDate dateDebut, BusinessException businessException) {
		LocalDate date = null;
		try {
			if (dateDebut != null) {
				date = dateDebut;
			}
		} catch (NumberFormatException e) {
			businessException.ajouterErreur(CodesResultatBLL.DATE_DEBUT_ENCHERE_OBLIGATOIRE);
		}
		return date;
	}
	
	/**
	 * Vérifie si la date saisie n'est pas nulle
	 * @param dateFin la date de fin
	 * @param businessException
	 * @return la date de fin
	 */
	private LocalDate dateFinNotNull(LocalDate dateFin, BusinessException businessException) {
		LocalDate date = null;
		try {
			if (dateFin != null) {
				date = dateFin;
			}
		} catch (NumberFormatException e) {
			businessException.ajouterErreur(CodesResultatBLL.DATE_FIN_ENCHERE_OBLIGATOIRE);
		}
		return date;
	}
	
	/**
	 * @param categorieId
	 * @param businessException
	 * @return
	 */
	private int categorieId(String categorieId, BusinessException businessException) {
		int id = 0;
		try {
			if (categorieId != null) {
				id = Integer.parseInt(categorieId);
			}
		} catch (NumberFormatException e) {
			businessException.ajouterErreur(CodesResultatBLL.CATEGORIE_ARTICLE_OBLIGATOIRE);
		}
		return id;
	}
	
	/**
	 * @param vendeurId
	 * @param businessException
	 * @return
	 */
	private int vendeurId(Integer vendeurId, BusinessException businessException) {
		int id = 0;
		try {
			if (vendeurId != null) {
				id = vendeurId;
			}
		} catch (NumberFormatException e) {
			businessException.ajouterErreur(CodesResultatBLL.VENDEUR_CONNEXION_OBLIGATOIRE);
		}
		return id;
	}
	
	/**
	 * Vérifie que la date de fin ne soit pas antérieur ou égale à la date de début
	 * @param dateDebut la date de début
	 * @param dateFin la date de fin
	 * @param businessException
	 */
	private void validerDateEnchere(LocalDate dateDebut, LocalDate dateFin, BusinessException businessException) {
		if (dateFin.isBefore(dateDebut) || dateFin.isEqual(dateDebut)) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DATE_ERREUR);
		}		
	}
	
	/**
	 * Vérifie que le prix de départ ne soit pas inférieur ou égale à 0
	 * @param prix le prix
	 * @param businessException
	 */
	private void validerPrix(int prix, BusinessException businessException) {
		if (prix <= 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_PRIX_ERREUR);
		}
	}
	
	/**
	 * Vérifie que la description ne dépasse pas 300 caractère
	 * @param description
	 * @param businessException
	 */
	private void validerDescription(String description, BusinessException businessException) {
		if (description == null || description.trim().equals("")) {
			businessException.ajouterErreur(CodesResultatBLL.DESCRIPTION_ARTICLE_OBLIGATOIRE);
		}
		
		if (description.length() > 300) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DESCRIPTION_ERREUR);
		}
	}
	
	/**
	 * Vérifie la rue
	 * @param rue le nom de la rue
	 * @param businessException
	 */
	private void validerRue(String rue, BusinessException businessException) {
		if (rue == null || rue.equals("")) {
			businessException.ajouterErreur(CodesResultatBLL.RUE_ARTICLE_OBLIGATOIRE);
		}
		
		if (rue.length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.RUE_ARTICLE_LONGUEUR);
		}
	}
	
	/**
	 * Vérifie la ville
	 * @param ville le nom de la ville
	 * @param businessException
	 */
	private void validerVille(String ville, BusinessException businessException) {
		if (ville == null || ville.trim().equals("")) {
			businessException.ajouterErreur(CodesResultatBLL.VILLE_ARTICLE_OBLIGATOIRE);
		}
		
		if (ville.length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.VILLE_ARTICLE_LONGUEUR);
		}
	}
	
	/**
	 * Vérifie le code postal
	 * @param codePostal le code postal
	 * @param businessException
	 */
	private void validerCodePostal(String codePostal, BusinessException businessException) {
		if (codePostal == null || codePostal.trim().equals("")) {
			businessException.ajouterErreur(CodesResultatBLL.CODE_POSTAL_OBLIGATOIRE);
		}
		
		if (codePostal.length() > 6) {
			businessException.ajouterErreur(CodesResultatBLL.CODE_POSTAL_LONGUEUR);
		}
	}
	
	
	/**
	 * Vérifie le nom de l'article
	 * @param nomArticle
	 * @param businessException
	 */
	private void validerNomArticle(String nomArticle, BusinessException businessException) {
		if (nomArticle == null || nomArticle.trim().equals("")) {
			businessException.ajouterErreur(CodesResultatBLL.NOM_ARTICLE_OBLIGATOIRE);
		}
	}
	
	/**
	 * Méthode pour afficher un article
	 * @param articleId
	 * @return article
	 */
	public Article afficherArticle(int articleId) throws BusinessException {
		BusinessException businessException = new BusinessException();
		if (articleDAO.selectById(articleId) == null) {
			businessException.ajouterErreur(CodesResultatBLL.SELECT_BY_ID_ARTICLE_NULL);
			throw businessException;
		}
		return articleDAO.selectById(articleId);
	}
	
	/**
	 * Méthode pour afficher la meilleure enchère sur un article
	 * @param articleId
	 * @return si une enchère existe sur l'article : la meilleur enchère
	 * @return si l'article n'a pas été enchéri : l'enchère avec l'article, une date nulle, un montant égal au prix initial et un acheteur nul
	 */
	public Enchere afficherMeilleureEnchere(int articleId) throws BusinessException {
		List<Enchere> ListeEncheres = new ArrayList<>();
		Enchere meilleureEnchere = new Enchere();
		
		// Récupération de l'article
		Article article = this.articleDAO.selectById(articleId);
		meilleureEnchere.setArticle(article);
		
		ListeEncheres = this.enchereDAO.selectAllByArticleId(articleId);
		
		// Insertion du prix initial de l'article s'il n'y a pas eu des enchères
		if (ListeEncheres.size() == 0) {
			meilleureEnchere.setMontant_enchere(article.getPrixInitial());
			
		// Sinon on sélectionne la meilleur enchère de la liste
		} else {
			int meilleureOffre = 0;
			for (Enchere current : ListeEncheres) {
				if (current.getMontant_enchere() > meilleureOffre) {
					meilleureEnchere.setAcheteur(current.getAcheteur());
					meilleureEnchere.setDateEnchere(current.getDateEnchere());
					meilleureEnchere.setMontant_enchere(current.getMontant_enchere());
					meilleureOffre = current.getMontant_enchere();
				}
			}
		}
		return meilleureEnchere;
	}
	
	public Retrait afficherRetrait(int articleId) throws BusinessException {
		Retrait retrait = new Retrait();
		retrait = this.retraitDAO.selectById(articleId);
		return retrait;
	}

	public void creerEnchere(int idArticle, Utilisateur user, int nouvelleOffre) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Enchere nouvelleEnchere = new Enchere();
		Enchere ancienneEnchere = new Enchere();
		Utilisateur ancienEncherisseur = new Utilisateur();
		ancienneEnchere = afficherMeilleureEnchere(idArticle);
		
		if (nouvelleOffre <= ancienneEnchere.getMontant_enchere()) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_BID_AMOUNT_MINI_ERROR);
		}
		if (nouvelleOffre > user.getCredit()) {
			businessException.ajouterErreur(CodesResultatBLL.INSERT_USER_NOT_ENOUGH_CREDITS);
		}
		
		if(businessException.hasErreurs()) {
			throw businessException;
		} else {
			// Insertion de la nouvelle enchère
			nouvelleEnchere.setArticle(ancienneEnchere.getArticle());
			nouvelleEnchere.setMontant_enchere(nouvelleOffre);
			nouvelleEnchere.setDateEnchere(LocalDate.now());
			nouvelleEnchere.setAcheteur(user);
			this.enchereDAO.insert(nouvelleEnchere);
			
			// Ancienne mise retournée au précédent enchérisseur
			if (ancienneEnchere.getAcheteur() != null) {
				ancienEncherisseur = ancienneEnchere.getAcheteur();
				ancienEncherisseur.setCredit(ancienEncherisseur.getCredit() + ancienneEnchere.getMontant_enchere());
				this.utilisateurDAO.updateCredit(ancienEncherisseur.getUtilisateurId(), ancienEncherisseur.getCredit());
			}
			
			// Nouvelle mise déduite du nouvel enchérisseur
			user.setCredit(user.getCredit() - nouvelleOffre);
			this.utilisateurDAO.updateCredit(user.getUtilisateurId(), user.getCredit());
		}
	}
}
