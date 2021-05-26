package fr.eni.eniEncheres.dal;

import fr.eni.eniEncheres.bo.Enchere;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String INSERT_ENCHERE="INSERT INTO ENCHERES (date_enchere, montant_enchere, no_article, no_utilisateur) VALUES (?,?,?,?)";
	
	
	@Override
	public void encherir(Enchere enchere, int noArticle, int noUtilisateur) throws BusinessException {
		
	}

}
