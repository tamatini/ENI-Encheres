package org.encheres.eni.bo;

/**
 * Classe qui représente l'adresse du point de retrait
 * @author benocode
 */
public class Retrait {

	private String rue;
	private String codePostal;
	private String ville;
	
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
	public Retrait(String rue, String codePostal, String ville) {
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
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

	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}
}
