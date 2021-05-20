package fr.eni.eniEncheres.dal;

import java.util.List;

import fr.eni.eniEncheres.bo.Utilisateur;

public interface UtilisateurDAO {
	public List<Utilisateur> selectAllUtilisateur () throws BusinessException; 
	public void insert(Utilisateur utilisateur) throws BusinessException;
	public Utilisateur selectByLogin(String login, String motDePasse);

	
//	public Utilisateur selectByLoginMotDePasse(String login,String motDePasse) throws BusinessException;
//	public void delete(int id) throws BusinessException;

//	public Utilisateur selectById(int id) throws BusinessException;
	
	//public void ajouter(Utilisateur utilisateur);


}
