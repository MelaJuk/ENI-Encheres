package fr.eni.eniEncheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.eniEncheres.bo.Enchere;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String INSERT_ENCHERE="INSERT INTO ENCHERES (date_enchere, montant_enchere, no_article, no_utilisateur) VALUES (?,?,?,?)";
	
	
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
