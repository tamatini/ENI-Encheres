package org.encheres.eni.bo;

/**
 * Classe qui représente l'adresse du point de retrait
 * @author benocode
 */
public class Retrait {

	private String rue;
	private String codePostal;
	private String ville;
	private int articleId;
	
	/**
	 * Constructeur sans paramètres
	 */
	public Retrait() {
	}

	/**
	 * Constructeur tous paramètres
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	public Retrait(String rue, String codePostal, String ville, int articleId) {
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.articleId = articleId;
	}

	public String getrue() {
		return rue;
	}

	public void setrue(String rue) {
		this.rue = rue;
	}

	public String getcodePostal() {
		return codePostal;
	}

	public void setcodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getville() {
		return ville;
	}

	public void setville(String ville) {
		this.ville = ville;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}
}
