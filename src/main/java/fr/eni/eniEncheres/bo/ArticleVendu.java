package fr.eni.eniEncheres.bo;

import java.time.LocalDate;

public class ArticleVendu {

	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAprix;
	private int prixVente;
	private int EtatVente;
	private Utilisateur acheteur;
	private Utilisateur vendeur;
	private Categorie categorieArticle;
	private Retrait lieuRetrait;
	
	
	
	/**
	 * 
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAprix
	 * @param vend
	 * @param categorieArticle
	 */
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAprix, Utilisateur vendeur, Categorie categorieArticle) {
		
		//créer par la base de donnée
		this.noArticle = noArticle;
		
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAprix = miseAprix;
		this.vendeur = vendeur;
		this.categorieArticle = categorieArticle;
		
		//par defaut adresse du vendeur
		this.lieuRetrait.setRue(vendeur.getRue()); 
		this.lieuRetrait.setCode_postal(vendeur.getCodePostal());
		this.lieuRetrait.setVille(vendeur.getVille());
		
		
	}

	
	


	





	public int getNoArticle() {
		return noArticle;
	}



	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}



	public String getNomArticle() {
		return nomArticle;
	}



	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}



	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}



	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}



	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}



	public int getMiseAprix() {
		return miseAprix;
	}



	public void setMiseAprix(int miseAprix) {
		this.miseAprix = miseAprix;
	}



	public int getPrixVente() {
		return prixVente;
	}



	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}



	public int getEtatVente() {
		return EtatVente;
	}



	public void setEtatVente(int etatVente) {
		EtatVente = etatVente;
	}



	public Utilisateur getAcheteur() {
		return acheteur;
	}



	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}



	public Utilisateur getVendeur() {
		return vendeur;
	}



	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}



	public Categorie getCategorieArticle() {
		return categorieArticle;
	}



	public void setCategorieArticle(Categorie categorieArticle) {
		this.categorieArticle = categorieArticle;
	}



	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}



	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}
	

	
}
