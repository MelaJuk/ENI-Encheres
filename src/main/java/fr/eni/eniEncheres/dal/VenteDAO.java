package fr.eni.eniEncheres.dal;

import java.util.List;

import fr.eni.eniEncheres.bo.ArticleVendu;
import fr.eni.eniEncheres.bo.Retrait;

public interface VenteDAO {
	
	
	public void inserRetrait(Retrait retrait,int noUtilisateur) throws BusinessException;
	public int insert(ArticleVendu articleVendu,int noUtilisateur) throws BusinessException;
	public List<ArticleVendu> selectAll() throws BusinessException;
	public List<ArticleVendu> selectByCategorieNom(String categorie,String contient) throws BusinessException;
	public List<ArticleVendu> selectByNom(String contient) throws BusinessException;
	
	public ArticleVendu selectByNoArticle(int noArticle);
	
	


}
