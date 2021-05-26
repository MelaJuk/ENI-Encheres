package fr.eni.eniEncheres.dal;

import fr.eni.eniEncheres.bo.Enchere;

public interface EnchereDAO {

	public void encherir (Enchere enchere, int noArticle, int noUtilisateur) throws BusinessException ; 
}
