package fr.eni.eniEncheres.bo;

public class Categorie {

	private  int noCategorie;
	private String libelle;
	
	
	/**
	 * 
	 * @param noCategorie
	 * @param libelle
	 */
	public Categorie(int noCategorie, String libelle) {
		
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}


	/**
	 * 
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
