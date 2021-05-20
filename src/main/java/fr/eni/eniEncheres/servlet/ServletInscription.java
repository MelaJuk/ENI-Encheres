package fr.eni.eniEncheres.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniEncheres.bll.UtilisateurManager;
import fr.eni.eniEncheres.dal.BusinessException;


/**
 * Servlet implementation class ServletInscription
 */
@WebServlet(urlPatterns={"/creer","/annuler"})
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/creer")) {
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			String pseudo = request.getParameter("pseudo");
			String prenom = request.getParameter("prenom");
			String telephone = request.getParameter("telephone");
			String codepostal = request.getParameter("codePostal");
			String motDePasse = request.getParameter("motDePasse");
			String confirmation = request.getParameter("confirmation");
			String nom = request.getParameter("nom");
			String email = request.getParameter("email");
			String ville = request.getParameter("ville");
			PrintWriter out = response.getWriter();
			//test si l'email est valide
			
			System.out.println(utilisateurManager.loginExiste(pseudo));
			
			
			if (utilisateurManager.loginExiste(pseudo)==1 | utilisateurManager.loginExiste(pseudo)==2 | !confirmation.equals(motDePasse) | !email.contains("@") | codepostal.length()!=5 | !codepostal.matches("\\p{Digit}+") | !telephone.trim().matches("\\p{Digit}+") | telephone.trim().length()!=10) {
				
				//mail
				if(!email.contains("@")) {
				
					request.setAttribute("email","email");
				}
				//mot de passe
				if(!confirmation.equals(motDePasse)){
					out.println("Les mots de passes sont diffï¿½rents");
					request.setAttribute("erreurMotDePasse","erreurMotDePasse");
				}
				
				//codepostal est un nombre et ï¿½ 5 chiffres
				if(!codepostal.matches("\\p{Digit}+") |codepostal.length()!=5 ){
					
					request.setAttribute("erreurCodePostal","erreurCodePostal");
				}
				
				//telephone
				if(!telephone.trim().matches("\\p{Digit}+") | telephone.trim().length()!=10) {
					request.setAttribute("erreurTelephone","erreurCodePostal");
				}
				
				//email déjà existant
				if(utilisateurManager.loginExiste(pseudo)==1) {
					request.setAttribute("emailExist","emailExist");
				}
				
				//pseudo déjà existant
				if(utilisateurManager.loginExiste(pseudo)==2) {
					request.setAttribute("pseudo","pseudo");
				}
				
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
				rd.forward(request, response);	
			}else {
				
				try {
					
					utilisateurManager.ajouterUtilisateur(pseudo, nom, prenom, email, telephone, email, codepostal, ville, motDePasse);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			
		}
		
	}

	
	
	
	
}
