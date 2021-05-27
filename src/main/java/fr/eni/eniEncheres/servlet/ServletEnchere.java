package fr.eni.eniEncheres.servlet;

import java.io.IOException;
import java.time.LocalDate;
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
import fr.eni.eniEncheres.bo.Enchere;
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
		ArticleManager articleManager = new ArticleManager();
		if(request.getServletPath().equals("/ServletAjouterEnchere")) {
			//test
			
			
			
			int noArticle = Integer.parseInt(request.getParameter("noArticle")); 
			request.setAttribute("articleVendu", articleManager.afficherArticle(noArticle));
			
					
					
			// afficher les détails de la vente en cours 
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailVente.jsp"); 
			rd.forward(request, response);
		}
	
		
		if(request.getServletPath().equals("/afficherListeEnchere")) {
			EnchereManager enchereManager = new EnchereManager();
			
			List<ArticleVendu> listeEncheres = null;
			List<ArticleVendu> listeVentes = null;
			String categorie =request.getParameter("categories");
			String nom = request.getParameter("nom");
			String achats = request.getParameter("encheres");
			HttpSession session = request.getSession(false);
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("sessionUtilisateur");
			UtilisateurManager utilateurManager = new UtilisateurManager();
			String choix = request.getParameter("choix");
			String ventes = request.getParameter("ventes");
			
			int noUtilisateur;
			if(categorie==null || categorie.equalsIgnoreCase("toute")) {
				categorie="%";
			}
			if(choix==null) {
				choix="mesAchats";
			}
			
			try {
				

				if(choix.equalsIgnoreCase("mesAchats")) {
					 
					if (achats == null) {
					achats = "eouvertes";
					}
			
					switch (achats) {
					  case "eencours" :
						
						noUtilisateur = utilateurManager.numeroUtilisateurByPseudo(utilisateur.getPseudo());
						listeEncheres =enchereManager.listeArticleEnchereParAcheteur(categorie,nom, noUtilisateur);
					
					  break;
					  
					  case "eemportees" : 
						  break;
						  
					  case "eouvertes":
						
							listeEncheres =enchereManager.listeArticleEnchereOuverte(categorie,nom);
							break;
					default : break;
						
						}
			
				}else {
					if (ventes == null) {
						ventes = "vencours";
						}
					noUtilisateur = utilateurManager.numeroUtilisateurByPseudo(utilisateur.getPseudo());
					
					switch (ventes) {
					  case "vencours" :
						
						  listeEncheres = articleManager.listeArticleVendusParCategorieParNomParPseudo(categorie, nom,noUtilisateur);
						  break;
					  
					  
					  case "vndebutees" :  listeEncheres = articleManager.listeArticleVendusParCategorieParNomParPseudoNonDebutees(categorie, nom,noUtilisateur);
						  break;
						  
					  case "vterminees": listeEncheres = articleManager.listeArticleVendusParCategorieParNomParPseudoTerminees(categorie, nom,noUtilisateur);
						
							
							break;
					default : break;
						
						}
				
					
				
				}	
				request.setAttribute("listeEncheres", listeEncheres);
			RequestDispatcher r = request.getRequestDispatcher("/WEB-INF/encheres.jsp");
				r.forward(request, response);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}
			
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// lire les paramètres 
		int noUtilisateur; 
		int nvlleEnchere; 
		int noArticle ; 
		
		// récupérer et affecter l'utilisateur de la session 
		//noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur")); 
		
		//request.setAttribute("noUtilisateur", "noUtilisateur"); 
		//System.out.println(noUtilisateur);
		
		//  récupérer l'article concerné 
		noArticle = Integer.parseInt(request.getParameter("noArticle")); 
		request.setAttribute("noArticle", "noArticle"); 
		System.out.println(noArticle);
		
		//récupérer le montant de l'enchère
		nvlleEnchere = Integer.parseInt(request.getParameter("montant_nvlle_enchere"));
		request.setAttribute("montant_nvlle_enchere", "montant_nvlle_enchere"); 
		System.out.println(nvlleEnchere);
		
		// test jusque là 
		
		// vérifier les condition de l'enchère 
		
		// ajouter l'enchère 
		EnchereManager enchereManager = new EnchereManager(); 
		try {
			System.out.println("test");
			enchereManager.encherir(LocalDate.now(), nvlleEnchere, noArticle, 1);
		} catch (BusinessException e) {
			e.printStackTrace();
		} 
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jspTest.jsp"); 
		
	}

	// valider montant enchère 
	/*private boolean validerMontantEnchere(Enchere montanEnchere) {
	
		if () {
			return false ; 
		} else {
			return true ; 
		}
	}*/
	
	//valider crédit suffisant 
	
}
