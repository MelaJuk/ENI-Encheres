<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ENI-Enchères</title>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ page import="java.time.format.DateTimeFormatter" %>
<!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

</head>
<body>
	<div class="container">
	<h1>ENI-Enchères</h1>
	
	
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
          	<c:if test="${empty sessionScope.sessionUtilisateur}">
            <li class="nav-item active">
              <a class="nav-link" href="ServletInscription">S'inscrire</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="ServletConnexion">Se connecter</a>
            </li>
            </c:if>
            <c:if test="${!empty sessionScope.sessionUtilisateur}">
             <li class="nav-item">
              <a class="nav-link" href="Ventes">Accueil</a>
            </li>
             <li class="nav-item active">
              <a class="nav-link" href="afficherListeEnchere">Enchères</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="ServletAjouterVente">Vendre un article</a>
              <li class="nav-item">
              <a class="nav-link" href="modifierProfil">Mon profil</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="ServletDeconnexion">Déconnexion</a>
            </li>
            </c:if>
          </ul>
        </div>
      </div>
    </nav>