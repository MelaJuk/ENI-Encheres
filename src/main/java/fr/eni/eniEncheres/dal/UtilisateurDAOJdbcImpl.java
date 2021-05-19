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

		private static final String SELECT_ALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal FROM Utilisateurs";
		private static final String INSERT_UTILATEUR = "INSERT INTO UTILISATEURS(pseudo,nom,prenom,email,telephone,rue,ville,code_postal,mot_de_passe) VALUES(?,?,?,?,?,?,?,?,?)";
		private static final String SELECT_BY_EMAIL = "SELECT email, motDePasse FROM utilisateur where (email=? AND motDePasse=?) ";
		private static final String SELECT_BY_PSEUDO = "SELECT pseudo, motDePasse FROM utilisateur where email=? ";
		private static final String AJOUTER = "insert into utilisateur (email, motDePasse) values (?, ?)";

	
		
		@Override
		public List<Utilisateur> selectAll() throws BusinessException {
			return null;
		}
		
		/*
		// r�cup�rer une liste d'utilisateurs
		@Override
		public List<Utilisateur> lister() {
			List<Utilisateur>utilisateurs = new ArrayList<Utilisateur>();
			Connection connexion = null;
			Statement requete = null;
			ResultSet resultat = null;
			
			try {
				
				connexion = ConnectionProvider.getConnection();
				requete = connexion.prepareStatement(SELECT_BY_EMAIL);
				
				while(resultat.next()) {
					String email = resultat.getString("Email");
					String motDePasse = resultat.getString("motDePasse");
					
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setEmail(email);
					utilisateur.setMotDePasse(motDePasse);
					
					utilisateurs.add(utilisateur);
				}
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}	
		return utilisateurs;
		}
		
	public Utilisateur select(String email,String motDePasse) {
		Utilisateur utilisateur;
		
		Connection connexion = null;
		PreparedStatement requete_mail = null;
		PreparedStatement requete_pseudo = null;
		ResultSet resultat = null;
		
		try {
			
			connexion = ConnectionProvider.getConnection();
			requete_mail = connexion.prepareStatement(SELECT_BY_EMAIL);
			requete_mail.setString(1,email);
			requete_mail.setString(1,motDePasse);
			resultat = requete_mail.executeQuery();
			
			
			while(resultat.next()) {
				String email = resultat.getString("Email");
				String motDePasse = resultat.getString("motDePasse");
				
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setEmail(email);
				utilisateur.setMotDePasse(motDePasse);
				
				utilisateur.add(utilisateur);
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return utilisateur;
	}
	*/	

	

	// ajouter un compte utilisateur avec juste email et mot de passe
	public void ajouter(Utilisateur utilisateur) {
		Connection connexion = null;
		PreparedStatement requete = null;
		
		try {
			connexion = ConnectionProvider.getConnection();
			requete = connexion.prepareStatement(AJOUTER);
			
			requete.setString(1, utilisateur.getEmail());
			requete.setString(2, utilisateur.getMotDePasse());
			requete.executeUpdate();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
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











