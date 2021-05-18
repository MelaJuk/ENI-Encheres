<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Connexion</title>
</head>
<body>

	<div class="container">
		<h1 class="text-center">Connexion</h1>
	
		<form action="<%=request.getContextPath()%>/ServletConnexion" method="post">
			<div class="form-group row">
				<div class="col-2">
                   <label for="nom" >Identifiant</label>
            	</div>
                 <div class="col-3">
                   <input type="text" class="form-control" name="login" placeholder="login">
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