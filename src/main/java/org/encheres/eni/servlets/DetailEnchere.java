package org.encheres.eni.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
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
import org.encheres.eni.bll.EncheresBLL;
import org.encheres.eni.bll.UtilisateurBLL;
import org.encheres.eni.bo.Article;
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
		
		
		// Gestion si pas de session utilisateur
		if (user == null) {
			NullPointerException npe = new NullPointerException("Vous devez être connecté pour afficher cette page");
			System.out.println(npe.getMessage());
			throw npe;
		} else {
			// TODO rediriger vers l'accueil si l'utilisateur n'est pas connecté
			
			// TODO rediriger vers la page de modification de la vente si l'utilisateur connecté est le détenteur de l'article
			// et si la date de début d'enchère n'est pas passée
			
			// TODO récupérer l'article depuis Encheres.jsp
			EncheresBLL encheresBLL = new EncheresBLL();
			
			try {
				int articleId = Integer.parseInt(request.getParameter("id"));
				Article articleConsulte = encheresBLL.afficherArticle(articleId);
				System.out.println(articleConsulte);

			// TODO ajouter l'url à l'article
			String URLimage = "../Public/Images/pc.jpg";
			
			// TODO récupérer le nom du vendeur à l'aide du vendeurId de l'article
			String nomVendeur = "De Montmirail";
			
			// TODO récupérer le nom de la catégorie en fonction de la categoryId de l'article
			String categorie = "Informatique";
			
			// TODO récupérer le montant de la meilleur offre et le nom de l'utilisateur correspondant
			String nomGagnant = "Godefroy";
			int miseMax = 250;
			
			// TODO récupérer le point retrait correspondant à l'article
			Retrait retrait = new Retrait("15478 rue très loin", "75000", "Marseille");
			
			/* Modification du format de la date */
			Date date_fin_enchere = localDateToDate(articleConsulte.getDateFinEncheres());
			
			request.setAttribute("titre", articleConsulte.getNomArticle());
			request.setAttribute("article", articleConsulte);
			request.setAttribute("image", URLimage);
			request.setAttribute("retrait", retrait);
			request.setAttribute("categorie", categorie);
			request.setAttribute("nomGagnant", nomGagnant);
			request.setAttribute("nomVendeur", nomVendeur);
			request.setAttribute("miseMax", miseMax);
			request.setAttribute("dateFinEnchere_formatDate", date_fin_enchere);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/DetailEnchere.jsp");
			rd.forward(request, response);
			
			} catch (NumberFormatException | NullPointerException e) {
				// TODO créer une page 404 qui affiche la liste des erreurs
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				e.printStackTrace();
			} catch (BusinessException be) {
				be.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idArticle = 0;
		try {
			idArticle = Integer.parseInt(request.getParameter("idArticle"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		response.sendRedirect(request.getContextPath()+"/encheres/detailEnchere?id=" + idArticle);
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
