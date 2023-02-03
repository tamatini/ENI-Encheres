package org.encheres.eni.bll;

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
	public void creerArticle(Article article) throws BusinessException {
		this.articleDAO.insert(article);
	}
	
	
	// Mettre à jour une vente
	
	// Supprimer/Suspendre vente (il ne doit y avoir d'enchère en cours sur cette vente)
	
	// Enchérir une vente
	
}
