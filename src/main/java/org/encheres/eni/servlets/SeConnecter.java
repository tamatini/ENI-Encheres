package org.encheres.eni.servlets;

import java.io.IOException;
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
import org.encheres.eni.bll.UtilisateurBLL;
import org.encheres.eni.bo.Utilisateur;

/**
 * Servlet implementation class SeConnecter
 */
@WebServlet("/seconnecter")
public class SeConnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/Encheres/SeConnecter.jsp");
		HttpSession session = request.getSession(false);
		if (session.getAttribute("utilisateur") != null) {
			response.sendRedirect("/ENIEncheres/encheres");			
		} else {
			rq.forward(request, response);			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/Encheres/SeConnecter.jsp");
		
		UtilisateurBLL utilisateurBLL = new UtilisateurBLL();
		String pseudo = request.getParameter("pseudo");
		String motDePasse = request.getParameter("motDePasse");
		List<Integer> listeCodesErreurs = new ArrayList<>();

		if (pseudo.isEmpty()) {
			listeCodesErreurs.add(CodesResultatServlets.PSEUDO_OBLIGATOIRE);
		}
		
		if (motDePasse.isEmpty() ) {
			listeCodesErreurs.add(CodesResultatServlets.MOT_DE_PASSE_OBLIGATOIRE);
		}
		
		if (listeCodesErreurs.size() > 0) {
			request.setAttribute("listeCodesErreurs", listeCodesErreurs);
		} else {
			try {
				Utilisateur utilisateur = utilisateurBLL.seConnecter(pseudo);
				if (utilisateur != null && motDePasse.equals(utilisateur.getMotDePasse())) {
					HttpSession session = request.getSession();
					session.setAttribute("user", utilisateur);
					rq = request.getRequestDispatcher("/encheres");
				} else {
					listeCodesErreurs.add(CodesResultatServlets.MOT_DE_PASSE_ERREUR);
					request.setAttribute("listeCodesErreur", listeCodesErreurs);
					doGet(request, response);
				}
			} catch (BusinessException e) {
				e.printStackTrace();
				listeCodesErreurs.add(30032);
			}
		}
		rq.forward(request, response);
	}

}
