package org.encheres.eni.bll;

public abstract class CodesResultatBLL {
	
	/* Erreurs générales */
	public static final int INSERT_FIELD_NULL=20000;
	
	/* Erreurs création et modification utilisateur */
	public static final int INSERT_USER_PSEUDO_ERROR=20001;
	public static final int INSERT_USER_PSEUDO_OTHER=20002;
	public static final int INSERT_USER_NAME_ERROR=20003;
	public static final int INSERT_USER_FIRSTNAME_ERROR=20004;
	public static final int INSERT_USER_EMAIL_ERROR=20005;
	public static final int INSERT_USER_EMAIL_OTHER=20006;
	public static final int INSERT_USER_PHONE_ERROR=20007;
	public static final int INSERT_USER_STREET_ERROR=20008;
	public static final int INSERT_USER_ZIPCODE_ERROR=20009;
	public static final int INSERT_USER_CITY_ERROR=20010;
	public static final int INSERT_USER_PASSWORD_ERROR=20011;
	public static final int INSERT_USER_PASSWORD_OTHER=20012;
	public static final int REGLE_PSEUDO_ERREUR=20020;
	public static final int REGLE_MOT_DE_PASSE_ERREUR=20013;
	
	
	/* Erreur BLL article */
	public static final int REGLE_ARTICLE_DATE_ERREUR=20030;
	public static final int REGLE_ARTICLE_PRIX_ERREUR=20031;
	public static final int REGLE_ARTICLE_DESCRIPTION_ERREUR=20032;
	public static final int SELECT_BY_ID_ARTICLE_NULL=20040;
	
	public static final int NOM_ARTICLE_OBLIGATOIRE = 20033;
	public static final int DESCRIPTION_ARTICLE_OBLIGATOIRE = 20034;
	public static final int CATEGORIE_ARTICLE_OBLIGATOIRE = 20035;
	public static final int PRIX_INITIAL_OBLIGATOIRE = 20036;
	public static final int DATE_DEBUT_ENCHERE_OBLIGATOIRE = 20037;
	public static final int DATE_FIN_ENCHERE_OBLIGATOIRE = 20038;
	public static final int VENDEUR_CONNEXION_OBLIGATOIRE = 20039;
	
	/* Erreur BLL retrait */
	// Rue
	public static final int RUE_ARTICLE_OBLIGATOIRE = 20040;
	public static final int RUE_ARTICLE_LONGUEUR = 20041;
	
	// Ville
	public static final int VILLE_ARTICLE_OBLIGATOIRE = 20042;
	public static final int VILLE_ARTICLE_LONGUEUR = 20043;
	
	// CodePostal
	public static final int CODE_POSTAL_OBLIGATOIRE = 20044;
	public static final int CODE_POSTAL_LONGUEUR = 20045;
}
