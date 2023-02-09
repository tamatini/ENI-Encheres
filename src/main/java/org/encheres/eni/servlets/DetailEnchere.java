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
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		
		// TODO rediriger vers la page de modification de la vente si l'utilisateur connecté est le détenteur de l'article
		// et si la date de début d'enchère n'est pas passée
		
		try {
			// Gestion si pas d'utilisateur connecté
			if (user == null) {
				NullPointerException npe = new NullPointerException("Vous devez être connecté pour afficher cette page");
				System.err.println(npe.getMessage());
				throw npe;
			}
			EncheresBLL encheresBLL = new EncheresBLL();
			UtilisateurBLL utilisateurBLL = new UtilisateurBLL();
			
			// Actualisation du profil de l'utilisateur courant avec les données de la BDD
			int userId = user.getUtilisateurId();
			user = utilisateurBLL.afficherProfil(userId);
			request.setAttribute("user", user);
			
			int articleId = Integer.parseInt(request.getParameter("id")); // Peut lever une NumberFormatException redirigée vers 404
	
			// Affichage de la derniere enchère et du titre de la page
			Enchere derniereEnchere = encheresBLL.afficherMeilleureEnchere(articleId);
			request.setAttribute("enchere", derniereEnchere);
			request.setAttribute("titre", derniereEnchere.getArticle().getNomArticle());
			
			// Affichage du point retrait
			Retrait retrait = encheresBLL.afficherRetrait(articleId);
			request.setAttribute("retrait", retrait);
			
			// Affichage du pseudo du vendeur
			UtilisateurBLL vendeur = new UtilisateurBLL();
			String nomVendeur = vendeur.afficherProfil(derniereEnchere.getArticle().getVendeurId()).getPseudo();
			request.setAttribute("nomVendeur", nomVendeur);
			
			// Affichage du libellé de la catégorie
			CategorieBLL categorieArticle = new CategorieBLL();
			String categorie = categorieArticle.afficherCategorie(derniereEnchere.getArticle().getCategoryId()).getLibelle();
			request.setAttribute("categorie", categorie);
			
			// Affichage des dates
			Date debut_enchere = localDateToDate(derniereEnchere.getArticle().getDateDebutEncheres());
			request.setAttribute("dateDebutEnchere", debut_enchere);
			Date date_fin_enchere = localDateToDate(derniereEnchere.getArticle().getDateFinEncheres());
			request.setAttribute("dateFinEnchere", date_fin_enchere);
			Date date_aujourdhui = localDateToDate(LocalDate.now());
			request.setAttribute("dateAujourdhui", date_aujourdhui);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/DetailEnchere.jsp");
			rd.forward(request, response);
		
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (NullPointerException npe) {
			npe.printStackTrace();
			response.sendRedirect(request.getContextPath()+"/encheres");
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
		
		try {
			// Gestion si pas d'utilisateur connecté TODO à tester
			if (session == null) {
				NullPointerException npe = new NullPointerException("Vous devez être connecté pour afficher cette page");
				throw npe;
			}
			Utilisateur user = (Utilisateur) session.getAttribute("user");
			int userId = user.getUtilisateurId();
			
			int articleId = Integer.parseInt(request.getParameter("id"));
			int nouvelleOffre = Integer.parseInt(request.getParameter("nouvelleOffre"));
			
			encheresBLL.creerEnchere(articleId, userId, nouvelleOffre);
			
			response.sendRedirect(request.getContextPath()+"/encheres/detailEnchere?id=" + articleId);
			
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (NullPointerException npe) {
			npe.printStackTrace();
			response.sendRedirect(request.getContextPath()+"/encheres");
		} catch (BusinessException be) {
			be.printStackTrace();
			request.setAttribute("Liste_codes_erreurs", be.getListeCodesErreur());
			request.setAttribute("id", Integer.parseInt(request.getParameter("id")));
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
