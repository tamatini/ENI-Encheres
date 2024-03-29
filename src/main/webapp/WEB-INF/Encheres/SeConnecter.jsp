<%@ include file="./Partials/Header.jspf" %>

	<section class="connect_main">
    	<div >
		    <c:if test="${ !empty listeCodesErreurs }" >
				<div class="erreurs" id="msg">
					<a href="" onclick="clearMessage()">Fermer x</a>
					<ul>
				 		<c:forEach items="${ listeCodesErreurs }" var="erreur">
							<li>${ LecteurMessage.getMessageErreur(erreur) }</li>
						</c:forEach>
					</ul>		
				</div>
			</c:if>
	      <form class="connect_form" method="post">
       <div>
         <h2>ME CONNECTER</h2>
       </div>
       <div class="connect_input">
         <input class="form_input" type="text" placeholder="Identifiant" name="pseudo" id="pseudo" required>
         <input class="form_input" type="password" placeholder="Mot de passe" name="motDePasse" id="motDePasse" required>
       </div>
       <div>
         <input type="checkbox" name="rememberme" id="rememberme">
         <label for="rememberme">Se souvenir de moi</label>
       </div>
       <div class="forgot" >
         <a href="">Mot de passe oublier?</a>
       </div>
       <div class="connect_input">
         <button type="submit" class="form_button">Connexion</button>
       </div>
       <div class="connect_input">
         <a href="${ pageContext.request.contextPath }" class="form_button no_account">Cr�er un compte</a>
       </div>
     </form>
    </div>
  </section>
	
<%@ include file="./Partials/Footer.jspf" %>