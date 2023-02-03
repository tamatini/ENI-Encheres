package org.encheres.eni.servlets;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.encheres.eni.bll.UtilisateurBLL;
import org.encheres.eni.bo.Utilisateur;



/**
 * Servlet implementation class Profil
 */
@WebServlet("/encheres/profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/Encheres/Profil.jsp");
		UtilisateurBLL utilisateurBLL = new UtilisateurBLL();
		try {
			if(!request.getParameter("id").equals("")) {
				int utilisateurId = Integer.parseInt(request.getParameter("id"));
				Utilisateur utilisateur = utilisateurBLL.afficherProfil(utilisateurId);
				request.setAttribute("titre", "Profil de " + utilisateur.getPseudo());
				if(utilisateur.getUtilisateurId() != 0) {
					System.out.println(utilisateur.getUtilisateurId());
					request.setAttribute("utilisateur", utilisateur);
					rq.forward(request, response);	
				}
			}else {
				response.sendRedirect("/EniEncheres/encheres");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/EniEncheres/encheres");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
