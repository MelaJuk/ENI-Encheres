package fr.eni.eniEncheres.dal;

import java.util.List;

import fr.eni.eniEncheres.bo.ArticleVendu;

public interface EnchereDAO {

	
	//public List<ArticleVendu> selectALLEnchereOuvertes(String libelle) throws BusinessException;
		//public List<ArticleVendu> selectALLEnchereOuvertes(String libelle, String contient) throws BusinessException;
		public List<ArticleVendu> selectALLEnchereByAcheteur(String libelle, String contient, int noUtilisateur) throws BusinessException;

		public List<ArticleVendu> selectALLEnchereOuvertes(String libelle, String contient)
				throws BusinessException;

}
