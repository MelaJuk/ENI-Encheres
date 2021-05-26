package fr.eni.eniEncheres.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniEncheres.bll.ArticleManager;
import fr.eni.eniEncheres.bo.ArticleVendu;
import fr.eni.eniEncheres.dal.BusinessException;

/**
 * Servlet implementation class Ventes
 */
@WebServlet("/Ventes")
public class Ventes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleManager articleManager = new ArticleManager();
		List<ArticleVendu> listArticles = null;
		String categorie =request.getParameter("categories");
		String nom = request.getParameter("nom");
		String encheres = request.getParameter("encheres");
		
		if(encheres == "eouvertes") {
			try {
				listArticles = articleManager.listeArticleEnchereParCategorie(request.getParameter("categories"),request.getParameter("nom"));
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(categorie == null) {
			try {
				
				listArticles = articleManager.listeArticleVendus();
				request.setAttribute("listeArticles", listArticles);
				RequestDispatcher rd = request.getRequestDispatcher("accueil.jsp");
				
				rd.forward(request, response);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
			if(request.getParameter("categories").equals("toute")) {
				listArticles = articleManager.listeArticleVendusParNom(request.getParameter("nom"));
			}else {
				listArticles =  articleManager.listeArticleVendusParCategorieParNom(request.getParameter("categories"),request.getParameter("nom"));
			}
			
			request.setAttribute("listeArticles", listArticles);
			RequestDispatcher rd = request.getRequestDispatcher("accueil.jsp");
			rd.forward(request, response);
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
		
		doGet(request, response);
	}

}
