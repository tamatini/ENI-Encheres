package org.encheres.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.encheres.eni.bo.Utilisateur;
import org.encheres.eni.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	private String SELECT_BY_PSEUDO = "SELECT pseudo, motDePasse from Utilisateurs WHERE pseudo=?";
	
	/**
	 * La méthode retourne le pseudo et le mot de passe de l'utilisateur
	 * @param pseudo le pseudo de l'utilisateur
	 * @return l'utilisateur existant
	 */
	public Utilisateur SelectByPseudo(String pseudo) {
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
			// Sinon cet utilisateur n'existe pas / mauvais nom d'utilisateur
		}
		return utilisateur;
	}
}
