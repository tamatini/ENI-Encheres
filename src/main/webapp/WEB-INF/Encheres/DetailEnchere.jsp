<%@ include file="./Partials/Header.jspf" %>

<section class="container">
  <div class="card_text_title">
    <h1>Détail vente</h1>
  </div>
  <div>
    <h2>${article.nomArticle}</h2>
  </div>
  <table class="profil_table">
    <tr>
      <td>Description :</td>
      <td>${article.nomArticle}</td>
    </tr>
    <tr>
      <td>Catégorie :</td>
      <td>${categorie}</td>
    </tr>
    <tr>
      <td>Meilleure offre :</td>
      <td>${miseMax} points par ${nomGagnant}</td>
    </tr>
    <tr>
      <td>Mise à prix :</td>
      <td>${article.prixInitial}</td>
    </tr>
    <tr>
      <td>Fin de l'enchère :</td>
      <td>${article.dateFinEncheres.toLocalDate()}</td>
    </tr>
    <tr>
      <td>Retrait :</td>
      <td>
        ${retrait.rue}<br>
        ${retrait.codePostal} ${retrait.ville}
      </td>
    </tr>
    <tr>
      <td>Vendeur :</td>
      <td>${nomVendeur}</td>
    </tr>
    
    <c:choose>
      <c:when test="${user.credit > miseMax}">
	    <tr>
	      <td>Ma proposition :</td>
	      <td>
	        <form method="POST" action="${pageContext.request.contextPath}/encheres/detailEnchere">
	          <input type="number" min="${miseMax}" max="${user.credit}" value="${miseMax + 10}">
	          <button type="submit">Enchérir</button>
	        </form>
	      </td>
	    </tr>    
      </c:when>
      <c:otherwise>
        <p>Vous n'avez pas assez de crédits pour enchérir sur cette vente</p>
      </c:otherwise>
    </c:choose>
    
  </table>
</section>

<!--<div>
	<c:if test="${ utilisateur.utilisatuerId == user.utilisateurId }">	
		<a class="form_button" href="/encheres/profil">Modifier</a>	
	</c:if>
</div>-->	
	
<%@ include file="./Partials/Footer.jspf" %>
