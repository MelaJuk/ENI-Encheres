
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
	
		<form action="<%=request.getContextPath()%>/creer" method="post">
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
                   <label for="nom" >Nom</label>
            	</div>
                 <div class="col-3">
                   <input type="text" class="form-control" name="nom" placeholder="nom" required="required">
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
                   <label for="codePostal">Code postal</label>
                </div>
                <div class="col-3">
                   <input type="text" class="form-control" name="codePostal"  required="required">
                </div>   
                <c:if test="${erreurCodePostal=='erreurCodePostal'}">
           		<p>Le code postal n'est pas valide</p>
           		</c:if> 
           </div>
           
           <div class="form-group row">
           		<div class="col-2">
                   <label for="motDePasse">Mot de passe</label>
                </div>
                <div class="col-3">
                   <input type="password" class="form-control" name="motDePasse"  required="required">
                </div>  
                
 
           		 
           </div>
           
           <div class="form-group row">
           		<div class="col-2">
                   <label for="confirmation">Confirmation</label>
                </div>
                <div class="col-3">
                   <input type="password" class="form-control" name="confirmation"  required="required">
                </div>  
                <c:if test="${erreurMotDePasse=='erreurMotDePasse'}">
           		<p>Les mots de passes sont différents</p>
           		</c:if> 
           </div>
           
           
           
           <div class="form-group row">
           		<div class="col-2">
                   <label for="email">Email</label>
                </div>
                <div class="col-3">
                   <input type="text" class="form-control" name="email"  required="required">
                </div>   
                <c:if test="${email=='email'}">
           		<p>L'email n'est pas valide</p>
           		</c:if>
           </div>
          
           
           <div class="form-group row">
           		<div class="col-2">
                   <label for="rue">Rue</label>
                </div>
                <div class="col-3">
                   <input type="text" class="form-control" name="rue"  required="required">
                </div>   
           </div>
           
            <div class="form-group row">
           		<div class="col-2">
                   <label for="rue">Ville</label>
                </div>
                <div class="col-3">
                   <input type="text" class="form-control" name="ville"  required="required">
                </div>   
           </div>
           
           <div class="row">
           		<div class="col-2">
           		<input type="submit" value="Créer"/>
           		</div>
           		
           		<div class="col-3">
           		<input type="reset" value="Annuler"/>
           		</div>
           		
           		<div class="col-3">
           		<a href="index.html">Retourner à l'accueil</a>
           		</div>
           		

           </div>
           
           
           
		
		</form>
		
		 
	
	
	</div>
	
	
	
</body>
</html>