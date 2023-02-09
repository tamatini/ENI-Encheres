package org.encheres.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.encheres.eni.BusinessException;
import org.encheres.eni.bo.Retrait;
import org.encheres.eni.dal.CodesResultatDAL;
import org.encheres.eni.dal.DAO;

public class RetraitDAOJdbcImpl implements DAO<Retrait> {
	private String SELECT_BY_ID = "SELECT * FROM RETRAITS WHERE articleId = ?";
	private String INSERT_RETRAIT = "INSERT INTO RETRAITS VALUES (?, ?, ?, ?)";
	
	
	public Retrait selectById(int articleId) throws BusinessException {
		Retrait retrait = new Retrait();
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID);) {
			ps.setInt(1,  articleId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				retrait.setrue(rs.getString("rue"));
				retrait.setville(rs.getString("ville"));
				retrait.setcodePostal(rs.getString("codePostal"));
				retrait.setArticleId(rs.getInt("articleId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_RETRAIT_ECHEC);
			throw businessException;
		}
		return retrait;
	}


	@Override
	public void insert(Retrait retrait) throws BusinessException {
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_RETRAIT);) {
			ps.setInt(1, retrait.getArticleId());
			ps.setString(2, retrait.getrue());
			ps.setString(3, retrait.getville());
			ps.setString(4, retrait.getcodePostal());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_ECHEC);
			throw businessException;
		}
	}


	@Override
	public void update(Retrait object) throws BusinessException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(int id) throws BusinessException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Retrait> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
