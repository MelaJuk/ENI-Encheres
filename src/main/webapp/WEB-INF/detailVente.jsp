	<%@include file="entete.jsp" %>

	<h1>D�tail vente</h1>
	
	<p> Test </p>


                <c:if test="${!empty ArticleManager.articleVendu}">
                    <%-- Si la vente est cr��e, alors on affiche un r�capitulatif de la vente. --%>
                    <p class="succes">Vous venez de mettre en vente l'objet : ${ArticleManager.articleVendu.nomArticle}</p>
                </c:if>
     
     <div>
     	<p >Article : ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Description : ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Cat�gorie : ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Meilleure offre : ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Mise � prix : ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Fin de l'ench�re : ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Retrait : ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Vendeur : ${ArticleManager.articleVendu.nomArticle}</p>
     	
     	
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