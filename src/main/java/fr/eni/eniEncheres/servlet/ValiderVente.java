package fr.eni.eniEncheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.eniEncheres.bll.ArticleManager;
import fr.eni.eniEncheres.bll.EnchereManager;
import fr.eni.eniEncheres.bll.UtilisateurManager;
import fr.eni.eniEncheres.bo.Utilisateur;
import fr.eni.eniEncheres.dal.BusinessException;

/**
 * Servlet implementation class ValiderVente
 */
@WebServlet("/ValiderVente")
public class ValiderVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("sessionUtilisateur");
		UtilisateurManager utilateurManager = new UtilisateurManager();
		ArticleManager articleManager= new ArticleManager();
		int noArticle = Integer.parseInt(request.getParameter("noArticle")); 
		int credit = Integer.parseInt(request.getParameter("credit")); 
		EnchereManager enchereManager = new EnchereManager();
		
		try {
			int noUtilisateur = utilateurManager.numeroUtilisateurByPseudo(utilisateur.getPseudo());
			if(articleManager.afficherPrixVente(noArticle)==0) {
				enchereManager.modifierCreditUtilisateur (credit,noUtilisateur);
			enchereManager.modifierPrixVente(credit, noArticle);
			RequestDispatcher r = request.getRequestDispatcher("afficherListeEnchere");
			r.forward(request, response);
			}else {
				RequestDispatcher r = request.getRequestDispatcher("afficherListeEnchere");
				r.forward(request, response);
			}
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
