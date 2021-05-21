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

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet(urlPatterns={"/ServletConnexion","/ServletDeconnexion"})
public class ServletConnexion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	 	public static final String LOGIN        = "login";
	    public static final String MOT_DE_PASSE         = "motDePasse";
	    public static final String ATT_USER = "utilisateur";
	    public static final String ATT_SESSION_USER = "sessionUtilisateur";
	    public static final String VUE              = "/WEB-INF/connexion.jsp";
	    public static final String VUE_ECHEC              = "/WEB-INF/erreurConnexion.jsp";
	    public static final String VUE_USER = "/WEB-INF/listeDesEncheres.jsp";
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().equals("/ServletDeconnexion")) {
			/* Récupération et destruction de la session en cours */
			 HttpSession session = request.getSession();
		     session.invalidate();   
		     RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.forward(request, response);
		}
		RequestDispatcher rd = request.getRequestDispatcher(VUE);
		rd.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//crï¿½ation ou rï¿½cupï¿½ration de la session
		HttpSession session = request.getSession();
		String login;
		String motDePasse;
		
		try {
			login = request.getParameter(LOGIN);
			motDePasse = request.getParameter(MOT_DE_PASSE);
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			Utilisateur utilisateur = utilisateurManager.connection(login, motDePasse); 
			
			if (!utilisateurManager.utilisateurExiste(login,motDePasse)) {
				
				PrintWriter out = response.getWriter();
				out.println("Le login ou le mot de passe n'est pas correcte");
				session.setAttribute(ATT_SESSION_USER, null);
				request.setAttribute("erreur", "erreur");
				RequestDispatcher rd = request.getRequestDispatcher(VUE);
				rd.forward(request, response);
			} else {
				session.setAttribute(ATT_SESSION_USER, utilisateur);
				RequestDispatcher rd = request.getRequestDispatcher(VUE_USER);
				rd.forward(request, response);
			}
		
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
	}

}
