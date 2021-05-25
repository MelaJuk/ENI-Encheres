package fr.eni.eniEncheres.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

import java.time.LocalDate;


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
import fr.eni.eniEncheres.bo.Retrait;
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
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
			int miseAprix=0;
			
			
			
			//lecture utilisateur
			
			noUtilisateur =Integer.parseInt(request.getParameter("noUtilisateur")) ;
			
			
			// Lecture du nom
			nomArticle = request.getParameter("article");
			// lecture description
			if(!validerNomArticle(nomArticle)) {
				//pour l'erreur
				request.setAttribute("nomArticle","nomArticle");
			}
			description= request.getParameter("description");
			
			if(!validerDescriptionArticle(description)) {
				//pour l'erreur
				request.setAttribute("description","description");
			}
			
			
			
			
			dateDebutEnchere =LocalDate.parse(request.getParameter("debut"));
			// Lecture date fin enchère
			dateFinEnchere = LocalDate.parse(request.getParameter("fin"));
			// Lecture mise à prix 
			
			
				if(dateDebutEnchere.isAfter(dateFinEnchere)) {
					//pour l'erreur
					request.setAttribute("date","date");
				}
			
			
				miseAprix = Integer.parseInt(request.getParameter("credit"));
			
			Categorie categorie= new Categorie(request.getParameter("categories"));
			
			//retrait
			Retrait retrait = new Retrait(request.getParameter("rue"),request.getParameter("codePostal"),request.getParameter("ville"));
			
			if(!validerNomArticle(nomArticle) |!validerDescriptionArticle(description) |dateDebutEnchere.isAfter(dateFinEnchere) ) {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ajouterVente.jsp");
				rd.forward(request, response);	
				
			}else {
				//Ajouter une vente
			ArticleManager articleManager = new ArticleManager();
			try {
				System.out.println(miseAprix);
				articleManager.ajouterVente(nomArticle, description, dateDebutEnchere, dateFinEnchere, miseAprix,categorie,noUtilisateur, retrait);
				// si tout se passe bien, aller à la page de détail d'une vente
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailVente.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			catch (BusinessException e) {	
			
			
				// si cela ne fonctionne pas, on retourne à la liste des enchères
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/listeDesEncheres.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
			}
			
			
			
		}	
		
		
		private boolean validerNomArticle(String nom) {
			if(nom==null  || nom.equals("")|| nom.length()>50)
			{
				return false; 
			}
			else {
				return true;
			}
		}
		
		private boolean validerDescriptionArticle(String nom) {
			if(nom==null  || nom.equals("")|| nom.length()>150)
			{
				return false; 
			}
			else {
				return true;
			}
		}
}
				
					
				
	
			
			

	


