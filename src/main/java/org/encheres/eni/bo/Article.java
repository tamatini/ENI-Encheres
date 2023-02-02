package org.encheres.eni.bo;

import java.time.LocalDateTime;

/**
 * @author Jeffrey TEAHUI
 * @date 02/2023
 */
public class Article {
	private int articleId;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private int vendeurId;
	private int categoryId;
	
	/**
	 * Constructeur de la classe objet article sans paramètre
	 */
	public Article() {
		super();
	}
	
	/**
	 * Constructeur de la classe objet article sans articleId en paramètre
	 * @param nomArticle le nom
	 * @param description la description
	 * @param dateDebutEncheres la date de début d'enchère
	 * @param dateFinEncheres la date de fin d'enchère
	 * @param prixInitial le prix initial
	 * @param prixVente le prix de vente
	 * @param vendeurId le vendeur
	 * @param categoryId la catégorie
	 */
	public Article(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int prixInitial, int prixVente, int vendeurId, int categoryId) {
		super();
		this.setNomArticle(nomArticle);
		this.setDescription(description);
		this.setDateDebutEncheres(dateDebutEncheres);
		this.setDateFinEncheres(dateFinEncheres);
		this.setPrixInitial(prixInitial);
		this.setPrixVente(prixVente);
		this.setVendeurId(vendeurId);
		this.setCategoryId(categoryId);
	}
	
	/** 
	 * Constructeur de la classe objet article en passant le paramètre articleId
	 * @param articleId l'id de l'article
	 * @param nomArticle le nom
	 * @param description la description
	 * @param dateDebutEncheres la date de début d'enchère
	 * @param dateFinEncheres la date de fin d'enchère
	 * @param prixInitial le prix initial
	 * @param prixVente le prix de vente
	 * @param vendeurId le vendeur
	 * @param categoryId la catégorie
	 */
	public Article(int articleId, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int prixInitial, int prixVente, int vendeurId, int categoryId) {
		this(nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial, prixVente, vendeurId, categoryId);
		this.setArticleId(articleId);
	}


	/**
	 * @return l'id de l'article
	 */
	public int getArticleId() {
		return articleId;
	}
	
	/**
	 * 
	 * @param articleId l'id de l'article
	 */
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
	/**
	 * @return le nom de l'article
	 */
	public String getNomArticle() {
		return nomArticle;
	}
	
	/**
	 * @param nomArticle le nom de l'article
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	
	/**
	 * @return la description de l'article
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description la description de l'article
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return la date de début des enchères
	 */
	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	
	/**
	 * @param dateDebutEncheres la date de début des enchères
	 */
	public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	
	/**
	 * @return la date de fin des enchères
	 */
	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}
	
	/**
	 * @param dateFinEncheres la date des fin des enchères
	 */
	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	
	/**
	 * @return le prix initial
	 */
	public int getPrixInitial() {
		return prixInitial;
	}
	
	/**
	 * @param prixInitial le prix initiale
	 */
	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}
	
	/**
	 * @return le prix de vente
	 */
	public int getPrixVente() {
		return prixVente;
	}
	
	/**
	 * @param prixVente le prix de vente
	 */
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	
	/**
	 * @return l'id du vendeur
	 */
	public int getVendeurId() {
		return vendeurId;
	}
	
	/**
	 * @param vendeurId l'id du vendeur
	 */
	public void setVendeurId(int vendeurId) {
		this.vendeurId = vendeurId;
	}
	
	/**
	 * @return l'id de la catégorie
	 */
	public int getCategoryId() {
		return categoryId;
	}
	
	/**
	 * @param categoryId l'id de la catégorie
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	/**
	 * Article [articleId: 1, nomArticle: pc gamer, description: pc gamer neuf, dateDebutEncheres: 02-02-2022-00:00:00:000, 
	 * 		dateFinEncheres: 02-02-2022-00:00:00:000, prixInitial: 180, prixVente: 100, vendeurId: 1, categorieId: 1]  
	 */
	@Override
		public String toString() {
			return "Article [" 
					+ "articleId: " + this.getArticleId()
					+ ", nomArticle: " + this.getNomArticle()
					+ ", description: " + this.getDescription()
					+ ", dateDebutEncheres: " + this.getDateDebutEncheres()
					+ ", dateFinEncheres: " + this.getDateFinEncheres()
					+ ", prixInitiale: " + this.getPrixInitial()
					+ ", prixVente: " + this.getPrixVente()
					+ ", vendeurId: " + this.getVendeurId()
					+ ", categorieId: " + this.getCategoryId()
					+ "]"
				;
		}
}
