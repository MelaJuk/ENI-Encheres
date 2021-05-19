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

	private static final String INSERT_UTILATEUR = "INSERT INTO UTILISATEURS(pseudo,nom,prenom,email,telephone,rue,ville,code_postal,mot_de_passe) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_BY_LOGIN = "select * from UTILISATEURS u \r\n"
			+ "  lEFT JOIN ARTICLES_VENDUS ar on ar.no_utilisateur=u.no_utilisateur\r\n"
			+ "  where (email= ? AND mot_de_passe=? OR pseudo=? AND mot_de_passe=?) ";
	
	private static final String AJOUTER = "insert into utilisateur (email, motDePasse) values (?, ?)";

	
	
	public Utilisateur select(String login,String motDePasse) {
		Utilisateur utilisateur = new Utilisateur();
		Connection connexion = null;
		PreparedStatement requete_login = null;
		PreparedStatement requete_pseudo = null;
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
					}else {
						utilisateur.setPseudo(login);
					}
					premiereLigne=false;
				}
				
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
			pstmt.setString(7,utilisateur.getVille());
			pstmt.setString(8,utilisateur.getCodePostal());
			pstmt.setString(9,utilisateur.getMotDePasse());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
			{
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			System.err.println("erreur");
		}			
	}
	
	
	
	
	
}











