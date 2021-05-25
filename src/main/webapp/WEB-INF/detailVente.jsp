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
     	<p >${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Description: ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Catégorie: ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Meilleure offre : ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Mise à prix : ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Fin de l'enchère: ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Retrait: ${ArticleManager.articleVendu.nomArticle}</p>
     	<p >Vendeur: ${ArticleManager.articleVendu.nomArticle}</p>
     	
     	<%--Ajouter formulaire pour récupérer la proposition d'enchère et ajouter bouton "enchérir" --%>
     	<p >Ma proposition : ${ArticleManager.articleVendu.nomArticle}</p> 
     </div>

</body>
</html>