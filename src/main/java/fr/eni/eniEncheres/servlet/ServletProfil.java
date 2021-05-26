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
 * Servlet implementation class ServletProfil
 */
@WebServlet(urlPatterns={"/modifierProfil","/afficherProfil"})
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getServletPath().equals("/afficherProfil")) {
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			HttpSession session = request.getSession(false);

			Utilisateur utilisateur =(Utilisateur) session.getAttribute("sessionUtilisateur");
			String pseudo = utilisateur.getPseudo();
			
			
			request.setAttribute("utilisateur", utilisateurManager.afficherProfil(pseudo));
	
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/afficherProfilUtilisateur.jsp");
			rd.forward(request, response);
		}
		if (request.getServletPath().equals("/modifierProfil")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifierProfilUtilisateur.jsp");
			rd.forward(request, response);


		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/modifierProfil")) {
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String codepostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			
		if ( !email.contains("@") | codepostal.length()!=5 | !codepostal.matches("\\p{Digit}+") | !telephone.trim().matches("\\p{Digit}+") | telephone.trim().length()!=10) {
				
				//mail
				if(!email.contains("@")) {
				
					request.setAttribute("email","email");
				}
				
				
				//codepostal est un nombre et � 5 chiffres
				if(!codepostal.matches("\\p{Digit}+") |codepostal.length()!=5 ){
					
					request.setAttribute("erreurCodePostal","erreurCodePostal");
				}
				
				//telephone
				if(!telephone.trim().matches("\\p{Digit}+") | telephone.trim().length()!=10) {
					request.setAttribute("erreurTelephone","erreurCodePostal");
				}
				
				//email d�j� existant
				if(utilisateurManager.loginExiste(email)==1) {
					request.setAttribute("emailExist","emailExist");
				}
				//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
				//rd.forward(request, response);		
				
		}else {
			HttpSession session = request.getSession(false);
			
			try {
				utilisateurManager.modifierProfil((Utilisateur) session.getAttribute("sessionUtilisateur"), nom, prenom, email, telephone, rue, codepostal, ville);
				Utilisateur modifUtilisateur = (Utilisateur) session.getAttribute("sessionUtilisateur");
				modifUtilisateur.setNom(nom);
				modifUtilisateur.setPrenom(prenom);
				modifUtilisateur.setEmail(email);
				modifUtilisateur.setTelephone(telephone);
				modifUtilisateur.setRue(rue);
				modifUtilisateur.setCodePostal(codepostal);
				modifUtilisateur.setVille(ville);
				session.setAttribute("sessionUtilisateur",modifUtilisateur);
				
			} catch (BusinessException e) {
				
				e.printStackTrace();
			}
		}
			
		}
		
		doGet(request, response);
	}

} 
