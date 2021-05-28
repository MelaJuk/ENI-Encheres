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
	private static final String SELECT_ALL_ARTICLE = "SELECT   nom_article , prix_initial,date_fin_encheres,pseudo,ar.no_article as noArticle,MAX(ISNULL(e.montant_enchere,0)) AS montant_enchere FROM ARTICLES_VENDUS ar\r\n"
			+ "			LEFT JOIN UTILISATEURS u ON u.no_utilisateur=ar.no_utilisateur\r\n"
			+ "			LEFT JOIN ENCHERES e ON e.no_article=ar.no_article "
			+"          GROUP BY ar.no_article,nom_article,prix_initial,date_fin_encheres,pseudo"
			+ "         ORDER BY date_fin_encheres";
	
	private static final String SELECT_ALL_ARTICLE_NOM = "SELECT nom_article , prix_initial,date_fin_encheres,pseudo,ar.no_article as noArticle,MAX(ISNULL(e.montant_enchere,0)) AS montant_enchere FROM ARTICLES_VENDUS ar\r\n"
			+ "			LEFT JOIN UTILISATEURS u ON u.no_utilisateur=ar.no_utilisateur\r\n"
			+ "			LEFT JOIN ENCHERES e ON e.no_article=ar.no_article WHERE nom_article LIKE  ? "
			+ "         GROUP BY ar.no_article,nom_article,prix_initial,date_fin_encheres,pseudo  ORDER BY date_fin_encheres";
	
	private static final String SELECT_BY_CATEGORIE_ARTICLE = "SELECT nom_article , prix_initial,date_fin_encheres,pseudo,ar.no_article as noArticle,MAX(ISNULL(e.montant_enchere,0)) AS montant_enchere FROM ARTICLES_VENDUS ar\r\n"
			+ "			LEFT JOIN UTILISATEURS u ON u.no_utilisateur=ar.no_utilisateur\r\n"
			+ "			LEFT JOIN ENCHERES e ON e.no_article=ar.no_article  LEFT JOIN CATEGORIES c ON c.no_categorie=ar.no_categorie "
			+ "         WHERE libelle=? AND nom_article LIKE  ? "
			+"          GROUP BY ar.no_article,nom_article,prix_initial,date_fin_encheres,pseudo"
			+ "         ORDER BY date_fin_encheres";
	
	
	private static final String SELECT_BY_NO_ARTICLE = "SELECT av.nom_article, av.description,av.no_article, c.libelle, e.montant_enchere, av.prix_initial, \r\n"
			+ "			av.date_fin_encheres, r.rue, r.code_postal,r.ville, u.pseudo FROM ARTICLES_VENDUS av \r\n"
			+ "			LEFT JOIN CATEGORIES c ON c.no_categorie = av.no_categorie \r\n"
			+ "			LEFT  JOIN RETRAITS r ON r.no_article = av.no_article\r\n"
			+ "			LEFT  JOIN UTILISATEURS u ON u.no_utilisateur = av.no_utilisateur \r\n"
			+ "			LEFT  JOIN ENCHERES e ON e.no_article = av.no_article\r\n"
			+ "			WHERE av.no_article=?" ;
	
	


	
	private static final String SELECT_VENTE_BY_NOUTILISATEUR="SELECT nom_article , prix_initial,date_fin_encheres,pseudo,ar.no_article as noArticle,MAX(ISNULL(e.montant_enchere,0)) AS montant, date_debut_encheres  FROM ARTICLES_VENDUS ar \r\n"
			+ "						LEFT JOIN UTILISATEURS u ON u.no_utilisateur=ar.no_utilisateur \r\n"
			+ "						LEFT JOIN ENCHERES e ON e.no_article=ar.no_article  LEFT JOIN CATEGORIES c ON c.no_categorie=ar.no_categorie \r\n"
			+ "						WHERE libelle LIKE ? AND nom_article LIKE ? AND DATEDIFF(day,date_debut_encheres,GETDATE())>=0 AND DATEDIFF(day,date_fin_encheres,GETDATE())<=0 AND ar.no_utilisateur=? \r\n"
			+"          GROUP BY ar.no_article,nom_article,prix_initial,date_fin_encheres,pseudo, date_debut_encheres "
			+ "						ORDER BY date_fin_encheres";
	
	private static final String SELECT_VENTE_BY_NOUTILISATEUR_NON_DEBUTEES="SELECT nom_article , prix_initial,date_fin_encheres,pseudo,ar.no_article as noArticle,MAX(ISNULL(e.montant_enchere,0)) AS montant, date_debut_encheres FROM ARTICLES_VENDUS ar \r\n"
			+ "						LEFT JOIN UTILISATEURS u ON u.no_utilisateur=ar.no_utilisateur \r\n"
			+ "						LEFT JOIN ENCHERES e ON e.no_article=ar.no_article  LEFT JOIN CATEGORIES c ON c.no_categorie=ar.no_categorie \r\n"
			+ "						WHERE libelle LIKE ? AND nom_article LIKE ? AND DATEDIFF(day,date_debut_encheres,GETDATE())<0 AND DATEDIFF(day,date_fin_encheres,GETDATE())<=0 AND ar.no_utilisateur=? \r\n"
			+"          GROUP BY ar.no_article,nom_article,prix_initial,date_fin_encheres,pseudo, date_debut_encheres "
			+ "						ORDER BY date_fin_encheres";
	
	private static final String SELECT_VENTE_BY_NOUTILISATEUR_TERMINEES="SELECT nom_article , prix_initial,date_fin_encheres,pseudo,ar.no_article as noArticle,MAX(ISNULL(e.montant_enchere,0)) AS montant, date_debut_encheres FROM ARTICLES_VENDUS ar \r\n"
			+ "						LEFT JOIN UTILISATEURS u ON u.no_utilisateur=ar.no_utilisateur \r\n"
			+ "						LEFT JOIN ENCHERES e ON e.no_article=ar.no_article  LEFT JOIN CATEGORIES c ON c.no_categorie=ar.no_categorie \r\n"
			+ "						WHERE libelle LIKE ? AND nom_article LIKE ? AND DATEDIFF(day,date_fin_encheres,GETDATE())>0  AND ar.no_utilisateur=? \r\n"
			+"          GROUP BY ar.no_article,nom_article,prix_initial,date_fin_encheres,pseudo, date_debut_encheres "
			+ "						ORDER BY date_fin_encheres";
	
	private static final String SELECT_PRIX_VENTE = "SELECT prix_vente FROM ARTICLES_VENDUS WHERE no_article=?";
	
	
	
	
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
				article.setMiseAprix(rs.getInt("prix_initial"));
				article.setNoArticle(rs.getInt("noArticle"));
				LocalDate localDate =rs.getDate("date_fin_encheres").toLocalDate();
				
				Utilisateur vendeur = new Utilisateur();
				vendeur.setPseudo(rs.getString("pseudo"));
				article.setVendeur(vendeur);
				article.setDateFinEncheres(localDate );
				
				//si l'ench�re existe 
//				if( rs.getInt("montant_enchere")!=0) {
//						Enchere enchere=new Enchere(rs.getInt("montant_enchere"),article);
//						//ajoute l'enchere � l'article
//				article.getListeEncheresArticle().add(enchere);
				//}
			
				
				
				
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
				
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				
				System.out.println(articleVendu.getNomArticle());
				
				//récupérer le libelle de la catégorie 
				Categorie categorie = new Categorie(); 
				categorie.setLibelle(rs.getString("libelle"));
				articleVendu.setCategorieArticle(categorie);
				
				
				//récupérer l'enchère en cours sur l'article
				Enchere enchere = new Enchere(); 
				if (rs.getInt("montant_enchere")!=0) {
					enchere.setMontant_enchere(rs.getInt("montant_enchere"));
					articleVendu.setEnchere(enchere);
				}
				
				// mise à prix, fin enchère 
				articleVendu.setMiseAprix(rs.getInt("prix_initial"));
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				

				//retrait 
				Retrait retrait = new Retrait(); 
				retrait.setRue(rs.getString("rue"));
				retrait.setCode_postal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));
				articleVendu.setLieuRetrait(retrait);
				
				//vendeur
				Utilisateur vendeur = new Utilisateur(); 
				vendeur.setPseudo(rs.getString("pseudo"));
				articleVendu.setVendeur(vendeur);;
				
				System.out.println(articleVendu);
				
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
				article.setNoArticle(resultat.getInt("noArticle"));
				article.setMiseAprix(resultat.getInt("prix_initial"));
				LocalDate localDate =resultat.getDate("date_fin_encheres").toLocalDate();
				
				Utilisateur vendeur = new Utilisateur();
				vendeur.setPseudo(resultat.getString("pseudo"));
				article.setVendeur(vendeur);
				article.setDateFinEncheres(localDate );
				
				//si l'ench�re existe 
				//if( resultat.getInt("montant_enchere")!=0) {
					//	Enchere enchere=new Enchere(resultat.getInt("montant_enchere"),article);
						//ajoute l'enchere � l'article
				//article.getListeEncheresArticle().add(enchere);
				//}
			
				
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
					article.setMiseAprix(resultat.getInt("prix_initial"));
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
					if( resultat.getInt("montant_enchere")!=0 ) {
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
		
		
	//liste les ventes en cours d'un utilisateur
	
	
	
	@Override
	public List<ArticleVendu> selectALLVentesByUtilisateur(String libelle, String contient,int noUtilisateur) throws BusinessException{
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>(); 
		Connection connexion = null;
		PreparedStatement requete = null;
		ResultSet resultat = null;
		
		try {
			
			connexion = ConnectionProvider.getConnection();
			requete = connexion.prepareStatement(SELECT_VENTE_BY_NOUTILISATEUR);
			
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
	public List<ArticleVendu> selectALLVentesByUtilisateurNonDebutees(String libelle, String contient,int noUtilisateur) throws BusinessException{
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>(); 
		Connection connexion = null;
		PreparedStatement requete = null;
		ResultSet resultat = null;
		
		try {
			
			connexion = ConnectionProvider.getConnection();
			requete = connexion.prepareStatement(SELECT_VENTE_BY_NOUTILISATEUR_NON_DEBUTEES);
			
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
	public List<ArticleVendu> selectALLVentesByUtilisateurTerminees(String libelle, String contient,int noUtilisateur) throws BusinessException{
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>(); 
		Connection connexion = null;
		PreparedStatement requete = null;
		ResultSet resultat = null;
		
		try {
			
			connexion = ConnectionProvider.getConnection();
			requete = connexion.prepareStatement(SELECT_VENTE_BY_NOUTILISATEUR_TERMINEES);
			
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
	public int selectPrixVente(int noArticle) {
		Connection connexion = null;
		PreparedStatement requete = null;
		ResultSet resultat = null;
	
			try {
			
			connexion = ConnectionProvider.getConnection();
			requete = connexion.prepareStatement(SELECT_PRIX_VENTE);
			requete.setInt(1,noArticle);
			resultat = requete.executeQuery();
			
			while (resultat.next()) {
				
				return resultat.getInt("prix_vente");
			}
			
			}catch (SQLException e) {
				e.printStackTrace();
			}

			return 0;
	}
	
}
