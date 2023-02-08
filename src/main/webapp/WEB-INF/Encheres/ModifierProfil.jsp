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
            <input class="form_input" name="email" type="email" value="${utilisateur.email}" placeholder="Email" maxlength="20" required autofocus>
            <input class="form_input" name="pseudo" type="text" value="${utilisateur.pseudo}" placeholder="Pseudo" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="nom" type="text" value="${utilisateur.nom}" placeholder="Nom" maxlength="30" required>
            <input class="form_input" name="prenom" type="text" value="${utilisateur.prenom}" placeholder="Pr�nom" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="telephone" type="tel" value="${utilisateur.telephone}" placeholder="T�l�phone" maxlength="10">
          </div>

          <div class="form_create_section">
            <input class="form_input" name="rue" type="text" value="${utilisateur.rue}" placeholder="Rue" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="codePostal" type="text" value="${utilisateur.codePostal}" placeholder="Code Postal" maxlength="10" required>
            <input class="form_input" name="ville" type="text" value="${utilisateur.ville}" placeholder="Ville" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="motDePasse" type="password" placeholder="Mot de passe (8 caract�res min.)" minlength="8" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="nouveauMotDePasse" type="password" placeholder="Nouveau mot de passe (8 caract�res min.)" minlength="8" maxlength="30" >
          </div>
          <div class="form_create_section">
            <input class="form_input" name="controle_motDePasse" type="password" placeholder="Confirmer mot de passe" minlength="8" maxlength="30" >
          </div >
          <div class="form_create_section">
          	<p>Cr�dit : ${ utilisateur.credit }</p>
          </div>
          
        </div>
        <div class="create_form_controls">
          <div class="connect_input">
            <button type="submit" class="form_button">Enregistrer</button>
          </div>
          <div class="connect_input">
            <a href="${ pageContext.request.contextPath }/encheres/SupprimerMonCompte" class="form_button no_account">Supprimer mon compte</a>
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
