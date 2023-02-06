package org.encheres.eni.dal;

public abstract class CodesResultatDAL {
	// Echec utilisateur
	public static final int INSERT_USER_ECHEC=10002;
	public static final int SELECT_USER_ECHEC=10004;
	public static final int UPDATE_USER_ECHEC=10005;
	public static final int DELETE_USER_ECHEC=10003;
	
	// Echec object
	public static final int INSERT_OBJECT_ECHEC=10002;
	public static final int SELECT_OBJECT_ECHEC=10020;
	
	// Echec Article
	public static final int INSERT_ARTICLE_ECHEC=10010;
	public static final int SELECT_ALL_CATEGORIES_ECHEC = 10030;
	public static final int SELECT_RETRAIT_ECHEC = 10031;
	
}
