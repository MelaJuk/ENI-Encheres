<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		
			<div>
				<p>Pseudo : ${utilisateur.pseudo}</p>
			</div>
		
			<div>
				<p>Nom : ${utilisateur.nom} </p>
			</div>
			
			<div>
				<p>Prénom : ${utilisateur.prenom}</p>
			</div>
		
			<div>
				<p>Email : ${utilisateur.email}</p>
			</div>
			
			<div>
				<p>Téléphone :  ${utilisateur.telephone}</p>
			</div>
		
			<div>
				<p>Rue :  ${utilisateur.rue}</p>
			</div>
			
			<div>
				<p>Code Postal : ${utilisateur.codePostal}</p>
			</div>
		
			<div>
				<p>Ville : ${utilisateur.ville}</p>
			</div>
			<div>
				<a href="Ventes">Retour</a>
			</div>
			
		</div>
	</div>
</body>
</html>