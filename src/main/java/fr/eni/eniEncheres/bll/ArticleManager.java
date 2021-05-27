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
		
		//par categorie et par nom
		public List<ArticleVendu> listeArticleVendusParCategorieParNom(String categorie,String contient) throws BusinessException{
			return this.venteDAO.selectByCategorieNom(categorie,contient);
		}
		// afficher une vente pour afficher :  nom article, description, cat, meilleure offre, mise à prix, fin enchère, retrait, vendeur)
			// pour le vendeur : récupération du nom du vendeur et lien vers son profil (/afficherProfil et afficherProfilUtilisateur.jsp)
		// return une vente en particulier : donc faire méthode selectByNoArticle 
		
		public ArticleVendu afficherArticle (int noArticle) {
			
			return this.venteDAO.selectByNoArticle(noArticle); 
		}
		
		
	
		//par nom (commence par )
		public List<ArticleVendu> listeArticleVendusParNom(String contient) throws BusinessException{
			return this.venteDAO.selectByNom(contient);
		}
		
		
		//par categorie et par nom par vendeur
				public List<ArticleVendu> listeArticleVendusParCategorieParNomParPseudo(String categorie,String contient,int noUtililsateur) throws BusinessException{
					return this.venteDAO.selectALLVentesByUtilisateur(categorie, contient, noUtililsateur);
				}
				
				
				//par categorie et par nom par vendeur non commenc�
				public List<ArticleVendu> listeArticleVendusParCategorieParNomParPseudoNonDebutees(String categorie,String contient,int noUtililsateur) throws BusinessException{
					return this.venteDAO.selectALLVentesByUtilisateurNonDebutees(categorie, contient, noUtililsateur);
				}	
				
				
				//par categorie et par nom par vendeur terminee
				public List<ArticleVendu> listeArticleVendusParCategorieParNomParPseudoTerminees(String categorie,String contient,int noUtililsateur) throws BusinessException{
					return this.venteDAO.selectALLVentesByUtilisateurTerminees(categorie, contient, noUtililsateur);
				}			
}
