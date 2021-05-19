
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Inscrition</title>
</head>
<body>
	<div class="container">
		<h1 class="text-center">Mon profil</h1>
	
		<form action="<%=request.getContextPath()%>/ServletInscrition" method="post">
			<div class="form-group row">
				<div class="col-2">
                   <label for="pseudo" >Pseudo</label>
            	</div>
                 <div class="col-3">
                   <input type="text" class="form-control" name="pseudo" placeholder="pseudo" required="required">
                 </div>
           </div>
           
           <div class="form-group row">
				<div class="col-2">
                   <label for="prenom" >Prénom</label>
            	</div>
                 <div class="col-3">
                   <input type="text" class="form-control" name="prenom" placeholder="prenom" required="required">
                 </div>
           </div>
           
           <div class="form-group row">
				<div class="col-2">
                   <label for="telephone" >Téléphone</label>
            	</div>
                 <div class="col-3">
                   <input type="text" class="form-control" name="telephone" placeholder="téléphone" >
                 </div>
           </div>
           
           <div class="form-group row">
				<div class="col-2">
                   <label for="codePostal" >Pseudo</label>
            	</div>
                 <div class="col-3">
                   <input type="text" class="form-control" name="nom" placeholder="nom" required="required">
                 </div>
           </div>
           
           <div class="form-group row">
           		<div class="col-2">
                   <label for="motDePasse">Mot de passe</label>
                </div>
                <div class="col-3">
                   <input type="password" class="form-control" name="motDePasse" placeholder="Mot de passe">
                </div>   
           </div>
           
           <div class="row">
           		<div class="col-2">
           		<input type="submit" value="Connexion"/>
           		<c:if test="${erreur=='erreur'}">
           		<p>Erreur à la connexion</p>
           		</c:if>
           		</div>
           		
           		<div class="col-4">
	           		<div class="row form-check">
	           			<input type="checkbox" name="souvenir" >
	           			<label for="souvenir">Se souvenir de moi</label>
	           		</div>
	           		<div class="row">
	           			<a href="">Mot de passe oublié</a>
	           		</div>
           		</div>
           </div>
           
            <div class="form-group row">
           		
                <div class="col-3 text-center">
                   <a href="">Créer un compte</a>
                </div>
           </div>
           
		
		</form>
		
		 
	
	
	</div>
	
	
	
</body>
</html>