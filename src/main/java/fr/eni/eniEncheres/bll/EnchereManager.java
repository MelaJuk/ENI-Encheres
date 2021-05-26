package fr.eni.eniEncheres.bll;

import java.time.LocalDate;

import fr.eni.eniEncheres.bo.Enchere;
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
		Enchere enchere = new Enchere(montant_enchere); 
		
		if (!exception.hasErreurs()) {
			// ajouter l'enchère en ajoutant les numéros 
			if (enchere.getNoEnchere()!=0) {
				//vérification des conditions d'enchère
				validerEnchere(enchere); 
				//insérer l'enchère
				this.enchereDAO.insertEnchere(enchere, noArticle, noUtilisateur);
			}
			
		} else {
			throw exception; 
		}
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
	
	public void creditVendeur () {
		// si enchère terminée le crédit du vendeur est crédité du montant de l'enchère
	}
	
	
	// afficher la liste des enchères
	
}
