package fr.eni.eniEncheres.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.eniEncheres.bll.UtilisateurManager;
import fr.eni.eniEncheres.bo.Utilisateur;
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
			String rue = request.getParameter("rue");
			String motDePasse = request.getParameter("motDePasse");
			String confirmation = request.getParameter("confirmation");
			String nom = request.getParameter("nom");
			String email = request.getParameter("email");
			String ville = request.getParameter("ville");
		
			
			
			
			
			
			if (utilisateurManager.loginExiste(pseudo)==1 | utilisateurManager.loginExiste(pseudo)==2 | !confirmation.equals(motDePasse) | !email.contains("@") | codepostal.length()>10 | !codepostal.matches("\\p{Digit}+")  | (!telephone.trim().matches("\\p{Digit}+") && telephone.trim().length()!=0)| telephone.trim().length()>15) {
				
				//mail
				if(!email.contains("@")) {
					//pour l'erreur
					request.setAttribute("email","email");
					
				}
				//mot de passe
				if(!confirmation.equals(motDePasse)){
					//pour l'erreur
					request.setAttribute("erreurMotDePasse","erreurMotDePasse");
					
				}
				
				//codepostal est un nombre et ï¿½ 5 chiffres
				if(!codepostal.matches("\\p{Digit}+") |codepostal.length()!=5 ){
					//pour l'erreur
					request.setAttribute("erreurCodePostal","erreurCodePostal");
					
				}
				
				//telephone
				if(!telephone.trim().matches("\\p{Digit}+") | telephone.trim().length()>15) {
					request.setAttribute("erreurTelephone","erreurTelephone");
					
				}
				
				//email déjà existant
				if(utilisateurManager.loginExiste(email)==1) {
					request.setAttribute("emailExist","emailExist");
					
				}
				
				//pseudo déjà existant
				if(utilisateurManager.loginExiste(pseudo)==2) {
					request.setAttribute("pseudo","pseudo");
					
				}
				//pour réafficher le codepostal
				request.setAttribute("codePostal",codepostal);
				request.setAttribute("telephone",telephone);
				request.setAttribute("email",email);
				request.setAttribute("pseudof",pseudo);
				request.setAttribute("nom",nom);
				request.setAttribute("prenom",prenom);
				request.setAttribute("rue",rue);
				request.setAttribute("ville",ville);
				request.setAttribute("erreur","red");
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
				rd.forward(request, response);	
			}else {
				
				try {
					
					utilisateurManager.ajouterUtilisateur(pseudo, nom, prenom,  email,telephone, rue,codepostal, ville, motDePasse);
					HttpSession session = request.getSession();
				
					
					try {
						
						
						Utilisateur utilisateur = utilisateurManager.connection(pseudo, motDePasse); 
						
						
							session.setAttribute("sessionUtilisateur", utilisateur);
							
							RequestDispatcher rd = request.getRequestDispatcher("Ventes");
							rd.forward(request, response);
						
					
						
					}catch (Exception e) {
						// TODO: handle exception
					}
					
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			
		}
		
	}

	
	
	
	
}
