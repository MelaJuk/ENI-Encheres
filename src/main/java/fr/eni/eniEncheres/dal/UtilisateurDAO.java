package fr.eni.eniEncheres.dal;


import java.util.List;

import fr.eni.eniEncheres.bo.Utilisateur;





public interface UtilisateurDAO {
	public void insert(Utilisateur utilisateur) throws BusinessException;
//	public Utilisateur selectByLoginMotDePasse(String login,String motDePasse) throws BusinessException;
//	public void delete(int id) throws BusinessException;
//	public List<Utilisateur> selectAll() throws BusinessException;
//	public Utilisateur selectById(int id) throws BusinessException;
	
}
