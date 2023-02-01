package org.encheres.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.encheres.eni.BusinessException;
import org.encheres.eni.bo.Utilisateur;
import org.encheres.eni.dal.CodesResultatDAL;
import org.encheres.eni.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	private String SELECT_BY_PSEUDO = "SELECT pseudo, motDePasse from Utilisateurs WHERE pseudo=?";
	private static final String SQL_INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, "
			+ "rue, codePostal, ville, motDePasse, credit, administrateur) "
			+ "values (?,?,?,?,?,?,?,?,?,0,0)";
	
	/**
	 * La méthode retourne le pseudo et le mot de passe de l'utilisateur
	 * @param pseudo le pseudo de l'utilisateur
	 * @return l'utilisateur existant
	 * @throws BusinessException 
	 */
	public Utilisateur SelectByPseudo(String pseudo) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection conn = ConnectionProvider.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BY_PSEUDO);) {
			ps.setString(1, pseudo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String userPseudo = rs.getString("pseudo");
				String motDePasse = rs.getString("motDePasse");
				utilisateur.setPseudo(userPseudo);
				utilisateur.setMotDePasse(motDePasse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJECT_ECHEC);
			throw businessException;
		}
		return utilisateur;
	}
	
	@Override
	public void insert(Utilisateur utilisateur) throws BusinessException {
		/* TODO or notTODO : vérifier si insertion objet null
		if(utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_NULL);
			throw businessException;
		} */
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
				utilisateur.setUtilisateurId(rs.getInt("idArticle"));
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
				PreparedStatement pstmt = cnx.prepareStatement("SELECT utilisateurId, pseudo, nom, prenom, email, telephone, rue, codePostal, ville FROM Utilisateurs WHERE utilisateurId = ?");
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
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return pseudo;
	}

	@Override
	public Utilisateur selectByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
}
