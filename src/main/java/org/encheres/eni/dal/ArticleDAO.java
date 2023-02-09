package org.encheres.eni.dal;

import java.util.List;

import org.encheres.eni.BusinessException;
import org.encheres.eni.bo.Article;

public interface ArticleDAO extends DAO<Article> {
	public List<Article> selectAllByName(String rechercher, int categorieId) throws BusinessException;
}
