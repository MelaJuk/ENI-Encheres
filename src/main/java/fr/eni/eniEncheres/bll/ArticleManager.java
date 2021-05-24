package fr.eni.eniEncheres.bll;


import java.time.LocalDate;
import java.util.List;

import fr.eni.eniEncheres.bo.ArticleVendu;
import fr.eni.eniEncheres.bo.Categorie;
import fr.eni.eniEncheres.bo.Retrait;
import fr.eni.eniEncheres.dal.BusinessException;
import fr.eni.eniEncheres.dal.DAOFactory;
import fr.eni.eniEncheres.dal.VenteDAO;


public class ArticleManager {
	
		
	private VenteDAO venteDAO;
	
	
	//le constructeur permet de récupérer une instance de vente à travers le DAOFactory
		public ArticleManager() {
			this.venteDAO = DAOFactory.getVenteDAO(); 
		}
		
	// Méthode pour ajouter une vente
		
		public ArticleVendu ajouterVente(String nomArticle, String description, LocalDate dateDebutEnchere, LocalDate dateFinEnchere, int miseAPrix, Categorie categorieArticle,int noUtilisateur,Retrait retrait) throws BusinessException {
			BusinessException exception = new BusinessException();
			
			ArticleVendu articleVendu = new ArticleVendu (nomArticle, description, dateDebutEnchere, dateFinEnchere, miseAPrix, categorieArticle,retrait);
			
			
			
			if(!exception.hasErreurs())
			{
				
				//insert l'article et retourne son num�ro puis insere le lieu de retrait
				this.venteDAO.inserRetrait(retrait,this.venteDAO.insert(articleVendu,noUtilisateur));
				
				
			}
			
			if(exception.hasErreurs())
			{
				throw exception;
			}
			
			
			return articleVendu;
		}
		
		
		
		
		//pour afficher la liste des articles
		
		public List<ArticleVendu> listeArticleVendus() throws BusinessException{
			return this.venteDAO.selectAll();
		}
		
		
		
	

		
	

		
}
