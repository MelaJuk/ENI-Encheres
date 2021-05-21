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

</body>
</html>