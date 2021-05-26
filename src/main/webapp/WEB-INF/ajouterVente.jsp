	<%@include file="entete.jsp" %>
	<h1>Nouvelle vente</h1>
	<div>
	<form action="<%=request.getContextPath()%>/ServletAjouterVente" method="POST">
		 <div class="form-group">
			<div class="col-2">
                <label for="prenom" >Article:</label>
				<input type="text" class="form-control" name="article" placeholder="Nom de l'article" required="required">    
           </div>
           
           
           <div class="col-2">
                <label for="prenom" >Description:</label>
				  
				 <textarea name="description" rows="5" cols="33" placeholder="Description de votre article"></textarea> 
           </div>
           
           <label for="categories">Catégories:</label>
    		
    			<select name="categories" id="categories">
    				<option value="informatique" selected>Informatique</option>
    				<option value="ameublement">Ameublement</option>
    				<option value="vetement">Vêtement</option>
    				<option value="sportloisir">SportLoisirs</option>
    			</select>	
    			
    		<div class="col-2">
                <label for="prix">Mise à prix:</label>	
                <select name="credit" size="1">
                	<option value="10" selected>10 crédits</option>
                	<option value="20">20 crédits</option>
                	<option value="30">30 crédits</option>
                	<option value="40">40 crédits</option>
                	<option value="50">50 crédits</option>
                	<option value="60">60 crédits</option>
                	<option value="70">70 crédits</option>
                	<option value="80">80 crédits</option>
                	<option value="90">90 crédits</option>
                	<option value="100">100 crédits</option>
                	<option value="110">110 crédits</option>
                	<option value="120">120 crédits</option>
                	<option value="130">130 crédits</option>
                	<option value="140">140 crédits</option>
                	<option value="150">150 crédits</option>
                	<option value="160">160 crédits</option>
                	<option value="170">170 crédits</option>
                	<option value="180">180 crédits</option>
                	<option value="190">190 crédits</option>
                	<option value="200">200 crédits</option>
                	
                
                </select>
  					  
           </div>
           
           <div class="col-2">
           	<label for="debut">Début de l'enchère:</label>	
           	<input type="date" id="debut" name="debut" value="<%=LocalDate.now() %>"    min="<%=LocalDate.now() %>" max="" required >
           </div>
          
            <div class="col-2">
           	<label for="fin">Fin de l'enchère:</label>	
           	<input type="date" id="fin" name="fin" value="<%=LocalDate.now() %>"  min="<%=LocalDate.now() %>" max="" required>
           </div>
    			
    			
    			
    		
           	
           	<div class="retrait">
           	<p>Retrait</p>
           	<div class="col-2">
                <label for="rue" >Rue:</label>
				<input type="text" class="form-control" name="rue"  value="${sessionScope.sessionUtilisateur.rue}">    
           </div>
           		<div class="col-2">
                <label for="codePostal" >CodePostal:</label>
				<input type="text" class="form-control" name="codePostal"  value="${sessionScope.sessionUtilisateur.codePostal}">    
           </div>
           
           </div>
           		<div class="col-2">
                <label for="ville" >Ville:</label>
				<input type="text" class="form-control" name="ville"  value="${sessionScope.sessionUtilisateur.ville}">    
           </div>
           	
           	</div>
	 			<div class="row">
           		<div class="col-2">
           		<input type="hidden" name="noUtilisateur" value="${sessionScope.sessionUtilisateur.noUtilisateur}">
           		<input type="submit" value="Enregistrer"/>
           		</div>
           		
           		<div class="col-3">
           		<input type="reset" value="Annuler"/>

           		</div>
           		
           		 <div class="col-3">
           		<a href="ServletNouvelleVente">Retourner à l'accueil</a>
           		</div>
	
	
	</form>
	</div>
	

</body>
</html>