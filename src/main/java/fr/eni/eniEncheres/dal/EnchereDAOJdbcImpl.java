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

	
	private static final String INSERT_ENCHERE="INSERT INTO ENCHERES (date_enchere, montant_enchere, no_article, no_utilisateur) VALUES (?,?,?,?)";
	private static final String SELECT_MES_ENCHERE_ENCOURS="SELECT nom_article , prix_initial,date_fin_encheres,pseudo,ar.no_article as noArticle,MAX(ISNULL(e.montant_enchere,0)) AS montant, date_debut_encheres  FROM ARTICLES_VENDUS ar \r\n"
			+ "						LEFT JOIN UTILISATEURS u ON u.no_utilisateur=ar.no_utilisateur \r\n"
			+ "						LEFT JOIN ENCHERES e ON e.no_article=ar.no_article  LEFT JOIN CATEGORIES c ON c.no_categorie=ar.no_categorie \r\n"
			+ "						WHERE libelle LIKE ? AND nom_article LIKE ? AND e.no_utilisateur=? AND DATEDIFF(day,date_debut_encheres,GETDATE())>=0 AND DATEDIFF(day,date_fin_encheres,GETDATE())<=0 \r\n"
			+"                      GROUP BY ar.no_article,nom_article,prix_initial,date_fin_encheres,pseudo,date_debut_encheres "
			+ "						ORDER BY date_fin_encheres";
	
	private static final String SELECT_ALL_ENCHERE_OUVERTES="SELECT nom_article , prix_initial,date_fin_encheres,pseudo,ar.no_article as noArticle,MAX(ISNULL(e.montant_enchere,0)) AS montant, date_debut_encheres  FROM ARTICLES_VENDUS ar \r\n"
			+ "						LEFT JOIN UTILISATEURS u ON u.no_utilisateur=ar.no_utilisateur \r\n"
			+ "						LEFT JOIN ENCHERES e ON e.no_article=ar.no_article  LEFT JOIN CATEGORIES c ON c.no_categorie=ar.no_categorie \r\n"
			+ "						WHERE libelle LIKE ? AND nom_article LIKE ? AND DATEDIFF(day,date_debut_encheres,GETDATE())>=0 AND DATEDIFF(day,date_fin_encheres,GETDATE())<=0 \r\n"
			+"                      GROUP BY ar.no_article,nom_article,prix_initial,date_fin_encheres,pseudo,date_debut_encheres "
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
				requete.setString(2,contient+"%");
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
				article.setNoArticle(resultat.getInt("noArticle"));
				LocalDate localDate =resultat.getDate("date_fin_encheres").toLocalDate();
				
				Utilisateur vendeur = new Utilisateur();
				vendeur.setPseudo(resultat.getString("pseudo"));
				article.setVendeur(vendeur);
				article.setDateFinEncheres(localDate );
				
				if(article.getListeEncheresArticle()==null) {
					List<Enchere> listeEnchere = new ArrayList<Enchere>();
					article.setListeEncheresArticle(listeEnchere);
					
				}
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
				article.setNoArticle(resultat.getInt("noArticle"));
				LocalDate localDate =resultat.getDate("date_fin_encheres").toLocalDate();
				
				Utilisateur vendeur = new Utilisateur();
				vendeur.setPseudo(resultat.getString("pseudo"));
				article.setVendeur(vendeur);
				article.setDateFinEncheres(localDate );
				
				if(article.getListeEncheresArticle()==null) {
					List<Enchere> listeEnchere = new ArrayList<Enchere>();
					article.setListeEncheresArticle(listeEnchere);
					
				}
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

	
	// insérer une nouvelle enchère 
	@Override
	public void insertEnchere(Enchere enchere, int noArticle, int noUtilisateur) throws BusinessException {
		int noEnchere = 0; 
		
		if (enchere ==null) {
			BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(INSERT_ENCHERE, PreparedStatement.RETURN_GENERATED_KEYS); 
			stmt.setDate(1, java.sql.Date.valueOf(enchere.getDateEnchere()));
			stmt.setInt(2, enchere.getMontant_enchere());
			stmt.setInt(3, noArticle);
			stmt.setInt(4, noUtilisateur);
			
			stmt.executeUpdate(); 
			ResultSet rs = stmt.getGeneratedKeys(); 
			
			if(rs.next()) {
				enchere.setNoEnchere(rs.getInt(1));
				noEnchere=rs.getInt(1); 
			}
 			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	
	}

	
	
	
	
}
