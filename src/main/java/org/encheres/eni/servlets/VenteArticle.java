package org.encheres.eni.servlets;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
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
import org.encheres.eni.bll.CategorieBLL;
import org.encheres.eni.bll.EncheresBLL;
import org.encheres.eni.bo.Categorie;
import org.encheres.eni.bo.Utilisateur;


/* L'utilisateur doit pouvoir afficher la page d'ajout d'article et créer un nouvel article, une fois l'article créer il est redirigé
 vers l'article */ 


/**
 * Servlet implementation class VenteArticle
 */
@WebServlet(urlPatterns={
		"/encheres/nouvelArticle",
		"/encheres/supprimerArticle"
	})

public class VenteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getServletPath().equals("/encheres/nouvelArticle")) {
			List<Integer> listeCodesErreurs = new ArrayList<>();
			if (listeCodesErreurs.size() > 0) {
				request.setAttribute("listeCodesErreurs", listeCodesErreurs);
			} else {
				listeCategories(request, listeCodesErreurs);
				request.setAttribute("titre", "Vendre un article");				
			}
		} else if (request.getServletPath().equals("/encheres/supprimerArticle")) {
			System.out.println("supprimer article");
		}
		
		RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/Encheres/AjouterArticle.jsp");
		rq.forward(request, response);				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Integer> listeCodesErreurs = new ArrayList<>();
		String nomArticle, description;
		int categorieId, prixInitial, prixVente, vendeurId;
		LocalDate dateDebutEnchere, dateFinEnchere;
		
		
		nomArticle = this.nomArticle(request, listeCodesErreurs);
		description = this.description(request, listeCodesErreurs);
		dateDebutEnchere = this.dateDebutEnchere(request, listeCodesErreurs);
		dateFinEnchere = this.dateFinEnchere(request, listeCodesErreurs);
		prixInitial = this.prixInitial(request, listeCodesErreurs);
		prixVente = prixInitial;
		categorieId = this.categorieId(request, listeCodesErreurs);
		vendeurId = this.vendeurId(request, listeCodesErreurs);
		
	
		/* En attente du push de la bo retrait */
		//String rue = (String)request.getAttribute("rue");
		//String codePostal = (String)request.getAttribute("codePostal");
		//String ville = (String)request.getAttribute("ville");
		//article = new Article(nomArticle, description, dateDebutEnchere, dateFinEnchere, 0, prixInitial, ven);
		
		if (listeCodesErreurs.size() > 0) {
			request.setAttribute("listeCodesErreurs", listeCodesErreurs);
		} else {
			EncheresBLL encheresBLL = new EncheresBLL();
			try {
				encheresBLL.creerArticle(nomArticle, description, dateDebutEnchere, dateFinEnchere, prixInitial, prixVente, vendeurId, categorieId);
				listeCategories(request, listeCodesErreurs);
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreurs", e.getListeCodesErreur());
			}			
		}
		RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/Encheres/AjouterArticle.jsp");
		rq.forward(request, response);	
	}
	
	private void listeCategories(HttpServletRequest request, List<Integer> listeCodesErreurs) {
		try {
			CategorieBLL categorieBLL = new CategorieBLL();
			request.setAttribute("categories", categorieBLL.listeCategorie());
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreurs", listeCodesErreurs);
		}		
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
	 * Vérifie si le champ prixInitial n'est pas vide
	 * @param request la requête
	 * @param listeCodesErreurs la liste des codes des erreurs
	 * @return le prix de Initial
	 */
	private int prixInitial(HttpServletRequest request, List<Integer> listeCodesErreurs) {
		int prixInitial = 0;
		try {
			if (request.getParameter("prixInitial") != null || Integer.parseInt(request.getParameter("prixInitial")) != 0) {
				prixInitial = Integer.parseInt(request.getParameter("prixInitial"));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreurs.add(CodesResultatServlets.PRIX_INITIAL_OBLIGATOIRE);
		}
		return prixInitial;
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
