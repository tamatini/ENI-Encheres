package org.encheres.eni.bo;

/**
 * @author Tamatini
 *
 */
public class Categorie {
	private int categorieId;
	private String libelle;
	
	
	
	/**
	 * Constructeur de la classe objet catégorie 
	 */
	public Categorie() {
		super();
	}
	
		
	/**
	 * @param libelle le libellé
	 */
	public Categorie(String libelle) {
		this.setLibelle(libelle);
	}

	/**
	 * @param categorieId l'id de la catégorie
	 * @param libelle le libellé
	 */
	public Categorie(int categorieId, String libelle) {
		this(libelle);
		this.setCategorieId(categorieId);
	}


	/**
	 * @return l'id de la catégorie
	 */
	public int getCategorieId() {
		return categorieId;
	}
	
	/**
	 * @param categorieId l'id de la catégorie
	 */
	public void setCategorieId(int categorieId) {
		this.categorieId = categorieId;
	}
	
	/**
	 * @return le libellé
	 */
	public String getLibelle() {
		return libelle;
	}
	
	/**
	 * @param libelle libellé
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	@Override
	public String toString() {
		return "Categorie [" 
					+ "categorieId: " + this.getCategorieId()
					+ "libelle: " + this.getLibelle()
					+ "]"
				;
	}
}
