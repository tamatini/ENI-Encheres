package org.encheres.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.encheres.eni.BusinessException;
import org.encheres.eni.bo.Utilisateur;
import org.encheres.eni.dal.CodesResultatDAL;
import org.encheres.eni.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	private String SELECT_BY_PSEUDO = "SELECT * FROM Utilisateurs WHERE pseudo=?";
	private String SELECT_BY_EMAIL = "SELECT * FROM Utilisateurs WHERE email=?";
	private static final String SQL_UPDATE_CREDIT = "UPDATE Utilisateurs SET credit = ? WHERE utilisateurId = ?";
	private static final String SQL_INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, "
			+ "rue, codePostal, ville, motDePasse, credit, administrateur) "
			+ "values (?,?,?,?,?,?,?,?,?,0,0)";
	
	/**
	 * La méthode retourne le pseudo et le mot de passe de l'utilisateur
	 * @param pseudo le pseudo de l'utilisateur
	 * @return l'utilisateur existant
	 * @throws BusinessException 
	 */
	@Override
	public Utilisateur SelectByPseudo(String pseudo) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BY_PSEUDO);) {
			ps.setString(1, pseudo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				utilisateur.setUtilisateurId(rs.getInt("utilisateurId"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setMotDePasse(rs.getString("motDePasse"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("codePostal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
				utilisateur.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_USER_ECHEC);
			throw businessException;
		}
		return utilisateur;
	}
	
	@Override
	public void insert(Utilisateur utilisateur) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom().toUpperCase());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				utilisateur.setUtilisateurId(rs.getInt(1));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_USER_ECHEC);
			throw businessException;
		}
	}
	
	@Override
	public Utilisateur selectById(int Id) {
		Utilisateur pseudo = new Utilisateur();
		
		try(Connection cnx = ConnectionProvider.getConnection()){
				PreparedStatement pstmt = cnx.prepareStatement("SELECT * FROM Utilisateurs WHERE utilisateurId = ?");
				pstmt.setInt(1, Id);
				ResultSet rs = pstmt.executeQuery(); 
			if(rs.next()) {
				pseudo.setUtilisateurId(rs.getInt("utilisateurId"));
				pseudo.setPseudo(rs.getString("pseudo"));
				pseudo.setNom(rs.getString("nom"));
				pseudo.setPrenom(rs.getString("prenom"));
				pseudo.setEmail(rs.getString("email"));
				pseudo.setTelephone(rs.getString("telephone"));
				pseudo.setRue(rs.getString("rue"));
				pseudo.setCodePostal(rs.getString("codePostal"));
				pseudo.setVille(rs.getString("ville"));
				pseudo.setMotDePasse(rs.getString("motDePasse"));
				pseudo.setCredit(rs.getInt("credit"));
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return pseudo;
	}
// Méthode update
	@Override
	public void update(Utilisateur utilisateur) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement("UPDATE Utilisateurs SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, codePostal = ?, ville = ?, motDePasse = ? WHERE utilisateurId = ?");
			
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom().toUpperCase());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getUtilisateurId());
			
			pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_USER_ECHEC);
			throw businessException;
		}
	}
	
// Méthode Delete	
	@Override
	public void delete(int Id) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement("DELETE FROM Utilisateurs WHERE utilisateurId = ?");
			pstmt.setInt(1, Id);
			pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_USER_ECHEC);
			throw businessException;
		}
	}

	@Override
	public Utilisateur selectByEmail(String email) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BY_EMAIL);) {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				utilisateur.setUtilisateurId(rs.getInt("utilisateurId"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setMotDePasse(rs.getString("motDePasse"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("codePostal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
				utilisateur.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_USER_ECHEC);
			throw businessException;
		}
		return utilisateur;
	}

	@Override
	public List<Utilisateur> selectAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCredit(int id, int credit) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SQL_UPDATE_CREDIT);
			pstmt.setInt(1, credit);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_USER_ECHEC);
			throw businessException;
		}
	}
}
