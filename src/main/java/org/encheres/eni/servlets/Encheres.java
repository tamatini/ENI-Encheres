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

import org.encheres.eni.BusinessException;
import org.encheres.eni.bll.CategorieBLL;
import org.encheres.eni.bll.EncheresBLL;

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
		request.setCharacterEncoding("UTF-8");
		List<Integer> listeCodesErreurs = new ArrayList<>();
		EncheresBLL encheresBLL = new EncheresBLL();
		CategorieBLL categorieBLL = new CategorieBLL();
		
		if (listeCodesErreurs.size() > 0) {
			request.setAttribute("listeCodesErreurs", listeCodesErreurs);
		} else {
			try {
				request.setAttribute("titre", "Accueil");
				request.setAttribute("articles", encheresBLL.listeEnchere());
				request.setAttribute("categories", categorieBLL.listeCategorie());
			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreurs", e.getListeCodesErreur());
			}			
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Encheres/Encheres.jsp");
		rd.forward(request, response);
	}
	
	
}
