<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

 <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
<title>Ajouter vente</title>
</head>
<body>

	<h1>ENI-Enchères</h1>
	<h1>Nouvelle vente</h1>
	
	<form action="<%=request.getContextPath()%>/ServletAjouterVente" method="POST">
		 <div class="form-group row">
			<div class="col-2">
                <label for="prenom" >Article:</label>
				<input type="text" class="form-control" name="article" placeholder="Nom de l'article" required="required">    
           </div>
           
           
           <div class="col-2">
                <label for="prenom" >Description:</label>
				  
				 <textarea name="description" rows="5" cols="33" placeholder="Description de votre article"></textarea> 
           </div>
           
           <label for="categories">Catégories:</label>
    		
    			<select name="categories" id="categories">
    				<option value="">---Merci de choisir une option---</option>
    				<option value="informatique">Informatique</option>
    				<option value="ameublement">Ameublement</option>
    				<option value="vetement">Vêtement</option>
    				<option value="sport/loisirs">Sport&Loisirs</option>
    			</select>	
    			
    		<div class="col-2">
                <label for="prix">Mise à prix:</label>	
                <select name="credit" size="2">
                	<option value="10">10 crédits</option>
                	<option value="20">20 crédits</option>
                	<option value="30">30 crédits</option>
                	<option value="30">40 crédits</option>
                	<option value="30">50 crédits</option>
                	<option value="30">60 crédits</option>
                	<option value="30">70 crédits</option>
                	<option value="30">80 crédits</option>
                	<option value="30">90 crédits</option>
                	<option value="30">100 crédits</option>
                	<option value="30">110 crédits</option>
                	<option value="30">120 crédits</option>
                	<option value="30">130 crédits</option>
                	<option value="30">140 crédits</option>
                	<option value="30">150 crédits</option>
                	<option value="30">160 crédits</option>
                	<option value="30">170crédits</option>
                	<option value="30">180 crédits</option>
                	<option value="30">190 crédits</option>
                	<option value="30">200 crédits</option>
                	
                
                </select>
  					  
           </div>
           
           <div class="col-2">
           	<label for="debut">Début de l'enchère:</label>	
           	<input type="date" id="debut" name="debut" value"" min="" max="" >
           </div>
           
            <div class="col-2">
           	<label for="fin">Fin de l'enchère:</label>	
           	<input type="date" id="fin" name="fin" value"" min="" max="" >
           </div>
    			
    			
    			
    		 <div class="row">
           		<div class="col-2">
           		<input type="hidden" name="noUtilisateur" value="${sessionScope.sessionUtilisateur.noUtilisateur}">
           		<input type="submit" value="Enregistrer"/>
           		</div>
           		
           		<div class="col-3">
           		<input type="reset" value="Annuler"/>

           		</div>
           		
           		 <div class="col-3">
           		<a href="ServletNouvelleVente">Retourner à l'accueil</a>
           		</div>
           		
	
	
	
	</form>
	
	

</body>
</html>