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
		// TODO rediriger vers l'accueil si l'utilisateur n'est pas connecté
		
		// TODO rediriger vers la page de modification de la vente si l'utilisateur connecté est le détenteur de l'article
		// et si la date de début d'enchère n'est pas passée
		
		// TODO récupérer l'article depuis Encheres.jsp
		Article articleConsulte = new Article("PC Gamer", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do"
				+ "eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation"
				+ "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate"
				+ "velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in"
				+ "culpa qui officia deserunt mollit anim id est laborum.", LocalDate.of(2022, Month.SEPTEMBER, 1),
				LocalDate.of(2023, Month.MARCH, 5), 100, 250, 5, 1);
		String URLimage = "../Public/Images/pc.jpg";
//		System.out.println(articleConsulte);
		
		// TODO récupérer le nom du vendeur à l'aide du vendeurId de l'article
		String nomVendeur = "De Montmirail";
		
		// TODO récupérer le nom de la catégorie en fonction de la categoryId de l'article
		String categorie = "Informatique";
		
		// TODO récupérer le montant de la meilleur offre et le nom de l'utilisateur correspondant
		String nomGagnant = "Godefroy";
		int miseMax = 250;
		
		// TODO récupérer le point retrait correspondant à l'article
		Retrait retrait = new Retrait("15478 rue très loin", "75000", "Marseille");
		
		// Pour tester le fonctionnement : création du user
		Utilisateur user = new Utilisateur(101, "toto", "TANK", "Totor", "toto@gmail.com", null, "chemin du chemin", "85000",
				"Lossangeles", "ElephantRose*85", 1500, false);
		request.setAttribute("user", user);
		
		/* Modification du format de la date : LocalDate > Date */
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date dateFinEnchere_formatDate = Date.from(articleConsulte.getDateFinEncheres().atStartOfDay(defaultZoneId).toInstant());
		
		request.setAttribute("titre", articleConsulte.getNomArticle());
		request.setAttribute("article", articleConsulte);
		request.setAttribute("image", URLimage);
		request.setAttribute("retrait", retrait);
		request.setAttribute("categorie", categorie);
		request.setAttribute("nomGagnant", nomGagnant);
		request.setAttribute("nomVendeur", nomVendeur);
		request.setAttribute("miseMax", miseMax);
		request.setAttribute("dateFinEnchere_formatDate", dateFinEnchere_formatDate);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/DetailEnchere.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
