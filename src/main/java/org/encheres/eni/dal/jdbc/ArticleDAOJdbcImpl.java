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

// TODO modifier les méthodes une fois que le champ contenant l'URL de l'image sera créé dans la BDD

public class ArticleDAOJdbcImpl implements DAO<Article>{
	private String INSERT_ARTICLE = "INSERT INTO Articles VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_BY_ID_ARTICLE = "SELECT * FROM Articles WHERE articleId = ?";
	
	
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
			ps.setString(9, article.getImageURL());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				article.setArticleId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_ECHEC);
			throw businessException;
		}
	}

	@Override
	public Article selectById(int Id) throws BusinessException {
		Article article = new Article();
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID_ARTICLE);
			pstmt.setInt(1, Id);
			ResultSet rs = pstmt.executeQuery(); 
			if (rs.next()) {
				article.setArticleId(rs.getInt("articleId"));
				article.setNomArticle(rs.getString("nomArticle"));
				article.setDescription(rs.getString("description"));
				article.setDateDebutEncheres(rs.getDate("dateDebutEncheres").toLocalDate());
				article.setDateFinEncheres(rs.getDate("dateFinEncheres").toLocalDate());
				article.setPrixInitial(rs.getInt("prixInitial"));
				article.setPrixVente(rs.getInt("prixVente"));
				article.setVendeurId(rs.getInt("vendeurId"));
				article.setCategoryId(rs.getInt("categorieId"));
				article.setImageURL(rs.getString("imageURL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ARTICLE_ECHEC);
			throw businessException;
		}
		return article;
	}

	@Override
	public void update(Article object) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Article> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
