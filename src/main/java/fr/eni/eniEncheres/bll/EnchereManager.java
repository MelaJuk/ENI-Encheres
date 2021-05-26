package fr.eni.eniEncheres.bll;

import java.util.List;

import fr.eni.eniEncheres.bo.ArticleVendu;
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
