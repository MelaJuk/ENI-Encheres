package fr.eni.eniEncheres.bll;

import fr.eni.eniEncheres.bo.Utilisateur;

public class UtilisateurManager {

	// ce manager va permettre de faire les traitements attendus sur la classe Utilisateur 
	
	// private UtilisateurDAO utilisateurDAO; 
	
	//le constructeur permet de récupérer une instance  d'utilisateur à travers le DAOFactory
	
	/*
	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO(); 
	}
	*/
	
	// ajouter les méthodes qui permettent de vérifier les règles à respecter sur les variables 
		// les méthodes dont va avoir besoin l'utilisateur 
		
	
	public Utilisateur ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse) {
		Utilisateur utilisateur;
		if(telephone==null) {
			utilisateur = new Utilisateur(pseudo, nom, prenom, email, rue, codePostal, ville, motDePasse);
		}else {
			utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
		}
		
		return utilisateur;
	}
	
	
}
