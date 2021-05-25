package fr.eni.eniEncheres.bll;

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
	
}
