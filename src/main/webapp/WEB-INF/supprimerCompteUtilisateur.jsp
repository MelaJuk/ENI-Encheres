<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   <!-- Bootstrap core CSS -->
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
   
<title>Supprimer compre utilisateur</title>
</head>
<body>

	<div class="container">
		<h1 class="text-center">Supprimer mon compte</h1>
		
		<form action="<%=request.getContextPath()%>/servletSupprimerCompte" method="post">
		
		
			<p class="text-center">Êtes-vous sûre de vouloir supprimer votre compte?
			
			<div class="col-center">
           		<div class="col-5">
           		<input type="submit" value="Oui! Je veux supprimer mon compte maintenant"/>
           		</div>
           		
           </div>          
        </form>

	<a href="index.html">Annuler et retourner à l'accueil</a>

</body>
</html>