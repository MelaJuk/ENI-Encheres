package fr.eni.eniEncheres.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import fr.eni.eniEncheres.dal.DAOFactory;
import fr.eni.eniEncheres.dal.UtilisateurDAO;

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
				request.setAttribute("ventestermines",ventes);
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
		int montantEnchere; 
	
		Utilisateur utilisateur = new Utilisateur(); 
		
		
		
		// récupérer et affecter l'utilisateur de la session 
		noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur")); 
		
		request.setAttribute("noUtilisateur", "noUtilisateur"); 
		
		
		//  récupérer l'article concerné 
		noArticle = Integer.parseInt(request.getParameter("noArticle")); 
		request.setAttribute("noArticle", "noArticle"); 
		
		//récupérer le montant de la nouvelle enchère
		nvlleEnchere = Integer.parseInt(request.getParameter("montant_nvlle_enchere"));
		request.setAttribute("montant_nvlle_enchere", "montant_nvlle_enchere"); 
		
		
		// vérifier les condition de l'enchère 
		// récupérer l'ancienne enchère 
		if(request.getParameter("montant_enchere")!=null) {
			montantEnchere = Integer.parseInt(request.getParameter("montant_enchere")); 
		}else{
			montantEnchere=0;
		}
		
		
		request.setAttribute("montant_enchere", "montant_enchere"); 
		System.out.println(montantEnchere);
		System.out.println();
		
		// vérifier que la nouvelle enchere est supérieure et que crédit suffisant 
		if (nvlleEnchere > montantEnchere) {
			if (utilisateur.getCredit()>nvlleEnchere) {
				// ajouter l'enchère 
				EnchereManager enchereManager = new EnchereManager(); 
				try {
					// ajouter enchère 
					enchereManager.encherir(LocalDate.now(), nvlleEnchere, noArticle, noUtilisateur);
					// débiter acheteur 
					
					//re créditer ancien enchérisseur 
					
				} catch (BusinessException e) {
					e.printStackTrace();
				} 
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/validationEnchere.jsp"); 
				rd.forward(request, response);
			} else {
				PrintWriter out = response.getWriter(); 
				out.println("Le montant de votre crédit est insuffisant");
				out.close();
			}
			
		} else {
			PrintWriter out = response.getWriter(); 
			out.println("Le montant de votre enchère doit etre supérieur à l'enchère actuelle");
			out.close();
		}
		
	}

	
	
}
