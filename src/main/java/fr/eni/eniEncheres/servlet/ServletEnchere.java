package fr.eni.eniEncheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniEncheres.bll.ArticleManager;
import fr.eni.eniEncheres.bll.UtilisateurManager;
import fr.eni.eniEncheres.bo.ArticleVendu;

/**
 * Servlet implementation class ServletEnchere
 */
@WebServlet(urlPatterns = {"/ServletAjouterEnchere"})
public class ServletEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//test
		ArticleManager articleManager = new ArticleManager(); 
		request.setAttribute("articleVendu", articleManager.afficherArticle(12));
		
		/*ArticleManager articleManager = new ArticleManager(); 
		// a corriger car NumberFormatException 
		int noArticle = Integer.parseInt("noArticle"); 
		request.setAttribute("articleVendu", articleManager.afficherArticle(noArticle));
		*/
				
				
		// afficher les détails de la vente en cours 
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailVente.jsp"); 
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// pour récupérer l'utilisateur de la session --> besoin d'un utilisateur
		//UtilisateurManager utilisateurManager = new UtilisateurManager(); 
		
		// pour récupérer la proposition d'enchère --> besoin d'un article 
		//ArticleManager articleManager = new ArticleManager(); 
		
		// récupération de l'utilisateur (acheteur, celui qui enchérit)
		//noUtilisateur =Integer.parseInt(request.getParameter("noUtilisateur")) 
		
		
		//ajouter une proposition d'enchères (récupération de montant_enchere
		
		
		
	}

}
