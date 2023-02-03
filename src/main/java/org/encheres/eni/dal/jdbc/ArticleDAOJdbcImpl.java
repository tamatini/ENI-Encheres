package org.encheres.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.encheres.eni.BusinessException;
import org.encheres.eni.bo.Article;
import org.encheres.eni.dal.CodesResultatDAL;
import org.encheres.eni.dal.DAO;

public class ArticleDAOJdbcImpl implements DAO<Article>{
	private String INSERT_ARTICLE = "INSERT INTO Articles VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	
	
	@Override
	public void insert(Article article) throws BusinessException {
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_ARTICLE, Statement.RETURN_GENERATED_KEYS) ) {
			ps.setString(1, article.getNomArticle());
			ps.setString(2, article.getDescription());
			ps.setObject(3, article.getDateDebutEncheres());
			ps.setObject(4, article.getDateFinEncheres());
			ps.setInt(5, article.getPrixInitial());
			ps.setInt(6,  article.getPrixVente());
			ps.setInt(7,  article.getVendeurId());
			ps.setInt(8,  article.getCategoryId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				article.setArticleId(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_ECHEC);
			throw businessException;
		}
	}

	@Override
	public Article selectById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Article object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Article> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
