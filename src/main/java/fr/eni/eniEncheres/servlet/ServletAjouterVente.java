package fr.eni.eniEncheres.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;
import fr.eni.eniEncheres.bll.ArticleManager;
import fr.eni.eniEncheres.bll.UtilisateurManager;
import fr.eni.eniEncheres.bo.ArticleVendu;
import fr.eni.eniEncheres.bo.Categorie;
import fr.eni.eniEncheres.bo.Utilisateur;
import fr.eni.eniEncheres.dal.BusinessException;



/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet(urlPatterns={"/ServletAjouterVente","/ServletAnnulerVente"})

public class ServletAjouterVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ajouterVente.jsp");
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
			
			// lire les paramètres
			String nomArticle=null;
			String description=null;
			LocalDate dateDebutEnchere=null;
			LocalDate dateFinEnchere=null;
			int noUtilisateur;
			System.out.println(request.getParameter("noUtilisateur"));
			//lecture utilisateur
			System.out.println(request.getParameter("noUtilisateur"));
			noUtilisateur =Integer.parseInt(request.getParameter("noUtilisateur")) ;
			
			
			// Lecture du nom
			nomArticle = request.getParameter("article");
			// lecture description
			description= request.getParameter("description");
			// Lecture date début enchère
			
			dateDebutEnchere = LocalDate.parse(request.getParameter("debut"));
			// Lecture date fin enchère
			dateFinEnchere = LocalDate.parse(request.getParameter("fin"));
			// Lecture mise à prix 
			
			int miseAPrix = Integer.parseInt(request.getParameter("credit"));
			Categorie categorie= new Categorie(request.getParameter("categories"));
			
			
			//Ajouter une vente
			ArticleManager articleManager = new ArticleManager();
			try {
				articleManager.ajouterVente(nomArticle, description, dateDebutEnchere, dateFinEnchere, miseAPrix,categorie,noUtilisateur);
				// si tout se passe bien, aller à la page de détail d'une vente
				RequestDispatcher rd = request.getRequestDispatcher("/detailVente");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			catch (BusinessException e) {	
			
			
				// si cela ne fonctionne pas, on retourne à la liste des enchères
				RequestDispatcher rd = request.getRequestDispatcher("/listeDesEncheres");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
			
		}		
}
				
					
				
	
			
			

	


