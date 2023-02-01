<%@ include file="./Partials/Header.jspf" %>
	
	<section class="connect_main">
    <div >
      <form class="connect_form create_form" method="post" action="${pageContext.request.contextPath}/encheres/inscription" >
        <div>
          <h1>Créer un compte</h1>
        </div>
        <div class="connect_input">
          <div class="form_create_section">
            <input class="form_input" name="email" type="email" placeholder="Email" maxlength="20" required autofocus>
            <input class="form_input" name="pseudo" type="text" placeholder="Pseudo" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="nom" type="text" placeholder="Nom" maxlength="30" required>
            <input class="form_input" name="prenom" type="text" placeholder="Prénom" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="telephone" type="tel" placeholder="Téléphone" maxlength="10">
          </div>

          <div class="form_create_section">
            <input class="form_input" name="rue" type="text" placeholder="Rue" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="codePostal" type="text" placeholder="Code Postal" maxlength="10" required>
            <input class="form_input" name="ville" type="text" placeholder="Ville" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="motDePasse" type="password" placeholder="Mot de passe (8 caractères min.)" minlength="8" maxlength="30" required>
          </div>
          <div class="form_create_section">
            <input class="form_input" name="controle_motDePasse" type="password" placeholder="Confirmer mot de passe" minlength="8" maxlength="30" required>
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
    </div>
  </section>
	
<%@ include file="./Partials/Footer.jspf" %>
