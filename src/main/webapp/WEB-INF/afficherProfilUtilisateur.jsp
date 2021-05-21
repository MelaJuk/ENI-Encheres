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
		
		<form action="<%=request.getContextPath()%>/afficherProfil" method="get">
		
			<div>
				<div>
					<label for="pseudo">Pseudo : </label>
				</div>
				<div>
					<input type="text" name="pseudo" value="toto">
				</div>
			</div>
		
			<div>
				<div>
					<label for="nom">Nom : </label>
				</div>
				<div>
					<input type="text" name="nom" value="${utilisateur.nom}">
				</div>
			</div>
			
			<div>
				<div>
					<label for="prenom">Prénom : </label>
				</div>
				<div>
					<input type="text" name="prenom" value="${utilisateur.prenom}">
				</div>
			</div>
		
			<div>
				<div>
					<label for="email">Email : </label>
				</div>
				<div>
					<input type="text" name="email" value="${utilisateur.email}">
				</div>
			</div>
			
			<div>
				<div>
					<label for="telephone">Téléphone : </label>
				</div>
				<div>
					<input type="text" name="telephone" value="${utilisateur.telephone}">
				</div>
			</div>
		
			<div>
				<div>
					<label for="rue">Rue : </label>
				</div>
				<div>
					<input type="text" name="rue" value="${utilisateur.rue}">
				</div>
			</div>
			
			<div>
				<div>
					<label for="code_postal">Code Postal : </label>
				</div>
				<div>
					<input type="text" name="code_postal" value="${utilisateur.codePostal}">
				</div>
			</div>
		
			<div>
				<div>
					<label for="ville">Ville : </label>
				</div>
				<div>
					<input type="text" name="ville" value="${utilisateur.ville}">
				</div>
			</div>
		</form>
	</div>
</body>
</html>