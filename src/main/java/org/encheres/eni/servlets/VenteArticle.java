package org.encheres.eni.servlets;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.encheres.eni.BusinessException;
import org.encheres.eni.bll.CategorieBLL;
import org.encheres.eni.bll.EncheresBLL;
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
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 1,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 100
	)

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
		// gestion des images
		// adresse de stockage de l'image
		String IMAGE_FOLDER = "/Public/Images/Articles";
		String uploadPath = getServletContext().getRealPath(IMAGE_FOLDER);
		String fileName = "";
		
		Part filePart = request.getPart("file");
		for (Part part : request.getParts()) {
			fileName = part.getSubmittedFileName();
			String fullPath = uploadPath + File.separator + fileName;
			part.write(fullPath);
		}
		fileName = filePart.getSubmittedFileName(); 

		
		List<Integer> listeCodesErreurs = new ArrayList<>();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");
		String nomArticle, description, rue, ville, codePostal, categorieId, prixInitial;
		int vendeurId;
		LocalDate dateDebutEnchere, dateFinEnchere;	
		
		nomArticle = request.getParameter("nomArticle");
		description = request.getParameter("description");
		dateDebutEnchere = LocalDate.parse(request.getParameter("dateDebut"));
		dateFinEnchere = LocalDate.parse(request.getParameter("dateFin"));
		prixInitial = request.getParameter("prixInitial");
		categorieId = request.getParameter("categorieId");
		vendeurId = utilisateur.getUtilisateurId();
		rue = request.getParameter("rue");
		ville = request.getParameter("ville");
		codePostal = request.getParameter("codePostal");
		
		if (listeCodesErreurs.size() > 0) {
			request.setAttribute("listeCodesErreurs", listeCodesErreurs);
		} else {
			EncheresBLL encheresBLL = new EncheresBLL();
			try {
				listeCategories(request, listeCodesErreurs);
				
				encheresBLL.creerArticle(nomArticle, description, dateDebutEnchere, dateFinEnchere, prixInitial, 
						vendeurId, categorieId, rue, ville, codePostal, fileName);
				
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
}
