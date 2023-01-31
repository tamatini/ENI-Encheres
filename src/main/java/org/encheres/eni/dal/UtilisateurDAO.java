package org.encheres.eni.dal;

import org.encheres.eni.bo.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur> {
	
	public Utilisateur SelectByPseudo(String pseudo);
}