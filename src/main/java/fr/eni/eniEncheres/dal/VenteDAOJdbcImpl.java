package fr.eni.eniEncheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.eniEncheres.bo.ArticleVendu;

public class VenteDAOJdbcImpl implements VenteDAO {
	
	private static final String INSERT_VENTE = "INSERT INTO ARTICLES_VENDUS (nomArticle, description, nomArticle, description, dateFinEncheres, dateFinEncheres, miseAprix, vendeur, categorieArticle)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


	
	

	public void insert(ArticleVendu articleVendu){
		if(articleVendu==null) {
		
			
		}
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			PreparedStatement requete = cnx.prepareStatement(INSERT_VENTE, PreparedStatement.RETURN_GENERATED_KEYS);
			requete.setString(1, articleVendu.getNomArticle());
			requete.setString(2, articleVendu.getDescription());
			//requete.setDate(3, nouvelleVente.getDateDebutEncheres());
			//requete.setDate(4, nouvelleVente.getDateFinEncheres());
			//requete.setInt(5, nouvelleVente.getMiseAprix());
			//requete.setUtilisateur(6, nouvelleVente.getVendeur());
			//requete.setCategorie(7, nouvelleVente.getCategorieArticle());
			
			requete.executeUpdate();
			ResultSet rs = requete.getGeneratedKeys();
			if(rs.next())
			{
				articleVendu.setNoArticle(rs.getInt(1));
			}
			rs.close();
			requete.close();
		}
		catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			System.err.println("erreur");
		}
		
		
		
	}



}
