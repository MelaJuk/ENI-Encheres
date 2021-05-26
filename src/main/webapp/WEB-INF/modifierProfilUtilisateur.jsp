	<%@include file="entete.jsp" %>
		<h1>${sessionScope.sessionUtilisateur.pseudo}</h1>
		
		<form action="<%=request.getContextPath()%>/modifierProfil" method="post">
		
			<h1>Profil utilisateur</h1>
				<div>
					<label for="nom">Nom : </label>
				</div>
				<div>
					<input type="text" name="nom" value="${sessionScope.sessionUtilisateur.nom}">
				</div>
			
		
			<div>
				<div>
					<label for="prenom">Prénom : </label>
				</div>
				<div>
					<input type="text" name="prenom" value="${sessionScope.sessionUtilisateur.prenom}">
				</div>
			</div>
			
			<div>
				<div>
					<label for="email">Email : </label>
				</div>
				<div>
					<input type="text" name="email" value="${sessionScope.sessionUtilisateur.email}">
				</div>
			</div>
			
			<div>
				<div>
					<label for="telephone">Téléphone : </label>
				</div>
				<div>
					<input type="text"  name="telephone" value="${sessionScope.sessionUtilisateur.telephone}">
				</div>
			</div>
		
			<div>
				<div>
					<label for="rue">Rue : </label>
				</div>
				<div>
					<input type="text" name="rue" value="${sessionScope.sessionUtilisateur.rue}">
				</div>
			</div>
			
			<div>
				<div>
					<label for="code_postal">Code Postal : </label>
				</div>
				<div>
					<input type="text" name="codePostal" value="${sessionScope.sessionUtilisateur.codePostal}">
				</div>
			</div>
		
			<div>
				<div>
					<label for="ville" >Ville : </label>
				</div>
				<div>
					<input type="text" name="ville" value="${sessionScope.sessionUtilisateur.ville}">
				</div>
			
           		<input type="submit" value="Modifier"/>
           		<a href="<%=request.getContextPath()%>/ServletSupprimerCompte"> <input type="button" value="Supprimer"/></a>
			</div>
		</form>
		<a href="">Retour</a>
	</div>
</body>
</html>