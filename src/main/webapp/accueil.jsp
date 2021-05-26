<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ENI-Enchères</title>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ page import="java.time.format.DateTimeFormatter" %>
<!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

</head>
<body>
	<div class="container">
	<h1>ENI-Enchères</h1>
	
	
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
          	<c:if test="${empty sessionScope.sessionUtilisateur}">
            <li class="nav-item active">
              <a class="nav-link" href="ServletInscription">S'inscrire</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="ServletConnexion">Se connecter</a>
            </li>
            </c:if>
            <c:if test="${!empty sessionScope.sessionUtilisateur}">
             <li class="nav-item active">
              <a class="nav-link" href="#">Enchères</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="ServletAjouterVente">Vendre un article</a>
              <li class="nav-item">
              <a class="nav-link" href="modifierProfil">Mon profil</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="ServletDeconnexion">Déconnexion</a>
            </li>
            </c:if>
          </ul>
        </div>
      </div>
    </nav>
    
    <h2>Listes des enchères</h2>
    
    	<form role="search" action="<%=request.getContextPath()%>/Ventes" method="GET">
    		<div>
    			<label for="filtre">Filtres :</label>
    			<br>
    			<input type="search" id="filtre" name="nom" placeholder="Le nom de l'article contient:"  value='' size="30">
    			
    		</div>
    			
    	<label for="categories">Catégories:</label>
    		
    			<select name="categories" id="categories">
    				<option value="toute" selected>Toutes</option>
    				<option value="informatique">Informatique</option>
    				<option value="ameublement">Ameublement</option>
    				<option value="vetement">Vêtement</option>
    				<option value="sportloisirs">Sport&Loisirs</option>
    		
    		
    			</select>	
    			
    			<input type="submit" value="Rechercher" style="width: 320px;height: 70px">
					<c:if test="${!empty sessionScope.sessionUtilisateur}">
				<p>Mes achats</p>
    				<div>
    					<label for="achats">Enchères ouvertes</label>
    					<input type="checkbox" name="achats" value="achat">
    				</div>
    				<div>
    					<label for="achats">Mes enchères en cours</label>
    					<input type="checkbox" name=achats" value="achat">
    				</div>
    				<div>
    					<label for="achats">Mes enchères emportées</label>
    					<input type="checkbox" name=achats" value="achat">
    				</div>
    		</c:if>
		</form>
		<div class="container listeArticle">
			<c:if test="${!empty listeArticles}">
			
				<div class="card-deck" style="margin-top:20px">
					
					<c:forEach var="a" items="${listeArticles}">
							<div class="row">		
								<div class="card mb-4 " style="width: 30rem; margin-right:60px" >
									<div class="card-header text-center">${a.nomArticle}</div>
									<div class="car-body">
										<div class="card-text">Prix : ${a.prixVente}
										<c:if test="${!empty a.listeEncheresArticle}">
										
										
										
										</c:if></div>
										<div class="card-text">Fin de l'enchère : ${a.dateFinEncheres.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))}</div>
										<div class="card-text"><c:if test="${!empty sessionScope.sessionUtilisateur}"><a href= "afficherProfil"></c:if> Vendeur : ${a.vendeur.pseudo} <c:if test="${!empty sessionScope.sessionUtilisateur}"> </a> </c:if></div>
									</div>
								</div>
								</div>	
					</c:forEach>
					
				</div>
				
				
			</c:if>
		</div>
		
	</div>
</body>
</html>


