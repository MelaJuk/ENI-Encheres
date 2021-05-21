<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier profil utilisateur</title>
</head>
<body>
	<div>
		<h1>Profil utilisateur</h1>
		
		<form action="<%=request.getContextPath()%>/modifierProfil" method="post">
		
			<div>
				<div>
					<label for="pseudo">Pseudo : </label>
				</div>
				<div>
					<input type="text" name="pseudo">
				</div>
			</div>
		
			<div>
				<div>
					<label for="nom">Nom : </label>
				</div>
				<div>
					<input type="text" name="nom">
				</div>
			</div>
		
			<div>
				<div>
					<label for="prenom">Prénom : </label>
				</div>
				<div>
					<input type="text" name="prenom">
				</div>
			</div>
			
			<div>
				<div>
					<label for="email">Email : </label>
				</div>
				<div>
					<input type="text" name="email">
				</div>
			</div>
			
			<div>
				<div>
					<label for="telephone">Téléphone : </label>
				</div>
				<div>
					<input type="text" name="telephone">
				</div>
			</div>
		
			<div>
				<div>
					<label for="rue">Rue : </label>
				</div>
				<div>
					<input type="text" name="rue">
				</div>
			</div>
			
			<div>
				<div>
					<label for="code_postal">Code Postal : </label>
				</div>
				<div>
					<input type="text" name="code_postal">
				</div>
			</div>
		
			<div>
				<div>
					<label for="ville">Ville : </label>
				</div>
				<div>
					<input type="text" name="ville">
				</div>
			</div>
			
			<div>
           		<input type="submit" value="Modifier"/>
			</div>
		</form>
	</div>
</body>
</html>