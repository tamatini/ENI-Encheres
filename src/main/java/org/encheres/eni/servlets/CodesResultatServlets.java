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
}