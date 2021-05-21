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
				  
				 <textarea rows="5" cols="33" placeholder="Description de votre article"></textarea> 
           </div>
           
           <label for="categories">Catégories:</label>
    		
    			<select name="categories" id="categories">
    				<option value="">---Merci de choisir une option---</option>
    				<option value="informatique">Informatique</option>
    				<option value="ameublement">Ameublement</option>
    				<option value="vetement">Vêtement</option>
    				<option value="sport&loisirs">Sport&Loisirs</option>
    			</select>	
    			
    		<div class="col-2">
                <label for="prix">Mise à prix:</label>	
                <select name="credit" size="2">
                	<option>10 crédits</option>
                	<option>20 crédits</option>
                	<option>30 crédits</option>
                	<option>40 crédits</option>
                	<option>50 crédits</option>
                	<option>60 crédits</option>
                	<option>70 crédits</option>
                	<option>80 crédits</option>
                	<option>90 crédits</option>
                	<option>100 crédits</option>
                	<option>110 crédits</option>
                	<option>120 crédits</option>
                	<option>130 crédits</option>
                	<option>140 crédits</option>
                	<option>150 crédits</option>
                	<option>160 crédits</option>
                	<option>170crédits</option>
                	<option>180 crédits</option>
                	<option>190 crédits</option>
                	<option>200 crédits</option>
                	
                
                </select>
  					  
           </div>
           
           <div class="col-2">
           	<label for="debut">Début de l'enchère:</label>	
           	<input type="datetime-local" id="debut" name="debut" value"" min="" max="" >
           </div>
           
            <div class="col-2">
           	<label for="fin">Fin de l'enchère:</label>	
           	<input type="datetime-local" id="fin" name="debut" value"" min="" max="" >
           </div>
    			
    			
    			
    		 <div class="row">
           		<div class="col-2">
           		<input type="submit" value="Enregistrer"/>
           		</div>
           		
           		<div class="col-3">
           		<input type="reset" value="Annuler"/>

           		</div>
           		
           		 <div class="col-3">
           		<a href="listeDesEncheres">Retourner à l'accueil</a>
           		</div>
	
	
	
	</form>
	
	

</body>
</html>