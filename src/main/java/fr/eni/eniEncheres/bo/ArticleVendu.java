package fr.eni.eniEncheres.bo;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleVendu implements Serializable {

	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;	
	private LocalDate dateFinEncheres;	
	private int miseAprix;
	private int prixVente;
	private int etatVente;
	private Utilisateur acheteur;
	private Utilisateur vendeur;
	private Categorie categorieArticle;
	private Retrait lieuRetrait;
	private Enchere enchere;
	private List<Enchere> listeEncheresArticle; 
	
	
	
	
	public ArticleVendu() {
		
	}




	// Constructeur pour test
	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAprix, Categorie categorieArticle) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAprix = miseAprix;
		this.categorieArticle = categorieArticle;
		this.listeEncheresArticle = new ArrayList<Enchere>() ;
		
		//par defaut adresse du vendeur 
			//this.lieuRetrait.setRue(vendeur.getRue()); 
				//this.lieuRetrait.setCode_postal(vendeur.getCodePostal());
				//this.lieuRetrait.setVille(vendeur.getVille());
					
	}
	
	
	
	
	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAprix, Categorie categorieArticle, Retrait lieuRetrait) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAprix = miseAprix;
		this.categorieArticle = categorieArticle;
		this.lieuRetrait = lieuRetrait;
		this.listeEncheresArticle = new ArrayList<Enchere>() ;
	}


	
	//constructeur pour afficher un article
	public ArticleVendu(String nomArticle, LocalDate dateFinEncheres, int miseAprix, Utilisateur vendeur) {
		super();
		this.nomArticle = nomArticle;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAprix = miseAprix;
		this.vendeur = vendeur;
		this.listeEncheresArticle = new ArrayList<Enchere>() ;
	}




	//Petit constructeur 2 paramètres pour test
	public ArticleVendu(String nomArticle, String description) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
	}

	
		
	// getters and setters

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
		return etatVente;
	}



	public void setEtatVente(int etatVente) {
		this.etatVente = etatVente;
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

	
	public Enchere getEnchere() {
		return enchere;
	}


	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}

	public List<Enchere> getListeEncheresArticle() {
		return listeEncheresArticle;
	}


	public void setListeEncheresArticle(List<Enchere> listeEncheresArticle) {
		this.listeEncheresArticle = listeEncheresArticle;
	}
		
	




	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAprix="
				+ miseAprix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", acheteur=" + acheteur
				+ ", vendeur=" + vendeur + ", categorieArticle=" + categorieArticle + ", lieuRetrait=" + lieuRetrait
				+ ", listeEncheresArticle=" + listeEncheresArticle + "]";
	}
	

	
}
