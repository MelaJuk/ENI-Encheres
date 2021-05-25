package fr.eni.eniEncheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniEncheres.bll.ArticleManager;
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
	
		// afficher les détails de la vente en cours 
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailVente.jsp"); 
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// lire les parametres 
		//int noUtilisateur=null; 
		
		// récupération de l'utilisateur (acheteur)
		//noUtilisateur =Integer.parseInt(request.getParameter("noUtilisateur")) ;
		
		//récupération de la méthode afficherVente dans ArticleManager pour afficher :  nom article, description, cat, meilleure offre, mise à prix, fin enchère, retrait, vendeur)
	
		
		//récupération du nom du vendeur et lien vers son profil (/afficherProfil et afficherProfilUtilisateur.jsp)
		
		//ajouter une proposition d'enchères (récupération des info du form)
		
		
		
	}

}
