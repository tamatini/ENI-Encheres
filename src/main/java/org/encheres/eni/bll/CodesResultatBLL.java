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
	public static final int REGLE_PSEUDO_ERREUR=20020;

}
