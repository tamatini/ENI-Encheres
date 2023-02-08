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
	private static final String SQL_INSERT = "INSERT INTO Encheres (vendeurId, articleId, dateEnchere, montantEnchere) VALUES (?,?,?,?)";
	
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
	public void insert(Enchere enchere) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
//			// auto commit suspendu le temps d'effectuer toutes les méthodes
//			cnx.setAutoCommit(false);

			PreparedStatement pstmt = cnx.prepareStatement(SQL_INSERT);
			pstmt.setInt(1, enchere.getAcheteur().getUtilisateurId());
			pstmt.setInt(2, enchere.getArticle().getArticleId());
			pstmt.setObject(3, enchere.getDateEnchere());
			pstmt.setInt(4, enchere.getMontant_enchere());
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
//			cnx.rollback();
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_BID_ECHEC);
			throw businessException;
		}
	}

	@Override
	public Enchere selectById(int Id) throws BusinessException {
		// no action
		return null;
	}

	@Override
	public void update(Enchere object) throws BusinessException {
		// no action
	}

	@Override
	public void delete(int id) throws BusinessException {
		// no action
	}

	@Override
	public List<Enchere> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
