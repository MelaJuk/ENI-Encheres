package fr.eni.eniEncheres.bll;

import java.util.List;

import fr.eni.eniEncheres.bo.Utilisateur;
import fr.eni.eniEncheres.dal.BusinessException;
import fr.eni.eniEncheres.dal.DAOFactory;
import fr.eni.eniEncheres.dal.UtilisateurDAO;

public class UtilisateurManager {

	// ce manager va permettre de faire les traitements attendus sur la classe Utilisateur 
	
	private UtilisateurDAO utilisateurDAO; 
	
	//le constructeur permet de récupérer une instance  d'utilisateur à travers le DAOFactory
	
	
	public UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO(); 
	}
	
	
	
	// ajouter les méthodes qui permettent de vérifier les règles à respecter sur les variables 
		// les méthodes dont va avoir besoin l'utilisateur 
		
	
	public Utilisateur ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse) throws BusinessException {
		Utilisateur utilisateur;
		
		if(telephone.length()==0) {
			utilisateur = new Utilisateur(pseudo, nom, prenom, email, rue, codePostal, ville, motDePasse);
		}else {
			
			utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
			
		}
		this.utilisateurDAO.insert(utilisateur);
		return utilisateur;
	}



	public Utilisateur connection(String login, String motDePasse) {
		return this.utilisateurDAO.selectByLogin(login, motDePasse);
	}
	
	
	public List<Utilisateur> listeUtilisateur() throws BusinessException {

			return this.utilisateurDAO.selectAllUtilisateur();

	}
	
	
//	test si un utilisateur existe
//	retourne 0 : l'utilisateur n'existe pas
//	retourne 1 : un utilisateur � le m�me email
//	retourne 2 : un utilisateur � le m�me pseudo
	public int loginExiste(String login){
		List<Utilisateur> listeUtililsateur;
		try {
			listeUtililsateur = this.listeUtilisateur();
			
		} catch (BusinessException e) {
			System.err.println("erreur");
			return 0;
		}
		for(Utilisateur utilisateur : listeUtililsateur) {
			if(utilisateur.getEmail().trim().equals(login)) {
				return 1;
			}
			if(utilisateur.getPseudo().trim().equals(login)) {
				return 2;
			}
		}
		return 0;
		
	}
	
//	//teste si le mot de passe et le login correspond � un utilisateur
	public boolean utilisateurExiste(String login, String motDePasse){
		List<Utilisateur> listeUtililsateur;
		try {
			listeUtililsateur = this.listeUtilisateur();
		} catch (BusinessException e) {
			return false;
		}
		for(Utilisateur utilisateur : listeUtililsateur) {
			if ((utilisateur.getEmail().trim().equals(login) && utilisateur.getMotDePasse().trim().equals(motDePasse) ) || (utilisateur.getPseudo().trim().equals(login) && utilisateur.getMotDePasse().trim().equals(motDePasse) ) ) {
				return true;
			}
		}
		return false;
	}
	
	
	//modifier un utilisateur
	public void modifierProfil(Utilisateur utilisateur,String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville) throws BusinessException {
		 this.utilisateurDAO.updateProfil(utilisateur, nom, prenom, email, telephone, rue, codePostal, ville);
		
	}
	
	//afficher un utilisateur 
	public Utilisateur afficherProfil (String pseudo) {
		return this.utilisateurDAO.selectByPseudo(pseudo); 
	}
	
}
