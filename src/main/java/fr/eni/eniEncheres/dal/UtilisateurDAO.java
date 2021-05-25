package fr.eni.eniEncheres.dal;

import java.util.List;

import fr.eni.eniEncheres.bo.Utilisateur;

public interface UtilisateurDAO {
	public List<Utilisateur> selectAllUtilisateur () throws BusinessException; 
	public void insert(Utilisateur utilisateur) throws BusinessException;
	public Utilisateur selectByLogin(String login, String motDePasse);
	public void updateProfil(Utilisateur utilisateur, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville) throws BusinessException;
	public Utilisateur selectByPseudo (String pseudo); 
	
	//Supprimer un compte utilisateur
	public boolean delete(String email) throws BusinessException;

	
	



//	public Utilisateur selectById(int id) throws BusinessException;
	
	//public void ajouter(Utilisateur utilisateur);


}
