<%@ page import="org.encheres.eni.messages.LecteurMessage" %>

<%@ include file="./Partials/Header.jspf" %>

<main class="darkblue_main">
  <section>
    <div>
      <h1 class="white_title">Détail vente</h1>
    </div>
  </section>
  
  <section class="container flex_cards">
    <div>
      <img class="auction_image" src="../Public/Images/Articles/${article.imageURL}" alt="${article.nomArticle}">
    </div>
    <div class="auction_description">
      <div>
        <h2>${enchere.article.nomArticle}</h2>
      </div>
      <table>
        <tr>
          <td class="auction_table_titles">Description :</td>
          <td>${enchere.article.description}</td>
        </tr>
        <tr>
          <td class="auction_table_titles">Catégorie :</td>
          <td>${categorie}</td>
        </tr>
        <c:if test="${!empty enchere.acheteur.pseudo}">
          <tr>
            <td class="auction_table_titles">Meilleure offre :</td>
            <td>${enchere.montant_enchere} pts par ${enchere.acheteur.pseudo}</td>
          </tr>
        </c:if>
        <tr>
          <td class="auction_table_titles">Mise à prix :</td>
          <td>${enchere.article.prixInitial} points</td>
        </tr>
        <tr>
          <td class="auction_table_titles">Fin de l'enchère :</td>
          <td>
            <fmt:formatDate value="${dateFinEnchere_formatDate}" dateStyle="short"/>
          </td>
        </tr>
        <tr>
          <td class="auction_table_titles">Retrait :</td>
          <td>
            ${retrait.rue}<br>
            ${retrait.codePostal} ${retrait.ville}
          </td>
        </tr>
        <tr>
          <td class="auction_table_titles">Vendeur :</td>
          <td>${nomVendeur}</td>
        </tr>
        <c:choose>
          <c:when test="${user.utilisateurId != enchere.article.vendeurId && user.credit > enchere.montant_enchere}">
    	    <tr>
    	      <td class="auction_table_titles">Ma proposition :</td>
    	      <td>
    	        <form method="POST" action="${pageContext.request.contextPath}/encheres/detailEnchere">
    	          <input class="form_input_light" name="nouvelleOffre" type="number" min="${enchere.montant_enchere + 1}" max="${user.credit}" value="${enchere.montant_enchere + 10}">
    	          <button class="form_button_light" type="submit" name="articleId" value="${enchere.article.articleId}">Enchérir</button>
    	        </form>
    	      </td>
    	    </tr>    
          </c:when>
          <c:when test="${user.utilisateurId != enchere.article.vendeurId && user.credit <= enchere.montant_enchere}">
            <tr>
              <td class="auction_no_bet" colspan="2">Vous n'avez pas assez de crédits pour enchérir sur cette vente</td>
            </tr>
          </c:when>
          <c:otherwise></c:otherwise>
        </c:choose>
        
      </table>
      
      <!-- TODO : mettre en forme les messages d'erreur -->
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
  
  <!--<div>
  	<c:if test="${ utilisateur.utilisatuerId == user.utilisateurId }">	
  		<a class="form_button" href="/encheres/profil">Modifier</a>	
  	</c:if>
  </div>-->	
	
</main>
<%@ include file="./Partials/Footer.jspf" %>