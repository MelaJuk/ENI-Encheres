package fr.eni.eniEncheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eniEncheres.bo.ArticleVendu;
import fr.eni.eniEncheres.bo.Categorie;
import fr.eni.eniEncheres.bo.Enchere;
import fr.eni.eniEncheres.bo.Retrait;
import fr.eni.eniEncheres.bo.Utilisateur;

public class VenteDAOJdbcImpl implements VenteDAO {
	
	private static final String INSERT_RETRAIT="INSERT INTO RETRAITS (no_article,rue,code_postal,ville) VALUES (?,?,?,?)";
	private static final String INSERT_VENTE = "INSERT INTO ARTICLES_VENDUS (nom_article,description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_BY_LIBELLE_CATEGORIE = "SELECT no_categorie FROM CATEGORIES WHERE libelle=?";
	private static final String SELECT_ALL_ARTICLE = "SELECT nom_article , prix_initial,date_fin_encheres,pseudo,ar.no_article,ISNULL(e.montant_enchere,0) AS montant_enchere FROM ARTICLES_VENDUS ar\r\n"
			+ "			LEFT JOIN UTILISATEURS u ON u.no_utilisateur=ar.no_utilisateur\r\n"
			+ "			LEFT JOIN ENCHERES e ON e.no_article=ar.no_article ORDER BY date_fin_encheres";
	
	private static final String SELECT_ALL_ARTICLE_NOM = "SELECT nom_article , prix_initial,date_fin_encheres,pseudo,ar.no_article,ISNULL(e.montant_enchere,0) AS montant_enchere FROM ARTICLES_VENDUS ar\r\n"
			+ "			LEFT JOIN UTILISATEURS u ON u.no_utilisateur=ar.no_utilisateur\r\n"
			+ "			LEFT JOIN ENCHERES e ON e.no_article=ar.no_article WHERE nom_article LIKE  ?  ORDER BY date_fin_encheres";
	
	private static final String SELECT_BY_CATEGORIE_ARTICLE = "SELECT nom_article , prix_initial,date_fin_encheres,pseudo,ar.no_article,ISNULL(e.montant_enchere,0) AS montant_enchere FROM ARTICLES_VENDUS ar\r\n"
			+ "			LEFT JOIN UTILISATEURS u ON u.no_utilisateur=ar.no_utilisateur\r\n"
			+ "			LEFT JOIN ENCHERES e ON e.no_article=ar.no_article  LEFT JOIN CATEGORIES c ON c.no_categorie=ar.no_categorie WHERE libelle=? AND nom_article LIKE  ? ORDER BY date_fin_encheres";
	
	
	/*private static final String SELECT_BY_NO_ARTICLE ="SELECT av.nom_article, av.description, c.libelle, e.montant_enchere, av.prix_initial, \r\n"
			+ "av.date_fin_encheres, r.rue, r.code_postal,r.ville, u.pseudo FROM ARTICLES_VENDUS av \r\n"
			+ "JOIN CATEGORIES c ON c.no_categorie = av.no_categorie \r\n"
			+ "JOIN RETRAITS r ON r.no_article = av.no_article\r\n"
			+ "JOIN UTILISATEURS u ON u.no_utilisateur = av.no_utilisateur \r\n"
			+ "JOIN ENCHERES e ON e.no_utilisateur = u.no_utilisateur\r\n"
			+ "WHERE av.no_article=?" ;*/
	private static final String SELECT_BY_NO_ARTICLE ="SELECT av.nom_article, av.description, c.libelle, av.prix_initial, \r\n"
			+ "av.date_fin_encheres, r.rue, r.code_postal,r.ville, u.pseudo FROM ARTICLES_VENDUS av \r\n"
			+ "JOIN CATEGORIES c ON c.no_categorie = av.no_categorie \r\n"
			+ "JOIN RETRAITS r ON r.no_article = av.no_article\r\n"
			+ "JOIN UTILISATEURS u ON u.no_utilisateur = av.no_utilisateur \r\n"
			+ "WHERE av.no_article=?" ;
	
	
	
	@Override
	public void inserRetrait(Retrait retrait,int noArticle) throws BusinessException {
			if(retrait==null) {
				BusinessException businessException = new BusinessException();
				throw businessException;
			}
			
			try (Connection cnx = ConnectionProvider.getConnection()){
			
			//cnx.setAutoCommit(false);
			PreparedStatement requete = cnx.prepareStatement(INSERT_RETRAIT);
			requete.setInt(1,noArticle);
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
	
	//pour afficher la liste des articles vendus
	@Override
	public List<ArticleVendu> selectAll(){
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>(); 
	
		
		try {
			Connection cnx = ConnectionProvider.getConnection();
			Statement stmt = cnx.createStatement(); 
			ResultSet rs = stmt.executeQuery(SELECT_ALL_ARTICLE); 
			
			while(rs.next()) {
				ArticleVendu article = new ArticleVendu(); 
				article.setNomArticle(rs.getString("nom_article"));
				article.setPrixVente(rs.getInt("prix_initial"));
				LocalDate localDate =rs.getDate("date_fin_encheres").toLocalDate();
				
				Utilisateur vendeur = new Utilisateur();
				vendeur.setPseudo(rs.getString("pseudo"));
				article.setVendeur(vendeur);
				article.setDateFinEncheres(localDate );
				
				//si l'ench�re existe 
				if( rs.getInt("montant_enchere")!=0) {
						Enchere enchere=new Enchere(rs.getInt("montant_enchere"),article);
						//ajoute l'enchere � l'article
				article.getListeEncheresArticle().add(enchere);
				}
			
				
				
				
				listeArticles.add(article); 
				
				//rs.close();
				//stmt.close();
	
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException(); 
			System.err.println("error");
		} 
		
		return listeArticles;
	}


	// pour afficher un article 
	@Override
	public ArticleVendu selectByNoArticle(int noArticle) {
		ArticleVendu articleVendu = new ArticleVendu(); 
		
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_NO_ARTICLE); 
			stmt.setInt(1, noArticle);
			ResultSet rs = stmt.executeQuery(); 
			
			while (rs.next()) {
				articleVendu.setNoArticle(noArticle);
				System.out.println(articleVendu.getNoArticle());
				articleVendu.setNomArticle(rs.getString("nom_article"));
				System.out.println(articleVendu.getNomArticle()); 
				articleVendu.setDescription(rs.getString("description"));
				System.out.println(articleVendu.getDescription());
				
				//récupérer le libelle de la catégorie 
				Categorie categorie = new Categorie(); 
				categorie.setLibelle(rs.getString("libelle"));
				articleVendu.setCategorieArticle(categorie);
				System.out.println(articleVendu.getCategorieArticle().getLibelle());
				
				/*//récupérer la meilleure offre ?
				Enchere enchere = new Enchere(); 
				enchere.setMontant_enchere(rs.getInt("montant_enchere"));
				articleVendu.setEnchere(enchere);
				System.out.println(articleVendu.getEnchere().getMontant_enchere());*/
				
				
				// mise à prix, fin enchère 
				articleVendu.setMiseAprix(rs.getInt("prix_initial"));
				System.out.println(articleVendu.getMiseAprix());
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				System.out.println(articleVendu.getDateFinEncheres());
				

				//retrait 
				Retrait retrait = new Retrait(); 
				retrait.setRue(rs.getString("rue"));
				retrait.setCode_postal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
				articleVendu.setLieuRetrait(retrait);
				System.out.println(articleVendu.getLieuRetrait().getRue());
				System.out.println(articleVendu.getLieuRetrait().getCode_postal());
				System.out.println(articleVendu.getLieuRetrait().getVille());
				
				//vendeur
				Utilisateur vendeur = new Utilisateur(); 
				vendeur.setPseudo(rs.getString("pseudo"));
				articleVendu.setVendeur(vendeur);;
				System.out.println(articleVendu.getVendeur().getPseudo());
				
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		
		return articleVendu ;
	}
	
	//afficher la liste des articles par cat�gories et par nom d'article
	
	@Override
	public List<ArticleVendu> selectByCategorieNom(String categorie,String contient) throws BusinessException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>(); 
		Utilisateur utilisateur = new Utilisateur();
		Connection connexion = null;
		PreparedStatement requete = null;
		ResultSet resultat = null;
		
		try {
			
			connexion = ConnectionProvider.getConnection();
			requete = connexion.prepareStatement(SELECT_BY_CATEGORIE_ARTICLE);
			requete.setString(1, categorie);
			requete.setString(2,contient + "%");
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
	
	//afficher la liste des article par nom d'article
	
		
	@Override
	public List<ArticleVendu> selectByNom(String contient) throws BusinessException {
			List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>(); 
			Connection connexion = null;
			PreparedStatement requete = null;
			ResultSet resultat = null;
			
			try {
				
				connexion = ConnectionProvider.getConnection();
				requete = connexion.prepareStatement(SELECT_ALL_ARTICLE_NOM);
				requete.setString(1,contient + "%");
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
		
		
		
	
	
}
