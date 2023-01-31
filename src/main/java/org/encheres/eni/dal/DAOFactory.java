package org.encheres.eni.dal;

import org.encheres.eni.bo.Utilisateur;
import org.encheres.eni.dal.jdbc.UtilisateurDAOJdbcImpl;


public abstract class DAOFactory {
	
	private static DAO<Utilisateur> utilisateurDAO;

	public static DAO<Utilisateur> getUtilisateurDAO() {
		if (utilisateurDAO == null) {
			utilisateurDAO = new UtilisateurDAOJdbcImpl();
		}
		return utilisateurDAO;
	}
}
