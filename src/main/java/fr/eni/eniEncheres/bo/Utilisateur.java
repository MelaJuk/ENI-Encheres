package fr.eni.eniEncheres.bo;

import java.util.List;

public class Utilisateur {

	private int noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private int credit =0;
	private boolean administrateur;
	private List<ArticleVendu> listeVente; 
	private List<Enchere> listeEncheresUtilisateur; 
	private List<ArticleVendu> listeAchat; 
	
	
	
	
	//création d'un utlisateur
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = 0;
		this.administrateur = false;
	}
	
	public Utilisateur() {
		super();
	}

		//création d'un utlisateur sans  telephone
		public Utilisateur(String pseudo, String nom, String prenom, String email, String rue,
				String codePostal, String ville, String motDePasse) {
			this.pseudo = pseudo;
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.rue = rue;
			this.codePostal = codePostal;
			this.ville = ville;
			this.motDePasse = motDePasse;
			this.credit = 0;
			this.administrateur = false;
		}


	public Utilisateur(String login, String motDePasse) {
		if(login.contains("@")) {
			this.email = login;
		}else {
			this.pseudo = login;
		}
			
			this.motDePasse = motDePasse;
		}

	

	public int getNoUtilisateur() {
		return noUtilisateur;
	}


	
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}



	public String getPseudo() {
		return pseudo;
	}



	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getTelephone() {
		return telephone;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	public String getRue() {
		return rue;
	}



	public void setRue(String rue) {
		this.rue = rue;
	}



	public String getCodePostal() {
		return codePostal;
	}



	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}



	public String getVille() {
		return ville;
	}



	public void setVille(String ville) {
		this.ville = ville;
	}



	public String getMotDePasse() {
		return motDePasse;
	}



	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}



	public int getCredit() {
		return credit;
	}



	public void setCredit(int credit) {
		this.credit = credit;
	}



	public boolean isAdminstrateur() {
		return administrateur;
	}



	public void setAdminstrateur(boolean adminstrateur) {
		this.administrateur = adminstrateur;
	}

	

	public List<Enchere> getListeEncheresUtilisateur() {
		return listeEncheresUtilisateur;
	}

	public void setListeEncheresUtilisateur(List<Enchere> listeEncheresUtilisateur) {
		this.listeEncheresUtilisateur = listeEncheresUtilisateur;
	}

	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal="
				+ codePostal + ", ville=" + ville + ", motDePasse=" + motDePasse + ", credit=" + credit
				+ ", adminstrateur=" + administrateur + "]";
	}
	

}
