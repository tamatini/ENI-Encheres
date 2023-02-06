package org.encheres.eni.dal;

public abstract class CodesResultatDAL {
	// Echec utilisateurs
	public static final int INSERT_USER_ECHEC=10002;
	public static final int SELECT_USER_ECHEC=10004;
	public static final int UPDATE_USER_ECHEC=10005;
	public static final int DELETE_USER_ECHEC=10003;
	
	// Echec objects
	public static final int INSERT_OBJECT_ECHEC=10002;
	public static final int SELECT_OBJECT_ECHEC=10020;

	// Echec Articles
	public static final int INSERT_ARTICLE_ECHEC=10010;
	public static final int SELECT_RETRAIT_ECHEC = 10031;
	public static final int SELECT_ARTICLE_ECHEC=10011;
	
	// Echec Catégories
	public static final int SELECT_ALL_CATEGORIES_ECHEC = 10030;
}
