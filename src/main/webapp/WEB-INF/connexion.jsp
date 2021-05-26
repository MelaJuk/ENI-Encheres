	<%@include file="entete.jsp" %>
		<h1 class="text-center">Connexion</h1>
	
		<form action="<%=request.getContextPath()%>/ServletConnexion" method="post">
			<div class="form-group row">
				<div class="col-2">
                   <label for="nom" >Identifiant</label>
            	</div>
                 <div class="col-3">
                   <input type="text" class="form-control" name="login" placeholder="login">
                 </div>
           </div>
           <div class="form-group row">
           		<div class="col-2">
                   <label for="motDePasse">Mot de passe</label>
                </div>
                <div class="col-3">
                   <input type="password" class="form-control" name="motDePasse" placeholder="Mot de passe">
                </div>   
           </div>
           
           <div class="row">
           		<div class="col-2">
           		<input type="submit" value="Connexion"/>
           		<c:if test="${erreur=='erreur'}">
           		<p>Erreur à la connexion</p>
           		</c:if>
           		</div>
           		
           		<div class="col-4">
	           		<div class="row form-check">
	           			<input type="checkbox" name="souvenir" >
	           			<label for="souvenir">Se souvenir de moi</label>
	           		</div>
	           		<div class="row">
	           			<a href="">Mot de passe oublié</a>
	           		</div>
           		</div>
           </div>
           
            <div class="form-group row">
           		
                <div class="col-3 text-center">
                   <a href="ServletInscription">Créer un compte</a>
                </div>
           </div>
           
           <div class="col-3">
           		<a href="index.html">Retourner à l'accueil</a>
           		</div>
           
		
		</form>
		
		 
	
	
	</div>
</body>
</html>