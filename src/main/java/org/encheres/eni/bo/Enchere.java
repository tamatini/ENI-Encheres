package org.encheres.eni.bo;

import java.time.LocalDate;

/**
 * Classe qui modélise une enchère
 * @author benocode
 */
public class Enchere {
	
	private int vendeurId;
	private int articleId;
	private LocalDate dateEnchere;
	private int montant_enchere;
	
	/**
	 * Constructeur tous paramètres
	 * @param vendeurId
	 * @param articleId
	 * @param dateEnchere
	 * @param montant_enchere
	 */
	public Enchere(int vendeurId, int articleId, LocalDate dateEnchere, int montant_enchere) {
		this.vendeurId = vendeurId;
		this.articleId = articleId;
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}
	
	/**
	 * Constructeur sans paramètres
	 */
	public Enchere() {
	}

	/* Getters et setters */
	public int getVendeurId() {
		return vendeurId;
	}

	public void setVendeurId(int vendeurId) {
		this.vendeurId = vendeurId;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
