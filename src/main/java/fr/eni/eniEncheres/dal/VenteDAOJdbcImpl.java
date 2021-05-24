package fr.eni.eniEncheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.jdt.internal.compiler.classfmt.JavaBinaryNames;

import fr.eni.eniEncheres.bo.ArticleVendu;
import fr.eni.eniEncheres.bo.Categorie;
import fr.eni.eniEncheres.bo.Retrait;

public class VenteDAOJdbcImpl implements VenteDAO {
	
	private static final String INSERT_RETRAIT="INSERT INTO RETRAITS (no_article,rue,code_postal,ville) VALUES (?,?,?,?)";
	private static final String INSERT_VENTE = "INSERT INTO ARTICLES_VENDUS (nom_article,description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_BY_LIBELLE_CATEGORIE = "SELECT (no_categorie) FROM CATEGORIES WHERE libelle=?";

	
	
	@Override
	public void inserRetrait(Retrait retrait,int noUtilisateur) throws BusinessException {
			if(retrait==null) {
				BusinessException businessException = new BusinessException();
				throw businessException;
			}
			
			try (Connection cnx = ConnectionProvider.getConnection()){
			
			//cnx.setAutoCommit(false);
			PreparedStatement requete = cnx.prepareStatement(INSERT_RETRAIT);
			requete.setInt(1,noUtilisateur);
			requete.setString(2,retrait.getRue());
			requete.setString(3, retrait.getCode_postal());
			requete.setString(4, retrait.getVille());
			requete.executeUpdate();
			//rs.close();
			//requete.close();
		}
		catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			System.err.println("erreur");
		}
			
	}
	
	
	
	@Override
	public int insert(ArticleVendu articleVendu,int noUtilisateur)throws BusinessException{
		int noArticle = 0;
		if(articleVendu==null) {
			BusinessException businessException = new BusinessException();
			throw businessException;
		}
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			
			//cnx.setAutoCommit(false);
			PreparedStatement requete = cnx.prepareStatement(INSERT_VENTE, PreparedStatement.RETURN_GENERATED_KEYS);
			requete.setString(1, articleVendu.getNomArticle());
			requete.setString(2, articleVendu.getDescription());
			
			requete.setDate(3, java.sql.Date.valueOf(articleVendu.getDateDebutEncheres()));
			requete.setDate(4, java.sql.Date.valueOf(articleVendu.getDateFinEncheres()));	
			requete.setInt(5, articleVendu.getMiseAprix());
			requete.setInt(6, noUtilisateur);
			articleVendu.getCategorieArticle().setNoCategorie(noCategorie(articleVendu.getCategorieArticle().getLibelle()));
			
			requete.setInt(7, articleVendu.getCategorieArticle().getNoCategorie());
			
			
	
			
			requete.executeUpdate();
			ResultSet rs = requete.getGeneratedKeys();
			
			if(rs.next())
			{
				articleVendu.setNoArticle(rs.getInt(1));
				noArticle=rs.getInt(1);
			}
			
			//rs.close();
			//requete.close();
		}
		catch(Exception e){
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			System.err.println("erreur");
		}
		
		return noArticle;
		
	}

	//retourne le num�ro de la cat�gorie
	public int noCategorie(String libelle) {
		int noCategorie = 0;
		Connection connexion = null;
		PreparedStatement requete = null;
		ResultSet resultat = null;
		
		try {
			
			connexion = ConnectionProvider.getConnection();
			
			
			requete = connexion.prepareStatement(SELECT_BY_LIBELLE_CATEGORIE);
			
			requete.setString(1,libelle.toLowerCase());
			
			resultat = requete.executeQuery();
			
			
			while(resultat.next()) {
				noCategorie=resultat.getInt(1);
			}
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		return noCategorie;
	}

}
