package fr.eni.eniEncheres.dal;

import fr.eni.eniEncheres.bo.ArticleVendu;
import fr.eni.eniEncheres.bo.Retrait;

public interface VenteDAO {
	
	
	public void inserRetrait(Retrait retrait,int noUtilisateur) throws BusinessException;
	public int insert(ArticleVendu articleVendu,int noUtilisateur) throws BusinessException;

}
