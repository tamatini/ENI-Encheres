package org.encheres.eni.bo;

import java.time.LocalDate;

/**
 * Classe qui modélise une enchère
 * @author benocode
 */
public class Enchere {
	
	private Utilisateur vendeur;
	private Article article;
	private LocalDate dateEnchere;
	private int montant_enchere;
	
	/**
	 * Constructeur tous paramètres
	 * @param vendeur
	 * @param article
	 * @param dateEnchere
	 * @param montant_enchere
	 */
	public Enchere(Utilisateur vendeur, Article article, LocalDate dateEnchere, int montant_enchere) {
		this.vendeur = vendeur;
		this.article = article;
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}

	/**
	 * Constructeur sans paramètres
	 */
	public Enchere() {
	}

	/* Getters & Setters */
	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
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
}
