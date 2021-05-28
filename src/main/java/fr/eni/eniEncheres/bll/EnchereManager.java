package fr.eni.eniEncheres.bll;

import java.util.List;

import fr.eni.eniEncheres.bo.ArticleVendu;
import java.time.LocalDate;

import fr.eni.eniEncheres.bo.Enchere;
import fr.eni.eniEncheres.bo.Utilisateur;
import fr.eni.eniEncheres.dal.BusinessException;
import fr.eni.eniEncheres.dal.DAOFactory;
import fr.eni.eniEncheres.dal.EnchereDAO;

public class EnchereManager {

	private EnchereDAO enchereDAO; 
	
	// constructeur 
	public EnchereManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	//ajouter enchère : (vérif validité enchère ici ou dans la servlet ?)
	
	public Enchere encherir (LocalDate dateEnchere, int montant_enchere, int noArticle, int noUtilisateur)throws BusinessException {
		BusinessException exception = new BusinessException(); 
		Enchere enchere = new Enchere(dateEnchere, montant_enchere); 
		
			// ajouter l'enchère en ajoutant les numéros 
		
				//vérification des conditions d'enchère
				//validerEnchere(enchere); // mettre dans un if pour traiter une éventuelle exception ? 
				//test 
				System.out.println("salut");
				//insérer l'enchère
				this.enchereDAO.insertEnchere(enchere, noArticle, noUtilisateur);
				//débiter l'enchirisseur 
				
				// re créditer enchérisseur précédent 
			
		
		return enchere; 
	}

	public void validerEnchere(Enchere enchere) {
		// enchère proposé > enchère actuelle
		// &&
		// crédit > enchère proposée
		
	}
	
	public void debitAcheteur () {
		// si enchère validée acheteur débité 
		// crédit acheteur - montant enchère proposée
	}
	
	public void creditEncherisseur () {
		// si enchère validée enchérisseur précédent est recrédité
		// crédit enchérisseur précédent + montant enchère précédente 
	}
	
	public void modifierCreditUtilisateur (int credit,int noUtilisateur) throws BusinessException {
		this.enchereDAO.updateCreditUtilisateur(credit,noUtilisateur);
	}
	
	public void modifierPrixVente(int prixVente, int noArticle) throws BusinessException {
		this.enchereDAO.updateArticlePrixVente(prixVente, noArticle);
	}
	
	// afficher la liste des enchères
	//enchere en cours et par categorie d'un utilisateur
	
			public List<ArticleVendu> listeArticleEnchereParAcheteur(String libelle,String contient,int noUtilisateur) throws BusinessException{
				return this.enchereDAO.selectALLEnchereByAcheteur(libelle, contient,noUtilisateur);
			}

			// afficher la liste des enchères
			//enchere en cours et par categorie
			public List<ArticleVendu> listeArticleEnchereOuverte(String libelle,String contient) throws BusinessException{
				return this.enchereDAO.selectALLEnchereOuvertes(libelle, contient);
			}
}
