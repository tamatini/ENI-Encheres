
<%@ include file="./Partials/Header.jspf" %>

<main class="darkblue_main">
  <section>
    <div>
      <h1 class="white_title">Détail vente</h1>
    </div>
  </section>
  
  <section class="container flex_cards">
    <div>
      <img class="auction_image" src="${image}" alt="${article.nomArticle}">
    </div>
    <div class="auction_description">
      <div>
        <h2>${article.nomArticle}</h2>
      </div>
      <table>
        <tr>
          <td class="auction_table_titles">Description :</td>
          <td>${article.description}</td>
        </tr>
        <tr>
          <td class="auction_table_titles">Catégorie :</td>
          <td>${categorie}</td>
        </tr>
        <tr>
          <td class="auction_table_titles">Meilleure offre :</td>
          <td>${miseMax} points par ${nomGagnant}</td>
        </tr>
        <tr>
          <td class="auction_table_titles">Mise à prix :</td>
          <td>${article.prixInitial}</td>
        </tr>
        <tr>
          <td class="auction_table_titles">Fin de l'enchère :</td>
          <td>${article.dateFinEncheres}</td>
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
          <c:when test="${user.credit > miseMax}">
    	    <tr>
    	      <td class="auction_table_titles">Ma proposition :</td>
    	      <td>
    	        <form method="POST" action="${pageContext.request.contextPath}/encheres/detailEnchere">
    	          <input class="form_input_light" type="number" min="${miseMax}" max="${user.credit}" value="${miseMax + 10}">
    	          <button class="form_button_light" type="submit">Enchérir</button>
    	        </form>
    	      </td>
    	    </tr>    
          </c:when>
          <c:otherwise>
            <p>Vous n'avez pas assez de crédits pour enchérir sur cette vente</p>
          </c:otherwise>
        </c:choose>
        
      </table>
    </div>
  </section>
  
  <!--<div>
  	<c:if test="${ utilisateur.utilisatuerId == user.utilisateurId }">	
  		<a class="form_button" href="/encheres/profil">Modifier</a>	
  	</c:if>
  </div>-->	
	
</main>
<%@ include file="./Partials/Footer.jspf" %>