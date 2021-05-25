<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Afficher profil utilisateur</title>
</head>
<body>
	<div>
		<h1>Profil utilisateur</h1>
		
		<div>
			<p>Pseudo : ${utilisateur.pseudo}</p>
			<p>Nom : ${utilisateur.nom} </p>
			<p>Prénom : ${utilisateur.prenom}</p>
			<p>Email : ${utilisateur.email}</p>
			<p>Téléphone :  ${utilisateur.telephone}</p>
			<p>Rue :  ${utilisateur.rue}</p>
			<p>Code Postal : ${utilisateur.codePostal}</p>
			<p>Ville : ${utilisateur.ville}</p>
			
			<div>
				<a href="Ventes">Retour</a>
			</div>
			
		</div>
	</div>
</body>
</html>