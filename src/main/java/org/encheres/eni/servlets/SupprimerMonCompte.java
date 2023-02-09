package org.encheres.eni.servlets;

import java.io.IOException;


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
 * Servlet implementation class SupprimerMonCompte
 */
@WebServlet("/encheres/SupprimerMonCompte")
public class SupprimerMonCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurBLL utilisateurBLL = new UtilisateurBLL();
		HttpSession session = request.getSession(false);
		Utilisateur user = null;
		if(session !=null) {
			user = (Utilisateur) session.getAttribute("user");
			try {
				utilisateurBLL.supprimerMonCompte(user.getUtilisateurId());
				session.invalidate();
				response.sendRedirect(request.getContextPath() + "/encheres");
			} catch (BusinessException e) {
				// TODO Gérer l'expression lorsqu'un utilisateur a créé des articles
				request.setAttribute("Liste_codes_erreurs", e.getListeCodesErreur());
				response.sendRedirect(request.getContextPath()+"/encheres");
			}
		}
	}
}
