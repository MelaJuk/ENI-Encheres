package fr.eni.eniEncheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.eniEncheres.bll.UtilisateurManager;
import fr.eni.eniEncheres.bo.Utilisateur;
import fr.eni.eniEncheres.dal.BusinessException;

/**
 * Servlet implementation class ServletSupprimerCompte
 */
@WebServlet("/ServletSupprimerCompte")
public class ServletSupprimerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/supprimerCompteUtilisateur.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateurSupprime = (Utilisateur) session.getAttribute("sessionUtilisateur");
		try {
			utilisateurManager.supprimerCompteUtilisateur(utilisateurSupprime.getPseudo());
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("Ventes");
		rd.forward(request, response);
	}

}
