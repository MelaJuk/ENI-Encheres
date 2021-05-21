package fr.eni.eniEncheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.eclipse.jdt.internal.compiler.classfmt.JavaBinaryNames;

import fr.eni.eniEncheres.bo.ArticleVendu;

public class VenteDAOJdbcImpl implements VenteDAO {
	
	private static final String INSERT_VENTE = "INSERT INTO ARTICLES_VENDUS (nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAprix, vendeur, categorieArticle)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";


	
	
	@Override
	public void insert(ArticleVendu articleVendu)throws BusinessException{
		if(articleVendu==null) {
			BusinessException businessException = new BusinessException();
			throw businessException;
		}
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			cnx.setAutoCommit(false);
			PreparedStatement requete = cnx.prepareStatement(INSERT_VENTE, PreparedStatement.RETURN_GENERATED_KEYS);
			requete.setString(1, articleVendu.getNomArticle());
			requete.setString(2, articleVendu.getDescription());
			requete.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
			requete.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));	
			requete.setInt(5, articleVendu.getMiseAprix());
			requete.setString(7, articleVendu.getCategorieArticle().getLibelle());
			requete.setInt(6, articleVendu.getVendeur().getNoUtilisateur());
			
			
			
			
			
			
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
