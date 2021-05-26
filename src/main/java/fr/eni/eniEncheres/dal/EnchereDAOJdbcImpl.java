package fr.eni.eniEncheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eniEncheres.bo.ArticleVendu;
import fr.eni.eniEncheres.bo.Enchere;
import fr.eni.eniEncheres.bo.Utilisateur;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	
	
	private static final String SELECT_MES_ENCHERE_ENCOURS="SELECT nom_article , prix_initial,date_fin_encheres,pseudo,ar.no_article,ISNULL(e.montant_enchere,0), date_debut_encheres AS montant_enchere FROM ARTICLES_VENDUS ar\r\n"
			+ "			LEFT JOIN UTILISATEURS u ON u.no_utilisateur=ar.no_utilisateur\r\n"
			+ "			LEFT JOIN ENCHERES e ON e.no_article=ar.no_article  LEFT JOIN CATEGORIES c ON c.no_categorie=ar.no_categorie \r\n"
			+ "			WHERE libelle LIKE ? AND nom_article LIKE ? nom_article AND DATEDIFF(day,date_debut_encheres,GETDATE())>=0 AND DATEDIFF(day,date_fin_encheres,GETDATE())<=0 AND e.no_utilisateur=? \r\n"
			+ "			ORDER BY date_fin_encheres";
	
	private static final String SELECT_ALL_ENCHERE_OUVERTES="SELECT nom_article , prix_initial,date_fin_encheres,pseudo,ar.no_article,ISNULL(e.montant_enchere,0) AS montant, date_debut_encheres AS montant_enchere FROM ARTICLES_VENDUS ar \r\n"
			+ "						LEFT JOIN UTILISATEURS u ON u.no_utilisateur=ar.no_utilisateur \r\n"
			+ "						LEFT JOIN ENCHERES e ON e.no_article=ar.no_article  LEFT JOIN CATEGORIES c ON c.no_categorie=ar.no_categorie \r\n"
			+ "						WHERE libelle LIKE ? AND nom_article LIKE ? AND DATEDIFF(day,date_debut_encheres,GETDATE())>=0 AND DATEDIFF(day,date_fin_encheres,GETDATE())<=0 \r\n"
			+ "						ORDER BY date_fin_encheres";
	
	
	
	@Override
	public List<ArticleVendu> selectALLEnchereByAcheteur(String libelle, String contient, int noUtilisateur)
			throws BusinessException{
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>(); 
		Connection connexion = null;
		PreparedStatement requete = null;
		ResultSet resultat = null;
		
		try {
			
			connexion = ConnectionProvider.getConnection();
			requete = connexion.prepareStatement(SELECT_MES_ENCHERE_ENCOURS);
			
			if (contient==null) {
				requete.setString(2,"%");
			}else {
				requete.setString(2,contient);
			}
			
			
			if (libelle=="toute"  || libelle==null ) {
				requete.setString(1,"%");
			}else {
				requete.setString(1,libelle);
			}
			requete.setInt(3,noUtilisateur);
			resultat = requete.executeQuery();
			
			while (resultat.next()) {
				ArticleVendu article = new ArticleVendu(); 
				article.setNomArticle(resultat.getString("nom_article"));
				article.setPrixVente(resultat.getInt("prix_initial"));
				LocalDate localDate =resultat.getDate("date_fin_encheres").toLocalDate();
				
				Utilisateur vendeur = new Utilisateur();
				vendeur.setPseudo(resultat.getString("pseudo"));
				article.setVendeur(vendeur);
				article.setDateFinEncheres(localDate );
				
				//si l'ench�re existe 
				if( resultat.getInt("montant_enchere")!=0) {
						Enchere enchere=new Enchere(resultat.getInt("montant_enchere"),article);
						//ajoute l'enchere � l'article
				article.getListeEncheresArticle().add(enchere);
				}
			
				
				listeArticles.add(article); 
			}
				//rs.close();
				//stmt.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

			
			return listeArticles;
			
			
	}
	

	@Override
	public List<ArticleVendu> selectALLEnchereOuvertes(String libelle, String contient)
			throws BusinessException{
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>(); 
		Connection connexion = null;
		PreparedStatement requete = null;
		ResultSet resultat = null;
		
		try {
			
			connexion = ConnectionProvider.getConnection();
			requete = connexion.prepareStatement(SELECT_ALL_ENCHERE_OUVERTES);
			
			if(contient==null) {
				requete.setString(2,"%");
			}else {
				requete.setString(2,contient+"%");
			}
			
			
			if (libelle=="toute" || libelle==null) {
				requete.setString(1,"%");
			}else {
				requete.setString(1,libelle);
			}
			resultat = requete.executeQuery();
			
			while (resultat.next()) {
				ArticleVendu article = new ArticleVendu(); 
				article.setNomArticle(resultat.getString("nom_article"));
				article.setPrixVente(resultat.getInt("prix_initial"));
				LocalDate localDate =resultat.getDate("date_fin_encheres").toLocalDate();
				
				Utilisateur vendeur = new Utilisateur();
				vendeur.setPseudo(resultat.getString("pseudo"));
				article.setVendeur(vendeur);
				article.setDateFinEncheres(localDate );
				System.out.println(resultat.getInt("montant"));
				//si l'ench�re existe 
				if( resultat.getInt("montant")!=0) {
						Enchere enchere=new Enchere(resultat.getInt("montant"),article);
						//ajoute l'enchere � l'article
				article.getListeEncheresArticle().add(enchere);
				}
			
				
				listeArticles.add(article); 
			}
				//rs.close();
				//stmt.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

			
			return listeArticles;
			
			
	}
	
}
