<%@include file="entete.jsp" %>
		<h1 class="text-center">Mon profil</h1>
	
		<form action="<%=request.getContextPath()%>/creer" method="post">
			<div class="form-group row">
				<div class="col-2">
                   <label for="pseudo" >Pseudo</label>
            	</div>
                 <div class="col-3">
                
                   <input type="text" class="form-control" name="pseudo" placeholder="pseudo" required="required" <c:if test="${!empty pseudof}"> value=${pseudof}</c:if> >
                 </div>
                  <c:if test="${pseudo=='pseudo'}">
           				<p>Le pseudo existe déjà</p>
           			</c:if>
           </div>
           
           <div class="form-group row">
				<div class="col-2">
                   <label for="prenom" >Prénom</label>
            	</div>
                 <div class="col-3">
                   <input type="text" class="form-control" name="prenom" placeholder="prenom" required="required" <c:if test="${!empty prenom}"> value=${prenom}</c:if> >
                 </div>
           </div>
           
           <div class="form-group row">
				<div class="col-2">
                   <label for="nom" >Nom</label>
            	</div>
                 <div class="col-3">
                   <input type="text" class="form-control" name="nom" placeholder="nom" required="required"  <c:if test="${!empty nom}"> value=${nom}</c:if>>
                 </div>
           </div>
           
           <div class="form-group row">
				<div class="col-2">
                   <label for="telephone" >Téléphone</label>
            	</div>
                 <div class="col-3">
                   <input type="text" class="form-control" name="telephone" placeholder="téléphone"  <c:if test="${!empty telephone}"> value=${telephone}</c:if>>
                    <c:if test="${erreurTelephone=='erreurTelephone'}">
           				<p>Le numéro de téléphone n'est pas valide</p>
           			</c:if>
                 </div>
           </div>
           
          <div class="form-group row">
           		<div class="col-2">
                   <label for="codePostal">Code postal</label>
                </div>
                <div class="col-3">
                   <input type="text" class="form-control" name="codePostal" placeholder="code postal" required="required" <c:if test="${!empty codePostal}"> value=${codePostal}</c:if>>
                </div>   
                <c:if test="${erreurCodePostal=='erreurCodePostal'}">
           		<p>Le code postal n'est pas valide</p>
           		</c:if> 
           		
           </div>
           
           <div class="form-group row">
           		<div class="col-2">
                   <label for="motDePasse">Mot de passe</label>
                </div>
                <div class="col-3">
                   <input type="password" class="form-control" name="motDePasse" placeholder="Mot de passe" required="required">
                </div>  
                
 
           		 
           </div>
           
           <div class="form-group row">
           		<div class="col-2">
                   <label for="confirmation">Confirmation</label>
                </div>
                <div class="col-3">
                   <input type="password" class="form-control" name="confirmation" placeholder="Mot de passe" required="required">
                </div>  
                <c:if test="${erreurMotDePasse=='erreurMotDePasse'}">
           		<p>Les mots de passes sont différents</p>
           		</c:if> 
           </div>
           
           
           
           <div class="form-group row">
           		<div class="col-2">
                   <label for="email">Email</label>
                </div>
                <div class="col-3">
                   <input type="text" class="form-control" name="email" placeholder="email" required="required" <c:if test="${!empty email}"> value=${email}</c:if>>
                </div>   
                <c:if test="${email=='email'}">
           		<p>L'email n'est pas valide</p>
           		</c:if>
           		<c:if test="${emailExist=='emailExist'}">
           		<p>Un utilisateur a déjà cet email</p>
           		</c:if>
           </div>
          
           
           <div class="form-group row">
           		<div class="col-2">
                   <label for="rue">Rue</label>
                </div>
                <div class="col-3">
                   <input type="text" class="form-control" name="rue" placeholder="rue" required="required" <c:if test="${!empty rue}"> value=${rue}</c:if>>
                </div>   
           </div>
           
            <div class="form-group row">
           		<div class="col-2">
                   <label for="rue">Ville</label>
                </div>
                <div class="col-3">
                   <input type="text" class="form-control" name="ville" placeholder="Ville" required="required" <c:if test="${!empty ville}"> value=${ville}</c:if>>
                </div>   
           </div>
           
           <div class="row">
           		<div class="col-2">
           		<input type="submit" value="Créer"/>
           		</div>
           		
           		<div class="col-3">
           		<input type="reset" value="Annuler"/>

           		</div>
           		
           		<div class="col-3">
           		<a href="">Retourner à l'accueil</a>

           		</div>
           		

           </div>
           
           
           
		
		</form>
		
		 
	
	
	</div>
	
	
	
</body>
</html>