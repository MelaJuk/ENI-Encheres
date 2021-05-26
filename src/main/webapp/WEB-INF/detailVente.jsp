	<%@include file="entete.jsp" %>

	<h1>D�tail vente</h1>
	
	<p> Test </p>


                <c:if test="${!empty ArticleManager.articleVendu}">
                    <%-- Si la vente est créée, alors on affiche un récapitulatif de la vente. --%>
                    <p class="succes">Vous venez de mettre en vente l'objet : ${articleVendu.nomArticle}</p>
                </c:if>
     
     <div>
     	<p>Article : ${articleVendu.nomArticle}</p>
     	<p>Description : ${articleVendu.description}</p>
     	<p>Cat�gorie : ${articleVendu.categorieArticle.libelle}</p>
     	<p>Meilleure offre : ${articleVendu.enchere.montant_enchere}</p>
     	<p>Mise � prix : ${articleVendu.miseAprix}</p>
     	<p>Fin de l'ench�re : ${articleVendu.dateFinEncheres}</p>
     	<p>Retrait : ${articleVendu.lieuRetrait.rue} ${articleVendu.lieuRetrait.code_postal} ${articleVendu.lieuRetrait.ville}</p>
     	<p> Vendeur : 
     		<a href="/afficherProfil">${articleVendu.vendeur.pseudo}</a>
     	</p>
     	
     	
     	<div>
     		<form action="<%=request.getContextPath()%>/ServletAjouterEnchere" method="post">
     			<div>
     				<label for="montant_enchere">Ma proposition : </label>
     				<select name="montant_enchere" size="1">
     					<option value="10" selected>10 cr�dits</option>
     					<option value="20" selected>20 cr�dits</option>
     					<option value="30" selected>30 cr�dits</option>	
     				</select>
     			</div>
     			<div>
     			<input type="hidden" name="noUtilisateur" value="${sessionScope.noUtilisateur }">
     			<input type="submit" value="Ench�rir">
     			</div>
     		</form>
     	</div>
     	
     </div>

</body>
</html>