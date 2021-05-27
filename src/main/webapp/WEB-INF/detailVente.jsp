	<%@include file="entete.jsp" %>

	<h1>D�tail vente</h1>
	
	<p> Test </p>


                <c:if test="${!empty ArticleManager.articleVendu}">
                    <%-- Si la vente est créée, alors on affiche un récapitulatif de la vente. --%>
                    <p class="succes">Vous venez de mettre en vente l'objet : ${articleVendu.nomArticle}</p>
                </c:if>
     <form action="<%=request.getContextPath()%>/ServletAjouterEnchere" method="post">
	     <div>
	     	<p>Article : ${articleVendu.nomArticle}</p>
	     	<p>Description : ${articleVendu.description}</p>
	     	<p>Cat�gorie : ${articleVendu.categorieArticle.libelle}</p>
	     	<p>Meilleure offre : ${articleVendu.enchere.montant_enchere}
	     		<input type="hidden" name="montant_enchere" value="<c:if test="${empty articleVendu.enchere}">0</c:if><c:if test="${!empty articleVendu.enchere}">${articleVendu.enchere.montant_enchere}</c:if>">
	     	</p>
	     	<p>Mise � prix : ${articleVendu.miseAprix}</p>
	     	<p>Fin de l'ench�re : ${articleVendu.dateFinEncheres}</p>
	     	<p>Retrait : ${articleVendu.lieuRetrait.rue} ${articleVendu.lieuRetrait.code_postal} ${articleVendu.lieuRetrait.ville}</p>
	     	<p> Vendeur : 
	     		<a href="afficherProfil?=">${articleVendu.vendeur.pseudo}</a>
	     	</p>
	     	
	     	
	     	<div>
	     		
	     			<div>
	     				<label for="montant_enchere">Ma proposition : </label>
	     				<select name="montant_nvlle_enchere" size="1">
	     					<option value="10" selected>10 cr�dits</option>
	     					<option value="20" selected>20 cr�dits</option>
	     					<option value="30" selected>30 cr�dits</option>	
	     					<option value="40" selected>40 cr�dits</option>	
	     				</select>
	     			</div>
	     			<div>
	     			<input type="hidden" name="noArticle" value="${articleVendu.noArticle}">
	     			<input type="hidden" name="noUtilisateur" value="${sessionScope.sessionUtilisateur.noUtilisateur}">
	     			<input type="submit" value="Ench�rir">
	     			</div>
	     		
	     	</div>
	     </div>
	 </form>	
	     

</body>
</html>