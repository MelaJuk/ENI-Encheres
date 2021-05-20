<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

 <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
<title>Nouvelle vente</title>
</head>
<body>

	<h1>ENI-Enchères</h1>
	<h1>Nouvelle vente</h1>
	
	<form action="" methode="POST">
		 <div class="form-group row">
			<div class="col-2">
                <label for="prenom" >Article:</label>
				<input type="text" class="form-control" name="article" placeholder="Nom de l'article" required="required">     
           </div>
           <div class="col-2">
                <label for="prenom" >Description:</label>
				<input type="text" class="form-control" name="description" placeholder="Description de votre article" required="required" style="width: 320px;height: 70px" >     
           </div>
           
           <label for="categories">Catégories:</label>
    		
    			<select name="categories" id="categories">
    				<option value="">---Merci de choisir une option---</option>
    				<option value="informatique">Informatique</option>
    				<option value="ameublement">Ameublement</option>
    				<option value="vetement">Vêtement</option>
    			</select>	
    			
    		 <div class="row">
           		<div class="col-2">
           		<input type="submit" value="Enregistrer"/>
           		</div>
           		
           		<div class="col-3">
           		<input type="reset" value="Annuler"/>

           		</div>
	
	
	
	</form>
	
	

</body>
</html>