package org.encheres.eni.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
import org.encheres.eni.bll.UtilisateurBLL;
import org.encheres.eni.bo.Article;
import org.encheres.eni.bo.Enchere;
import org.encheres.eni.bo.Retrait;
import org.encheres.eni.bo.Utilisateur;

/**
 * Servlet implementation class DetailEnchere
 */
@WebServlet("/encheres/detailEnchere")
public class DetailEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
				
		// TODO rediriger vers la page de modification de la vente si l'utilisateur connecté est le détenteur de l'article
		// et si la date de début d'enchère n'est pas passée
		
		try {
			
			// Gestion si pas d'utilisateur connecté
			if (session == null) {
				NullPointerException npe = new NullPointerException("Vous devez être connecté pour afficher cette page");
				System.out.println(npe.getMessage());
				throw npe;
			}
//			Utilisateur user = (Utilisateur) session.getAttribute("user");
			EncheresBLL encheresBLL = new EncheresBLL();
			
			int articleId = Integer.parseInt(request.getParameter("id")); // Peut lever une NumberFormatException redirigée vers 404
	
			// Récupération de la meilleure enchère avec l'article
			Enchere enchereGagnante = encheresBLL.afficherMeilleureEnchere(articleId);
			Article articleConsulte = enchereGagnante.getArticle();
			String URLimage = "../Public/Images/pc.jpg"; // TODO ajouter l'url à l'article
			
			// Récupération du point retrait correspondant à l'article
			Retrait retrait = encheresBLL.afficherRetrait(articleId);
			
			// Récupération du pseudo du vendeur
			UtilisateurBLL vendeur = new UtilisateurBLL();
			String nomVendeur = vendeur.afficherProfil(articleConsulte.getVendeurId()).getPseudo();
			
			// Récupération du libellé de la catégorie
			CategorieBLL categorieArticle = new CategorieBLL();
			String categorie = categorieArticle.afficherCategorie(articleConsulte.getCategoryId()).getLibelle();
			
			// Modification du format de la date
			Date date_fin_enchere = localDateToDate(articleConsulte.getDateFinEncheres());
			
			request.setAttribute("titre", articleConsulte.getNomArticle());
			request.setAttribute("enchere", enchereGagnante);
			request.setAttribute("image", URLimage); // A supprimer une fois la BDD modifiée
			request.setAttribute("retrait", retrait);
			request.setAttribute("categorie", categorie);
			request.setAttribute("nomVendeur", nomVendeur);
			request.setAttribute("dateFinEnchere_formatDate", date_fin_enchere);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/DetailEnchere.jsp");
			rd.forward(request, response);
		
		} catch (NumberFormatException | NullPointerException ne) {
			// TODO créer une page 404 personnalisée qui affiche la liste des erreurs
			ne.printStackTrace();
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (BusinessException be) {
			be.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		EncheresBLL encheresBLL = new EncheresBLL();
		HttpSession session = request.getSession();
		int articleId = 0;
		
		try {
			// Gestion si pas d'utilisateur connecté
			if (session == null) {
				NullPointerException npe = new NullPointerException("Vous devez être connecté pour afficher cette page");
				throw npe;
			}
			Utilisateur user = (Utilisateur) session.getAttribute("user");
			int userId = user.getUtilisateurId();
			int nouvelleOffre = 0;
			
			articleId = Integer.parseInt(request.getParameter("articleId"));
			nouvelleOffre = Integer.parseInt(request.getParameter("nouvelleOffre"));
			
			encheresBLL.creerEnchere(articleId, userId, nouvelleOffre);
			response.sendRedirect(request.getContextPath()+"/encheres/detailEnchere?id=" + articleId);
			
		} catch (NumberFormatException nfe) {
			// TODO gérer l'affichage des erreurs sur la jsp DetailEnchere
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatServlets.FORMAT_NOUVELLE_OFFRE_ERREUR);
			request.setAttribute("Liste_codes_erreurs", businessException.getListeCodesErreur());
			request.setAttribute("id", articleId);
			nfe.printStackTrace();
			doGet(request, response);
		} catch (NullPointerException npe) {
			// TODO gérer l'affichage des erreurs sur la page d'accueil
			System.err.println("Vous avez été déconnecté, veuillez vous identifier");
			npe.printStackTrace();
			response.sendRedirect(request.getContextPath()+"/encheres");
		} catch (BusinessException be) {
			request.setAttribute("Liste_codes_erreurs", be.getListeCodesErreur());
			request.setAttribute("id", articleId);
			doGet(request, response);
		}
	}
	
	/**
	 * Méthode pour convertir un format LocalDate en format Date
	 * @param LocalDate
	 * @return Date
	 */
	public Date localDateToDate(LocalDate localDate) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		return date;
	}
}
