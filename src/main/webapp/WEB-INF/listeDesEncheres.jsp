<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>liste des encheres</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>
	
	<p> Test </p>

	<a href=""<%=request.getContextPath()%>/creer"">déconnection</a> 
	
               
             
                    

                <c:if test="${!empty sessionScope.sessionUtilisateur}">
                    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
                    <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.email}</p>
                </c:if>
                
                
      
	<h1>ENI-Enchères</h1>
	
	
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="#">Enchères</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="ServletNouvelleVente">Vendre un article</a>
              <li class="nav-item">
              <a class="nav-link" href="#">Mon profil</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Déconnexion</a>
            </li>
            
            </li>
          </ul>
        </div>
      </div>
    </nav>
    
    <h2>Listes des enchères</h2>
    
    	<form role="search">
    		<div>
    			<label for="filtre">Filtres :</label>
    			<br>
    			<input type="search" id="filtre" name="q" placeholder="Le nom de l'article contient:" size="30">
    			
    		</div>
    			
    	<label for="categories">Catégories:</label>
    		
    			<select name="categories" id="categories">
    				<option value="">---Merci de choisir une option---</option>
    				<option value="toute">Toutes</option>
    				<option value="informatique">Informatique</option>
    				<option value="ameublement">Ameublement</option>
    				<option value="vetement">Vêtement</option>
    				<option value="sport&loisirs">Sport&Loisirs</option>
    			</select>	
    			
    			<input type="submit" value="Rechercher" style="width: 320px;height: 70px">
    		
    			
    			<p>Mes achats</p>
    				<div>
    					<label for="achats">Enchères ouvertes</label>
    					<input type="checkbox" name=achats" value="achat">
    				</div>
    				<div>
    					<label for="achats">Mes enchères en cours</label>
    					<input type="checkbox" name=achats" value="achat">
    				</div>
    				<div>
    					<label for="achats">Mes enchères emportées</label>
    					<input type="checkbox" name=achats" value="achat">
    				</div>
    		
    			
    			
    		
    			
		</form>
                
</body>
</html>