<%@ page import="org.encheres.eni.messages.LecteurMessage" %>

<%@ include file="./Partials/Header.jspf" %>
	
	<section class="connect_main">
    <div >
      <form class="connect_form create_form" method="post" action="${pageContext.request.contextPath}/encheres/inscription" >
        <div>
          <h1>Créer un compte</h1>
        </div>
        <div class="connect_input">
          <div class="form_create_section">
            <input class="form_input" name="email" type="email" value="${donnees_formulaire.email}" placeholder="Email" maxlength="20" required autofocus>
            <input class="form_input" name="pseudo" type="text" value="${donnees_formulaire.pseudo}" placeholder="Pseudo" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="nom" type="text" value="${donnees_formulaire.nom}" placeholder="Nom" maxlength="30" required>
            <input class="form_input" name="prenom" type="text" value="${donnees_formulaire.prenom}" placeholder="Prénom" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="telephone" type="tel" value="${donnees_formulaire.telephone}" placeholder="Téléphone" maxlength="10">
          </div>

          <div class="form_create_section">
            <input class="form_input" name="rue" type="text" value="${donnees_formulaire.rue}" placeholder="Rue" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="codePostal" type="text" value="${donnees_formulaire.codePostal}" placeholder="Code Postal" maxlength="10" required>
            <input class="form_input" name="ville" type="text" value="${donnees_formulaire.ville}" placeholder="Ville" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="motDePasse" type="password" value="${donnees_formulaire.motDePasse}" placeholder="Mot de passe (8 caractères min.)" minlength="8" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="controle_motDePasse" type="password" value="${controle_motDePasse}" placeholder="Confirmer mot de passe" minlength="8" maxlength="30" required>
          </div>
        </div>
        <div class="create_form_controls">
          <div class="connect_input">
            <button type="submit" class="form_button">Connexion</button>
          </div>
          <div class="connect_input">
            <a href="accueil.html" class="form_button no_account">Annuler</a>
          </div>
        </div>
      </form>
      
      <c:if test="${! empty Liste_codes_erreurs}">
      	<p>Erreurs !</p>
      	<ul>
          <c:forEach var="codeErreur" items="${Liste_codes_erreurs}">
      	    <li>${LecteurMessage.getMessageErreur(codeErreur)}</li>
      	  </c:forEach>
      	</ul>
      </c:if>
      
    </div>
  </section>
	
<%@ include file="./Partials/Footer.jspf" %>
