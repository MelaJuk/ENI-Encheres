<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail vente</title>
</head>
<body>

	<h1>Détail vente</h1>
	
	<p> Test </p>


                <c:if test="${!empty ArticleManager.articleVendu}">
                    <%-- Si la vente est créée, alors on affiche un récapitulatif de la vente. --%>
                    <p class="succes">Vous venez de mettre en vente l'objet : ${ArticleManager.articleVendu.nomArticle}</p>
                </c:if>
     
     <div>
     	<p >Article : ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Description : ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Catégorie : ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Meilleure offre : ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Mise à prix : ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Fin de l'enchère : ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Retrait : ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Vendeur : ${ArticleManager.articleVendu.nomArticle}</p>
     	
     	
     	<div>
     		<form action="<%=request.getContextPath()%>/ServletAjouterEnchere" method="post">
     			<div>
     				<label for="montant_enchere">Ma proposition : </label>
     				<select name="montant_enchere" size="1">
     					<option value="10" selected>10 crédits</option>
     					<option value="20" selected>20 crédits</option>
     					<option value="30" selected>30 crédits</option>	
     				</select>
     			</div>
     			<div>
     			<input type="hidden" name="noUtilisateur" value="${sessionScope.noUtilisateur }">
     			<input type="submit" value="Enchérir">
     			</div>
     		</form>
     	</div>
     	
     </div>

</body>
</html>