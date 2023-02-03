package org.encheres.eni.servlets;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.encheres.eni.BusinessException;
import org.encheres.eni.bll.EncheresBLL;
import org.encheres.eni.bo.Article;
import org.encheres.eni.bo.Utilisateur;


/* L'utilisateur doit pouvoir afficher la page d'ajout d'article et créer un nouvel article, une fois l'article créer il est redirigé
 vers l'article */ 


/**
 * Servlet implementation class VenteArticle
 */
@WebServlet("/encheres/ventearticle")
public class VenteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/Encheres/AjouterArticle.jsp");
		request.setAttribute("titre", "Vendre un article");
		
		rq.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Integer> listeCodesErreurs = new ArrayList<>();
		String nomArticle, description;
		int categorieId, prixVente, vendeurId;
		LocalDate dateDebutEnchere, dateFinEnchere;
		
		
		nomArticle = this.nomArticle(request, listeCodesErreurs);
		description = this.description(request, listeCodesErreurs);
		dateDebutEnchere = this.dateDebutEnchere(request, listeCodesErreurs);
		dateFinEnchere = this.dateFinEnchere(request, listeCodesErreurs);
		prixVente = this.prixVente(request, listeCodesErreurs);
		categorieId = this.categorieId(request, listeCodesErreurs);
		vendeurId = this.vendeurId(request, listeCodesErreurs);
		
	
		/* En attente du push de la bo retrait */
		//String rue = (String)request.getAttribute("rue");
		//String codePostal = (String)request.getAttribute("codePostal");
		//String ville = (String)request.getAttribute("ville");
		//article = new Article(nomArticle, description, dateDebutEnchere, dateFinEnchere, 0, prixVente, ven);
		
		if (listeCodesErreurs.size() > 0) {
			request.setAttribute("listeCodesErreurs", listeCodesErreurs);
	
		} else {
			EncheresBLL encheresBLL = new EncheresBLL();
			Article article = new Article(nomArticle, description, dateDebutEnchere, dateFinEnchere, 0, 
					prixVente, vendeurId, categorieId);
			try {
				encheresBLL.creerArticle(article);
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreurs", e.getListeCodesErreur());
			}			
		}
		doGet(request, response);
	}
	
	/**
	 * Vérifie si le champ nomArticle n'est pas vide
	 * @param request la requête
	 * @param listeCodesErreurs la liste des codes des erreurs
	 * @return le nom de l'article
	 */
	private String nomArticle(HttpServletRequest request, List<Integer> listeCodesErreurs) {
		String nomArticle;
		nomArticle = request.getParameter("nomArticle");
		if (nomArticle == null || nomArticle.trim().equals("")) {
			listeCodesErreurs.add(CodesResultatServlets.NOM_ARTICLE_OBLIGATOIRE);
		}
		return nomArticle;
	}
	
	/**
	 * Vérifie si le champ description n'est pas vide
	 * @param request la requête
	 * @param listeCodesErreurs la liste des codes des erreurs
	 * @return la description
	 */
	private String description(HttpServletRequest request, List<Integer> listeCodesErreurs) {
		String description;
		description = request.getParameter("description");
		if (description == null || description.trim().equals("") ) {
			listeCodesErreurs.add(CodesResultatServlets.DESCRIPTION_ARTICLE_OBLIGATOIRE);
		}
		return description;
	}
	
	/**
	 * Vérifie si le champ catégorie n'est pas vide
	 * @param request la requête
	 * @param listeCodesErreurs la liste des codes des erreurs
	 * @return la catégorie
	 */
	private int categorieId(HttpServletRequest request, List<Integer> listeCodesErreurs) {
		int categorieId = 0;
		try {
			if (request.getParameter("categorieId") != null) {
				categorieId = Integer.parseInt(request.getParameter("categorieId"));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreurs.add(CodesResultatServlets.CATEGORIE_ARTICLE_OBLIGATOIRE);
		}
		return categorieId;
	}
	
	/**
	 * Vérifie si le champ prixVente n'est pas vide
	 * @param request la requête
	 * @param listeCodesErreurs la liste des codes des erreurs
	 * @return le prix de vente
	 */
	private int prixVente(HttpServletRequest request, List<Integer> listeCodesErreurs) {
		int prixVente = 0;
		try {
			if (request.getParameter("prixVente") != null || Integer.parseInt(request.getParameter("prixVente")) != 0) {
				prixVente = Integer.parseInt(request.getParameter("prixVente"));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreurs.add(CodesResultatServlets.PRIX_VENTE_OBLIGATOIRE);
		}
		return prixVente;
	}
	
	/**
	 * Vérifie si le champ dateDebut n'est pas vide
	 * @param request la requête
	 * @param listeCodesErreurs la liste des codes des erreurs
	 * @return la date de début de l'enchère
	 */
	private LocalDate dateDebutEnchere (HttpServletRequest request, List<Integer> listeCodesErreurs) {
		LocalDate dateDebutEnchere = LocalDate.now();
		try {
			if (request.getParameter("dateDebut") != null) {
				dateDebutEnchere = LocalDate.parse(request.getParameter("dateDebut"));
			}
		} catch (DateTimeException e) {
			e.printStackTrace();
			listeCodesErreurs.add(CodesResultatServlets.DATE_DEBUT_ENCHERE_OBLIGATOIRE);
			
		}
		return dateDebutEnchere;
	}
	
	/**
	 * Vérifie si le champ dateFin n'est pas vide
	 * @param request la requète
	 * @param listeCodesErreurs la liste des codes des erreurs
	 * @return la date de fin de l'enchère
	 */
	private LocalDate dateFinEnchere (HttpServletRequest request, List<Integer> listeCodesErreurs) {
		LocalDate dateFinEnchere = LocalDate.now();
		try {
			if (request.getParameter("dateFin") != null) {
				dateFinEnchere = LocalDate.parse(request.getParameter("dateDebut"));
			}
		} catch (DateTimeException e) {
			e.printStackTrace();
			listeCodesErreurs.add(CodesResultatServlets.DATE_FIN_ENCHERE_OBLIGATOIRE);
			
		}
		return dateFinEnchere;
	}
	
	/**
	 * Récupère l'id de l'utilisateur connecté
	 * @param request la requête
	 * @param listeCodesErreurs la liste des codes des erreurs
	 * @return l'id du vendeur
	 */
	private int vendeurId(HttpServletRequest request, List<Integer> listeCodesErreurs) {
		int vendeurId = 0;
		Utilisateur utilisateur;
		HttpSession session = request.getSession(false);
		try {
			if (session != null) {
				utilisateur = (Utilisateur)session.getAttribute("user");
				System.out.println(utilisateur.getUtilisateurId());
				vendeurId = utilisateur.getUtilisateurId();
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreurs.add(CodesResultatServlets.VENDEUR_CONNEXION_OBLIGATOIRE);
		}
		return vendeurId;
	}

}
