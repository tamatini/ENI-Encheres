<%@ include file="./Partials/Header.jspf" %>

	<section class="connect_main">
    <div >
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
          <a href="#" class="form_button no_account">Créer un compte</a>
        </div>
      </form>
    </div>
  </section>
	<c:if test="${ !empty listeCodesErreur }" >
 		<c:forEach items="${ listeCodesErreur }" var="erreur">
			<span class="erreurs">${ LecteurMessage.getMessageErreur(erreur) }</span>
		</c:forEach>
	</c:if>
<%@ include file="./Partials/Footer.jspf" %>