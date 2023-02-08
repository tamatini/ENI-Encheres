package org.encheres.eni.dal;

import java.util.List;

import org.encheres.eni.BusinessException;
import org.encheres.eni.bo.Enchere;

public interface EnchereDAO extends DAO<Enchere> {

	public List<Enchere> selectAllByArticleId(int id) throws BusinessException;
}
