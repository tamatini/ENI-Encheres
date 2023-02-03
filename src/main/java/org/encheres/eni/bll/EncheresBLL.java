package org.encheres.eni.bll;

import java.time.LocalDate;

import org.encheres.eni.BusinessException;
import org.encheres.eni.bo.Article;
import org.encheres.eni.dal.DAO;
import org.encheres.eni.dal.DAOFactory;

public class EncheresBLL {
	
	private DAO<Article> articleDAO;

	public EncheresBLL() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}
	
	// Ajouter un article
	public void creerArticle(String nomArticle, String description, LocalDate dateDebut, LocalDate dateFin, int prixInitial, int prixVente, int vendeurId, int categoryId ) throws BusinessException {
		BusinessException businessException = new BusinessException();
		
		this.validerDateEnchere(dateDebut, dateFin, businessException);
		this.validerPrix(prixInitial, businessException);
		this.validerDescription(description, businessException);
		
		if (!businessException.hasErreurs()) {
			Article article = new Article();
			this.articleDAO.insert(article);			
		} else {
			throw businessException;
		}
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
	
}
