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
				//insérer l'enchère
				this.enchereDAO.insertEnchere(enchere, noArticle, noUtilisateur);
		
		return enchere; 
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
