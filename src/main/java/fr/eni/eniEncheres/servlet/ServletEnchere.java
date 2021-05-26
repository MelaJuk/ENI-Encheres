package fr.eni.eniEncheres.servlet;

import java.io.IOException;
import java.util.List;

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
import fr.eni.eniEncheres.bo.ArticleVendu;
import fr.eni.eniEncheres.bo.Utilisateur;
import fr.eni.eniEncheres.dal.BusinessException;

/**
 * Servlet implementation class ServletEnchere
 */
@WebServlet(urlPatterns = {"/ServletAjouterEnchere","/afficherListeEnchere"})
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
		//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailVente.jsp"); 
		//rd.forward(request, response);
		
		if(request.getServletPath().equals("/afficherListeEnchere")) {
			EnchereManager enchereManager = new EnchereManager();
			List<ArticleVendu> listeEncheres = null;
			String categorie =request.getParameter("categories");
			String nom = request.getParameter("nom");
			String achats = request.getParameter("encheres");
			HttpSession session = request.getSession(false);
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("sessionUtilisateur");
			UtilisateurManager utilateurManager = new UtilisateurManager();
		
			int noUtilisateur;
			if(categorie==null || categorie.equalsIgnoreCase("toute")) {
					categorie="%";
				}
				System.out.println(categorie);
			try {
				noUtilisateur = utilateurManager.numeroUtilisateurByPseudo(utilisateur.getPseudo());
				if (achats == null) {
				achats = "eouvertes";
				}
				
				
			switch (achats) {
			  case "eencours" :
				listeEncheres =enchereManager.listeArticleEnchereParAcheteur(categorie,nom, noUtilisateur);
			
			  break;
			  
			  case "eemportees" : 
				  break;
				  
			  case "eouvertes":
				
					listeEncheres =enchereManager.listeArticleEnchereOuverte(categorie,nom);
				
				}
			
				
				
				
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
				request.setAttribute("listeEncheres", listeEncheres);
			
				RequestDispatcher r = request.getRequestDispatcher("/WEB-INF/encheres.jsp");
				r.forward(request, response);
			
			
		}
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
