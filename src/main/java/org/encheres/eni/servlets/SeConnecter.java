package org.encheres.eni.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.encheres.eni.BusinessException;
import org.encheres.eni.bll.UtilisateurBLL;

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
		String isConnected = "false";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("isConnected")) {
					isConnected = cookie.getValue();
					break;
				}
			}
		}
		
		if (!isConnected.equals("true")) {
			rq.forward(request, response);
			
		} else {
			response.sendRedirect("/ENIEncheres/encheres");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rq = request.getRequestDispatcher("/WEB-INF/Encheres/SeConnecter.jsp");
		// passer l'état de l'utilisateur sur la session
		UtilisateurBLL utilisateurBLL = new UtilisateurBLL();
		String pseudo = request.getParameter("pseudo");
		String motDePasse = request.getParameter("motDePasse");
		try {
			boolean isConnected = utilisateurBLL.seConnecter(pseudo, motDePasse);
			if (isConnected) {
				Cookie cookie = new Cookie("isConnected", "true");
				response.addCookie(cookie);
				response.sendRedirect("/ENIEncheres/encheres");
			} else {
				rq.forward(request, response);				
			}
			
		} catch (BusinessException e) {
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			e.printStackTrace();
		}
	}

}
