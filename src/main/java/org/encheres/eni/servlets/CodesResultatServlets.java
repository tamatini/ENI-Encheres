package org.encheres.eni.servlets;

/**
 * Les codes disponibles sont entre 30000 et 39999
 */
public abstract class CodesResultatServlets {
	
	/**
	 * Erreur mot de passe
	 */
	public static final int FORMAT_UTILISATEUR_ERREUR=30000;
	
	/**
	 * Format saisie utilisateur incorrect
	 */
	public static final Integer PSEUDO_OBLIGATOIRE = 30001;
	public static final Integer MOT_DE_PASSE_OBLIGATOIRE = 30002;
	public static final Integer MOT_DE_PASSE_ERREUR = 30003;
	
	/**
	 * Format saisir article incorrect
	 */
	public static final Integer NOM_ARTICLE_OBLIGATOIRE = 30010;
	public static final Integer DESCRIPTION_ARTICLE_OBLIGATOIRE = 30011;
	public static final Integer CATEGORIE_ARTICLE_OBLIGATOIRE = 30012;
	public static final Integer PRIX_INITIAL_OBLIGATOIRE = 30013;
	public static final Integer DATE_DEBUT_ENCHERE_OBLIGATOIRE = 30014;
	public static final Integer DATE_FIN_ENCHERE_OBLIGATOIRE = 30015;
	public static final Integer VENDEUR_CONNEXION_OBLIGATOIRE = 30016;
}