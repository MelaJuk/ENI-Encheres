package fr.eni.eniEncheres.bo;

import java.util.List;

public class Categorie {

	private  int noCategorie;
	private String libelle;
	private List<ArticleVendu> categorieArticle ; 
	
	
	/**
	 * 
	 * @param noCategorie
	 * @param libelle
	 */
	public Categorie(String libelle) { 
		this.libelle = libelle;
	}
	

	public int getNoCategorie() {
		return noCategorie;
	}


	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Retrait [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
	}
	
	
}
