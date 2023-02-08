package org.encheres.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.encheres.eni.BusinessException;
import org.encheres.eni.bo.Enchere;
import org.encheres.eni.dal.CodesResultatDAL;
import org.encheres.eni.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	private static final String SQL_SELECT_ALL_BY_IDARTICLE = "SELECT * FROM Encheres WHERE articleId=?";
	
	public List<Enchere> selectAllByArticleId(int id) throws BusinessException {
		List<Enchere> encheres = new ArrayList<>();
		UtilisateurDAOJdbcImpl acheteur = new UtilisateurDAOJdbcImpl();
		ArticleDAOJdbcImpl article = new ArticleDAOJdbcImpl();
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_ALL_BY_IDARTICLE);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			// Traitement du résultat (TODO vendeurId correspond à l'acheteur)
			while (rs.next()) {
				encheres.add(new Enchere(acheteur.selectById(rs.getInt("vendeurId")), article.selectById(id), rs.getDate("dateEnchere").toLocalDate(), rs.getInt("montantEnchere")));
			}
		} catch(SQLException e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ENCHERE_ECHEC);
			throw businessException;
		}
		return encheres;
	}

	@Override
	public void insert(Enchere object) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enchere selectById(int Id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Enchere object) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Enchere> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
