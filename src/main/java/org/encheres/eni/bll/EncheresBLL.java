package org.encheres.eni.bll;

import java.time.LocalDate;

import org.encheres.eni.BusinessException;
import org.encheres.eni.bo.Article;
import org.encheres.eni.bo.Utilisateur;
import org.encheres.eni.dal.DAO;
import org.encheres.eni.dal.DAOFactory;

public class EncheresBLL {
	
	private DAO<Article> articleDAO;

	public EncheresBLL() {
		this.articleDAO = DAOFactory.getArticleDAO();
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
	public Article creerArticle(String nomArticle, String description, LocalDate dateDebut, LocalDate dateFin, int prixInitial, int prixVente, int vendeurId, int categoryId ) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Article article;
		
		this.validerDateEnchere(dateDebut, dateFin, businessException);
		this.validerPrix(prixInitial, businessException);
		this.validerDescription(description, businessException);
		
		if (!businessException.hasErreurs()) {
			article = new Article(nomArticle, description, dateDebut, dateDebut, prixInitial, prixVente, vendeurId, categoryId);
			this.articleDAO.insert(article);			
		} else {
			throw businessException;
		}
		
		return article;
	}
	
	private void validerDateEnchere(LocalDate dateDebut, LocalDate dateFin, BusinessException businessException) {
		if (dateFin.isBefore(dateDebut) || dateFin.isEqual(dateDebut)) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DATE_ERREUR);
		}		
	}
	
	private void validerPrix(int prix, BusinessException businessException) {
		if (prix <= 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_PRIX_ERREUR);
		}
	}
	
	private void validerDescription(String description, BusinessException businessException) {
		if (description.length() > 300) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DESCRIPTION_ERREUR);
		}
	}
	
	// Mettre à jour une vente
	
	// Supprimer/Suspendre vente (il ne doit y avoir d'enchère en cours sur cette vente)
	
	// Enchérir une vente
	
	/**
	 * Méthode pour afficher un article
	 * @param articleId
	 * @return article
	 */
	public Article afficherArticle(int articleId) throws BusinessException {
		BusinessException businessException = new BusinessException();
		if (articleId == 0) {
			businessException.ajouterErreur(CodesResultatBLL.SELECT_BY_ID_ARTICLE_NULL);
		}
		return articleDAO.selectById(articleId);
	}
}
