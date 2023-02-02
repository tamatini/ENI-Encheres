package org.encheres.eni.dal;

import org.encheres.eni.bo.Article;
import org.encheres.eni.bo.Utilisateur;
import org.encheres.eni.dal.jdbc.ArticleDAOJdbcImpl;
import org.encheres.eni.dal.jdbc.UtilisateurDAOJdbcImpl;


public abstract class DAOFactory {
	
	private static DAO<Utilisateur> utilisateurDAO;
	private static DAO<Article> articleDAO;

	public static DAO<Utilisateur> getUtilisateurDAO() {
		if (utilisateurDAO == null) {
			utilisateurDAO = new UtilisateurDAOJdbcImpl();
		}
		return utilisateurDAO;
	}
	
	
	/**
	 * @return articleDAO
	 */
	public static DAO<Article> getArticleDAO() {
		if (articleDAO == null) {
			articleDAO = new ArticleDAOJdbcImpl();
		}
		return articleDAO;
	}
}
