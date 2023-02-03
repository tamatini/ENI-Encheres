<!-- 
TODO revoir le completion du champs email qui est reste vide
 -->


<%@ page import="org.encheres.eni.messages.LecteurMessage" %>

<%@ include file="./Partials/Header.jspf" %>
	
	<section class="connect_main">
    <div >
      <form class="connect_form create_form" method="post" action="${pageContext.request.contextPath}/encheres/ModifierProfil" >
        <div>
          <h1>Mon Profil</h1>
        </div>
        <div class="connect_input">
          <div class="form_create_section">
            <input class="form_input" name="email" type="email" value="${user.email}" placeholder="Email" maxlength="20" required autofocus>
            <input class="form_input" name="pseudo" type="text" value="${user.pseudo}" placeholder="Pseudo" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="nom" type="text" value="${user.nom}" placeholder="Nom" maxlength="30" required>
            <input class="form_input" name="prenom" type="text" value="${user.prenom}" placeholder="Prénom" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="telephone" type="tel" value="${user.telephone}" placeholder="Téléphone" maxlength="10">
          </div>

          <div class="form_create_section">
            <input class="form_input" name="rue" type="text" value="${user.rue}" placeholder="Rue" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="codePostal" type="text" value="${user.codePostal}" placeholder="Code Postal" maxlength="10" required>
            <input class="form_input" name="ville" type="text" value="${user.ville}" placeholder="Ville" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="motDePasse" type="password" value="${user.motDePasse}" placeholder="Mot de passe (8 caractères min.)" minlength="8" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="controle_motDePasse" type="password" placeholder="Confirmer mot de passe" minlength="8" maxlength="30" required>
          </div >
          <div class="form_create_section">
          	<p>Crédit : ${ user.credit }</p>
          </div>
          
        </div>
        <div class="create_form_controls">
          <div class="connect_input">
            <button type="submit" class="form_button">Enregistrer</button>
          </div>
          <div class="connect_input">
            <a href="accueil.html" class="form_button no_account">Supprimer mon compte</a> <!--  TODO rediriger vers servlet suppression compte utilisateur et redirige vers accueil -->
          </div>
        </div>
      </form>
      
      <c:if test="${! empty Liste_codes_erreurs}">
      <div>
      	<p>Erreurs !</p>
      
          <c:forEach var="codeErreur" items="${Liste_codes_erreurs}">
      	    <span class="erreurs">${LecteurMessage.getMessageErreur(codeErreur)}</span>
      	  </c:forEach>
      	
      </div>
      </c:if>
      
    </div>
  </section>
	
<%@ include file="./Partials/Footer.jspf" %>
