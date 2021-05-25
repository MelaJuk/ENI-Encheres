package fr.eni.eniEncheres.bo;

import java.time.LocalDate;

public class Enchere {

	private LocalDate dateEnchere; 
	private int montant_enchere;
	private Utilisateur acheteur;;
	private ArticleVendu article;
	
	public Enchere() {

	}

	
	
	



	public Enchere(int montant_enchere, ArticleVendu article) {
		this.montant_enchere = montant_enchere;
		this.article = article;
	}







	public Enchere(LocalDate dateEnchere, int montant_enchere) {
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}


	public LocalDate getDateEnchere() {
		return dateEnchere;
	}


	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}


	public int getMontant_enchere() {
		return montant_enchere;
	}


	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}


	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montant_enchere=" + montant_enchere + "]";
	} 
	
	
	
	
	
	
	
	
	
	
	
}
