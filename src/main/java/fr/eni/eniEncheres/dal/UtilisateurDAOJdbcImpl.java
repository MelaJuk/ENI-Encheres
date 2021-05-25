package fr.eni.eniEncheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eniEncheres.bo.Utilisateur;





	public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

		private static final String INSERT_UTILATEUR = "INSERT INTO UTILISATEURS(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES(?,?,?,?,?,?,?,?,?,0,0)";
		private static final String SELECT_BY_LOGIN = "select * from UTILISATEURS u \r\n"
				+ "  lEFT JOIN ARTICLES_VENDUS ar on ar.no_utilisateur=u.no_utilisateur\r\n"
				+ "  where (email= ? AND mot_de_passe=? OR pseudo=? AND mot_de_passe=?) ";
		private static final String SELECT_ALL = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur FROM UTILISATEURS";

		private static final String UPDATE_PROFIL = "UPDATE UTILISATEURS \r\n"
				+ "SET nom=?\r\n"
				+ "    ,prenom=?\r\n"
				+ "      ,email=?\r\n"
				+ "      ,telephone=?\r\n"
				+ "      ,rue=?\r\n"
				+ "      ,code_postal=?\r\n"
				+ "      ,ville=?\r\n"
				+ "     \r\n"
				+ "WHERE pseudo=?";
		private static final String SELECT_BY_PSEUDO = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville FROM UTILISATEURS WHERE pseudo=?"; 

		private static final String DELETE_COMPTE = "DELETE FROM UTILISATEURS WHERE email=?";

	@Override
	public Utilisateur selectByLogin(String login,String motDePasse) {
		Utilisateur utilisateur = new Utilisateur();
		Connection connexion = null;
		PreparedStatement requete_login = null;
		ResultSet resultat = null;
		
		try {
			
			connexion = ConnectionProvider.getConnection();
			
			
			requete_login = connexion.prepareStatement(SELECT_BY_LOGIN);
			
			requete_login.setString(1,login);
			requete_login.setString(2,motDePasse);
			requete_login.setString(3,login);
			requete_login.setString(4,motDePasse);
			resultat = requete_login.executeQuery();
			boolean premiereLigne=true;
			
			while(resultat.next()) {
				if(premiereLigne) {
					if(login.contains("@")) {
						utilisateur.setEmail(login);
						utilisateur.setEmail(resultat.getString("pseudo"));
					}else {
						utilisateur.setPseudo(login);
						utilisateur.setEmail(resultat.getString("email"));
					}
					premiereLigne=false;
				}
				utilisateur.setNoUtilisateur(resultat.getInt("no_utilisateur"));
				utilisateur.setMotDePasse(motDePasse);
				utilisateur.setNom(resultat.getString("nom"));
				utilisateur.setPrenom(resultat.getString("prenom"));
				if(resultat.getString("telephone")!=null) {
					utilisateur.setTelephone(resultat.getString("telephone"));
				}
				
				utilisateur.setRue(resultat.getString("rue"));
				utilisateur.setCodePostal(resultat.getString("code_postal"));
				utilisateur.setVille(resultat.getString("ville"));
				utilisateur.setCredit(resultat.getInt("credit"));
				utilisateur.setAdminstrateur(resultat.getBoolean("administrateur"));				
			}
			//resultat.close();
			//requete_login.close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return utilisateur;
	}
	

	
	@Override
	public void insert(Utilisateur utilisateur) throws BusinessException {
		if(utilisateur==null) {
			BusinessException businessException = new BusinessException();
			//businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_UTILATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,utilisateur.getPseudo());
			pstmt.setString(2,utilisateur.getNom());
			pstmt.setString(3,utilisateur.getPrenom());
			pstmt.setString(4,utilisateur.getEmail());
			pstmt.setString(5,utilisateur.getTelephone());
			pstmt.setString(6,utilisateur.getRue());
			pstmt.setString(8,utilisateur.getVille());
			pstmt.setString(7,utilisateur.getCodePostal());
			pstmt.setString(9,utilisateur.getMotDePasse());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
			
			//rs.close();
			//pstmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			System.err.println("erreur");
		}
		
	}

	@Override
	public List<Utilisateur> selectAllUtilisateur() throws BusinessException {
		List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>(); 
	
		
		try {
			Connection cnx = ConnectionProvider.getConnection();
			Statement stmt = cnx.createStatement(); 
			ResultSet rs = stmt.executeQuery(SELECT_ALL); 
			
			while(rs.next()) {
				Utilisateur utilisateur = new Utilisateur(); 
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom")); 
				utilisateur.setPrenom(rs.getString("prenom")); 
				utilisateur.setEmail(rs.getString("email")); 
				utilisateur.setTelephone(rs.getString("telephone")); 
				utilisateur.setRue(rs.getString("rue")); 
				utilisateur.setCodePostal(rs.getString("code_postal")); 
				utilisateur.setVille(rs.getString("ville")); 
				utilisateur.setMotDePasse(rs.getString("mot_de_passe")); 
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdminstrateur(rs.getBoolean("administrateur"));	
				
				listeUtilisateurs.add(utilisateur); 
				
				//rs.close();
				//stmt.close();
	
			}
	
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException(); 
			System.err.println("error");
		} 
		
		return listeUtilisateurs;
	}



	@Override
	public void updateProfil(Utilisateur utilisateur,String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville) throws BusinessException {
		
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_PROFIL);
			pstmt.setString(1, nom);
			pstmt.setString(2,prenom);
			pstmt.setString(3, email);
			pstmt.setString(4,telephone);
			pstmt.setString(5,rue);
			pstmt.setString(6, codePostal);
			pstmt.setString(7, ville);
			pstmt.setString(8, utilisateur.getPseudo());
			pstmt.executeUpdate();
			//pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			
			throw businessException;
		}
		
		
	}



	@Override
	public Utilisateur selectByPseudo(String pseudo) {
		Utilisateur utilisateur = new Utilisateur();
		
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
<<<<<<< Updated upstream
			
			stmt.setString(1,pseudo);
=======
			stmt.setString(1, pseudo);
>>>>>>> Stashed changes
			ResultSet rs = stmt.executeQuery(); 
			
			
			while (rs.next()) {
				utilisateur.setPseudo("pseudo");
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom")); 
				utilisateur.setEmail(rs.getString("email")); 
				utilisateur.setTelephone(rs.getString("telephone")); 
				utilisateur.setRue(rs.getString("rue")); 
				utilisateur.setCodePostal(rs.getString("code_postal")); 
				utilisateur.setVille(rs.getString("ville")); 
				
				//rs.close();
				//stmt.close();
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			
			BusinessException businessException = new BusinessException(); 
		} 
	
		return utilisateur;
	}


	
	@CascadeOnDelete
	public boolean delete(String email) throws BusinessException {
		
		// 
		boolean compteDelete = false;
		
		
		
		
		try {
			Connection cnx = ConnectionProvider.getConnection();
			
			PreparedStatement requete = cnx.prepareStatement(DELETE_COMPTE);
			
			requete.setString(1, email);
			
			requete.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			
			throw businessException;
		}
		return compteDelete;
		
	}





	
	
		
	

	
	
	

	
	
	
}

	




	