package org.encheres.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.encheres.eni.BusinessException;
import org.encheres.eni.bo.Categorie;
import org.encheres.eni.dal.CodesResultatDAL;
import org.encheres.eni.dal.DAO;

public class CategorieDAOJdbcImpl implements DAO<Categorie> {
	private String SELECT_ALL_CATEGORIES = "SELECT * FROM CATEGORIES";
	private static final String SELECT_BY_ID_CATEGORIE = "SELECT * FROM Categories WHERE categorieId = ?";
	
	@Override
	public List<Categorie> selectAll() throws BusinessException {
		List<Categorie> categories = new ArrayList<>();
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL_CATEGORIES);){
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Categorie categorie = new Categorie(rs.getInt("categorieId"), rs.getString("libelle"));
				categories.add(categorie);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_CATEGORIES_ECHEC);
			throw businessException;
		}
		return categories;
	}


	@Override
	public void insert(Categorie object) throws BusinessException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Categorie selectById(int Id) throws BusinessException {
		Categorie categorie = new Categorie();
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID_CATEGORIE);
			pstmt.setInt(1, Id);
			ResultSet rs = pstmt.executeQuery(); 
			if (rs.next()) {
				categorie.setCategorieId(rs.getInt("categorieId"));
				categorie.setLibelle(rs.getString("libelle"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_CATEGORIE_ECHEC);
			throw businessException;
		}
		return categorie;
	}


	@Override
	public void update(Categorie object) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
}
