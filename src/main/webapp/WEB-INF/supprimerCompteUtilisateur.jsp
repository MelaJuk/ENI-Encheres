<%@include file="entete.jsp" %>
		<h1 class="text-center">Supprimer mon compte</h1>
		
		<form action="<%=request.getContextPath()%>/ServletSupprimerCompte" method="post">
		
		
			<p class="text-center">�tes-vous s�re de vouloir supprimer votre compte?
			
			<div class="col-center">
           		<div class="col-5">
           		<input type="submit" value="Oui! Je veux supprimer mon compte maintenant"/>
           		</div>
           		
           </div>          
        </form>

	<a href="Ventes">Annuler et retourner � l'accueil</a>

</body>
</html>