package org.encheres.eni.servlets;

import java.io.IOException;

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
 * Servlet implementation class ModifierProfil
 */
@WebServlet("/encheres/ModifierProfil")
public class ModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/Encheres/ModifierProfil.jsp");
		rq.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UtilisateurBLL utilisateurBLL = new UtilisateurBLL(); // utiliser pour modifier utilisateur
		HttpSession session = request.getSession(false);
		Utilisateur user = null; 
		String controle_motDePasse = null;
		String nouveauPseudo = null;
		String nouveauEmail = null;
		if(session !=null) {
			user = (Utilisateur) session.getAttribute("user");
			System.out.println(user.toString());	
			try {
				nouveauPseudo = (String) request.getParameter("pseudo");
				user.setNom((String) request.getParameter("nom"));
				user.setPrenom((String) request.getParameter("prenom"));
				nouveauEmail = (String) request.getParameter("email");
				user.setTelephone((String) request.getParameter("telephone"));
				user.setRue((String) request.getParameter("rue"));
				user.setCodePostal((String) request.getParameter("codePostal"));
				user.setVille((String) request.getParameter("ville"));
				user.setMotDePasse((String) request.getParameter("motDePasse"));
				controle_motDePasse = (String) request.getParameter("controle_motDePasse");
				
				utilisateurBLL.modifierUtilisateur(user, controle_motDePasse, nouveauPseudo, nouveauEmail);
				
			} catch (BusinessException e) {
				request.setAttribute("Liste_codes_erreurs", e.getListeCodesErreur());
				doGet(request, response);
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Encheres.jsp");
		rd.forward(request, response);
	}
	
}
