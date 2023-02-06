package org.encheres.eni.bll;

import java.util.List;

import org.encheres.eni.BusinessException;
import org.encheres.eni.bo.Article;
import org.encheres.eni.bo.Categorie;
import org.encheres.eni.dal.DAO;
import org.encheres.eni.dal.DAOFactory;

public class CategorieBLL {
	private DAO<Categorie> categorieDAO;
	
	
	/**
	 * Constructeur de CategorieBLL
	 */
	public CategorieBLL() {
		this.categorieDAO = DAOFactory.getCategorieDAO();
	}
	
	public List<Categorie> listeCategorie() throws BusinessException {
		return this.categorieDAO.selectAll();
	}
	
	public Categorie afficherCategorie(int categorieId) throws BusinessException {
		return categorieDAO.selectById(categorieId);
	}
	
}
