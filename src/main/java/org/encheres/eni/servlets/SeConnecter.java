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
@WebServlet(urlPatterns={
		"/encheres/SeConnecter",
		"/encheres/SeDeconnecter"
	})
public class SeConnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getServletPath().equals("/encheres/SeConnecter" )) {
			List <Integer> listeCodesErreurs = new ArrayList<>();
			request.setAttribute("titre", "Se connecter");
			if (listeCodesErreurs.size() > 0) {
				request.setAttribute("listesCodesErreurs", listeCodesErreurs);
			}
			RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/Encheres/SeConnecter.jsp");
			rq.forward(request, response);			
		} else if (request.getServletPath().equals("/encheres/SeDeconnecter")) {
			HttpSession session = request.getSession(false);
			if (session.getAttribute("user") != null) {
				session.invalidate();				
			}
			response.sendRedirect(request.getContextPath()+"/encheres");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/Encheres/SeConnecter.jsp");
		
		UtilisateurBLL utilisateurBLL = new UtilisateurBLL();
		String pseudo, motDePasse;
		List<Integer> listeCodesErreurs = new ArrayList<>();

		pseudo = this.pseudo(request, listeCodesErreurs);
		motDePasse = this.motDePasse(request, listeCodesErreurs);
		
		if (listeCodesErreurs.size() > 0) {
			request.setAttribute("listeCodesErreurs", listeCodesErreurs);
		} else {
			try {
				Utilisateur utilisateur = utilisateurBLL.seConnecter(pseudo, motDePasse);
				if (utilisateur != null) {
					HttpSession session = request.getSession();
					session.setAttribute("user", utilisateur);
					response.sendRedirect(request.getContextPath()+"/encheres");
				}
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreurs", e.getListeCodesErreur());
				doGet(request, response);
			}
		}
	}
	
	private String pseudo(HttpServletRequest request, List<Integer> listeCodesErreurs) {
		String pseudo;
		pseudo = request.getParameter("pseudo");
		if (pseudo == null || pseudo.trim().equals("")) {
			listeCodesErreurs.add(CodesResultatServlets.PSEUDO_OBLIGATOIRE);
		}
		return pseudo;
	}
	
	private String motDePasse(HttpServletRequest request, List<Integer> listeCodesErreurs) {
		String motDePasse;
		motDePasse = request.getParameter("motDePasse");
		if (motDePasse == null || motDePasse.trim().equals("")) {
			listeCodesErreurs.add(CodesResultatServlets.MOT_DE_PASSE_OBLIGATOIRE);
		}
		return motDePasse;
	}
	
}
