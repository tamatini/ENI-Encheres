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
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Inscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		UtilisateurBLL nouvelUtilisateur = new UtilisateurBLL();
		String pseudo = (String) request.getAttribute("pseudo");
		String nom = (String) request.getAttribute("nom");
		String prenom = (String) request.getAttribute("prenom");
		String email = (String) request.getAttribute("email");
		String telephone = (String) request.getAttribute("telephone");
		String rue = (String) request.getAttribute("rue");
		String codePostal = (String) request.getAttribute("codePostal");
		String ville = (String) request.getAttribute("ville");
		String motDePasse = (String) request.getAttribute("motDePasse");
		String controle_motDePasse = (String) request.getAttribute("controle_motDePasse");
		
		try {
			nouvelUtilisateur.creerUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
