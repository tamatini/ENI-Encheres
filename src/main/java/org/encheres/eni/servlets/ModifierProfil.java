// TODO quand toutes les données de l'uitilisateur temporaire sont ok, les tranferer dans le user. 


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
		request.setAttribute("titre", "Modifier mon profil");
		UtilisateurBLL utilisateurBLL = new UtilisateurBLL();
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Utilisateur utilisateur = utilisateurBLL.afficherProfil(id);
			request.setAttribute("utilisateur", utilisateur);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		rq.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UtilisateurBLL utilisateurBLL = new UtilisateurBLL();
		HttpSession session = request.getSession(false);
		Utilisateur user = null; 
		String controle_motDePasse = null;
		String nouveauPseudo = null;
		String nouveauEmail = null;
		String nouveauMotDePasse = null;
		if(session !=null) {
			user = (Utilisateur) session.getAttribute("user");
				
			try {
				
				nouveauPseudo = request.getParameter("pseudo");
				user.setNom((String) request.getParameter("nom"));
				user.setPrenom((String) request.getParameter("prenom"));
				nouveauEmail = request.getParameter("email");
				user.setTelephone((String) request.getParameter("telephone"));
				user.setRue((String) request.getParameter("rue"));
				user.setCodePostal((String) request.getParameter("codePostal"));
				user.setVille((String) request.getParameter("ville"));
				user.setMotDePasse((String) request.getParameter("motDePasse"));
				nouveauMotDePasse = request.getParameter("nouveauMotDePasse");
				controle_motDePasse = request.getParameter("controle_motDePasse");
				
				
				utilisateurBLL.modifierUtilisateur(user, controle_motDePasse, nouveauPseudo, nouveauEmail, nouveauMotDePasse);
				
				response.sendRedirect(request.getContextPath()+"/encheres");
				
			} catch (BusinessException e) {
				request.setAttribute("utilisateur", user);
				request.setAttribute("Liste_codes_erreurs", e.getListeCodesErreur());
				e.printStackTrace();
				request.getRequestDispatcher("/WEB-INF/Encheres/ModifierProfil.jsp").forward(request, response);
			
		}
	}
}
}
