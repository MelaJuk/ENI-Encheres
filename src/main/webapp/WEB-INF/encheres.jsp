
	
	
	<%@include file="entete.jsp" %>
    
    <h2>Listes des enchères</h2>
    
    	<form role="search" action="<%=request.getContextPath()%>/afficherListeEnchere" method="GET">
    		<div>
    			<label for="filtre">Filtres :</label>
    			<br>
    			<input type="search" id="filtre" name="nom" placeholder="Le nom de l'article contient:"  value='' size="30">
    			
    		</div>
    			
    	<label for="categories">Catégories:</label>
    		
    			<select name="categories" id="categories">
    				<option value="toute" selected>Toutes</option>
    				<option value="informatique">Informatique</option>
    				<option value="ameublement">Ameublement</option>
    				<option value="vetement">Vêtement</option>
    				<option value="sportloisirs">Sport&Loisirs</option>
    		
    		
    			</select>	
    			
    			<input type="submit" value="Rechercher" style="width: 320px;height: 70px">
					<c:if test="${!empty sessionScope.sessionUtilisateur}">
					 
					 <div class="row">
					 	<div class="col-6">
							 <input type="radio"  name="choix" value="mesAchats" checked>
		   					<label for="mesAchats">Mes achats</label><br/>
							
					
		    				<div>
		    					<label for="achats">Enchères ouvertes</label>
		    					<input type="checkbox"  name="encheres" value="eouvertes" checked >
		    				</div>
		    				<div>
		    					<label for="achats">Mes enchères en cours</label>
		    					<input type="checkbox" name="encheres" value="eencours" >
		    				</div>
		    				<div>
		    					<label for="achats">Mes enchères emportées</label>
		    					<input type="checkbox" name="encheres" value="eemportees">
		    				</div>
						</div> 
							 <div class="col-6">

			    				<input type="radio"  name="choix" value="mesVentes" >
			   					<label for="mesVentes">Mes ventes</label><br/>
			    				
			    					<div>
			    					<label for="encours">Mes ventes en cours</label>
			    					<input type="checkbox" name="ventes" value="vencours" checked>
			    				</div>
			    				<div>
			    					<label for="nondebutes">Ventes non débutées</label>
			    					<input type="checkbox" name="ventes" value="vndebutees">
			    				</div>
			    				<div>
			    					<label for="vterminees">Mes enchères emportées</label>
			    					<input type="checkbox"  name="ventes" value="vterminees">
			    				</div>
			    			</div>
			    	</div>
    		</c:if>
    	
		</form>
		<div class="container listeArticle">
			<c:if test="${!empty listeEncheres}">
			
				<div class="card-deck" style="margin-top:20px">
					
					<c:forEach var="a" items="${listeEncheres}">
							<div class="row">		
								<div class="card mb-4 " style="width: 30rem; margin-right:60px" >
									<div class="card-header text-center"><a href="ServletAjouterEnchere?noArticle=${a.noArticle}">${a.nomArticle}<a></a></div>
									<div class="car-body">
										<div class="card-text">Prix : ${a.prixVente}
										<c:if test="${!empty a.listeEncheresArticle}">
										
										
										
										</c:if></div>
										<div class="card-text">Fin de l'enchère : ${a.dateFinEncheres.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))}</div>
										<div class="card-text"><c:if test="${!empty sessionScope.sessionUtilisateur}"><a href= "afficherProfil?pseudo=${a.vendeur.pseudo}"></c:if> Vendeur : ${a.vendeur.pseudo} <c:if test="${!empty sessionScope.sessionUtilisateur}"> </a> </c:if></div>
									</div>
								</div>
								</div>	
					</c:forEach>
					
				</div>
				
				
			</c:if>
		</div>
		
	</div>
</body>
</html>