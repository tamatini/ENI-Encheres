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
import org.encheres.eni.bll.EncheresBLL;
import org.encheres.eni.bo.Article;

/**
 * Servlet implementation class Encheres
 */
@WebServlet("/encheres")
public class Encheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(this.getServletContext().getRealPath("/Public/Images/tests"));
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Encheres.jsp");
		HttpSession session = request.getSession();
		request.setAttribute("titre", "Accueil");
		request.setAttribute("utilisateur", session.getAttribute("utilisateur"));
		EncheresBLL encheresBLL = new EncheresBLL();
		try {
			Article article = encheresBLL.afficherArticle(12);
			request.setAttribute("article", article);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
