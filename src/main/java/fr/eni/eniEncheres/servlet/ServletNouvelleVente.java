package fr.eni.eniEncheres.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniEncheres.bll.ArticleManager;
import fr.eni.eniEncheres.bll.UtilisateurManager;
import fr.eni.eniEncheres.bo.ArticleVendu;
import fr.eni.eniEncheres.bo.Categorie;
import fr.eni.eniEncheres.bo.Utilisateur;
import fr.eni.eniEncheres.dal.BusinessException;


/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet(urlPatterns={"/ServletNouvelleVente","/ServletAnnulerVente"})

public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @param categorieArticle 
	 * @param vendeur 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

		protected void doPost(HttpServletRequest request, HttpServletResponse response){
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String nomArticle;
			String description;
		
				nomArticle = request.getParameter("nomArticle");
				description= request.getParameter("description");
				ArticleManager articleManager = new ArticleManager();
				ArticleVendu articleVendu = null;
				
					try {
						articleVendu = articleManager.ajouterVente(nomArticle, description);
					} catch (BusinessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					
				request.setAttribute("articleVendu", articleVendu);
			
			RequestDispatcher rd = request.getRequestDispatcher("detailVente.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
			

		/*if(request.getServletPath().equals("/ServletNouvelleVente")) {
			ArticleManager articleManager = new ArticleManager();
			
			String nomArticle = request.getParameter("NomArticle");
			String description = request.getParameter("description");
			//String dateDebutEncheres = request.getParameter("telephone");
			//String dateFinEncheres = request.getParameter("codePostal");
			//String miseAPrix = request.getParameter("miseaPrix");
			
			PrintWriter out = response.getWriter();
			
			
			System.out.println(articleManager.equals(nomArticle));
			
			
			
			if(!articleManager.equals(nomArticle)){	
				//out.println("Saisir le nom de l'article");
				//request.setAttribute("saisir nom article","saisir nom article");
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
				rd.forward(request, response);
				
			}
			else {
				
				articleManager.ajouterVente(nomArticle, description);
				
			}
			
			
			
		}*/
}
	


