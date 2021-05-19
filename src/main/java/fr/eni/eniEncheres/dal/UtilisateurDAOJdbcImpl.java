package fr.eni.eniEncheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.eniEncheres.bo.Utilisateur;




public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String INSERT_UTILATEUR = "INSERT INTO UTILISATEURS(pseudo,nom,prenom,email,telephone,rue,ville,code_postal,mot_de_passe) VALUES(?,?,?,?,?,?,?,?,?)";
	
	
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











