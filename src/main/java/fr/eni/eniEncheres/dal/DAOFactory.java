package fr.eni.eniEncheres.dal;

public abstract class DAOFactory {

	
	public static UtilisateurDAO getUtilisateurDAO () {
		return new UtilisateurDAOJdbcImpl() ; 
	}
	
	public static VenteDAO getVenteDAO () {
		return new  VenteDAOJdbcImpl() ; 
	}
	
	public static EnchereDAO getEnchereDAO () {
		return new EnchereDAOJdbcImpl() ;
	}
	
	
}
