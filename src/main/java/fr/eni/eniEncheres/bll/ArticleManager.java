package fr.eni.eniEncheres.bll;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import fr.eni.eniEncheres.bo.ArticleVendu;
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
		
		public ArticleVendu ajouterVente(String nomArticle, String description, LocalDateTime dateDebutEnchere, LocalDateTime dateFinEnchere, int miseAPrix) throws BusinessException {
			BusinessException exception = new BusinessException();
			
			ArticleVendu articleVendu = new ArticleVendu (nomArticle, description);
			
			this.validerNomArticle(articleVendu,exception);
			this.validerDescriptionArticle(articleVendu,exception);

			if(!exception.hasErreurs())
			{
				this.venteDAO.insert(articleVendu);
			}
			
			if(exception.hasErreurs())
			{
				throw exception;
			}
			return articleVendu;
		}
		
		
		private void validerDescriptionArticle(ArticleVendu articleVendu, BusinessException exception) {
			if(articleVendu.getDescription()==null  || articleVendu.getDescription().equals("")|| articleVendu.getDescription().length()>150)
			{
				
			}
		
	}

		private void validerNomArticle(ArticleVendu articleVendu, BusinessException exception) {
			if(articleVendu.getNomArticle()==null  || articleVendu.getNomArticle().equals("")|| articleVendu.getNomArticle().length()>50)
			{
				
			}
		
	}

		
}
