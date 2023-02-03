package org.encheres.eni.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.encheres.eni.BusinessException;
import org.encheres.eni.bll.UtilisateurBLL;
import org.encheres.eni.bo.Utilisateur;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/encheres/inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L; 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("titre", "Inscription");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Inscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher rd;
		UtilisateurBLL utilisateurBLL = new UtilisateurBLL();
		Utilisateur nouvelUtilisateur = new Utilisateur();
		String controle_motDePasse = null;
		
		try {
			nouvelUtilisateur.setPseudo((String) request.getParameter("pseudo"));
			nouvelUtilisateur.setNom((String) request.getParameter("nom"));
			nouvelUtilisateur.setPrenom((String) request.getParameter("prenom"));
			nouvelUtilisateur.setEmail((String) request.getParameter("email"));
			nouvelUtilisateur.setTelephone((String) request.getParameter("telephone"));
			nouvelUtilisateur.setRue((String) request.getParameter("rue"));
			nouvelUtilisateur.setCodePostal((String) request.getParameter("codePostal"));
			nouvelUtilisateur.setVille((String) request.getParameter("ville"));
			nouvelUtilisateur.setMotDePasse((String) request.getParameter("motDePasse"));
			controle_motDePasse = (String) request.getParameter("controle_motDePasse");
			
			System.out.println(nouvelUtilisateur.toString());
			utilisateurBLL.creerUtilisateur(nouvelUtilisateur, controle_motDePasse);
			
		} catch (BusinessException e) {
			request.setAttribute("Liste_codes_erreurs", e.getListeCodesErreur());
			request.setAttribute("donnees_formulaire", nouvelUtilisateur);
			request.setAttribute("controle_motDePasse", controle_motDePasse);
			// Voir avec le prof pour erreur : Impossible d'utiliser faire-suivre (forward) après que la réponse ait été envoyée
			doGet(request, response);
		}
		rd = request.getRequestDispatcher("/WEB-INF/Encheres/Encheres.jsp");
		rd.forward(request, response);
	}
}
