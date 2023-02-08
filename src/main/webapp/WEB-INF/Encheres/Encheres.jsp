<%@ include file="./Partials/Header.jspf" %>

  <section class="container-fluid main_section">
    <form action="" class="search_bar">
      <div class="form_search_section">
        <input class="form_input" type="text" name="rechercher" id="rechercher" placeholder="Chercher un article">
      </div>
      <div class="form_select_section">
        <label class="form_label" for="selection_category">Catégorie : </label>
        <Select class="form_input" name="selection_category">
        	<option value = "">Toutes</option>
          <c:forEach items="${ categories }" var="categorie">
          	<option value="${ categorie.categorieId }">${ categorie.libelle }</option>
          </c:forEach>
        </Select>
      </div>
      <div>
        <button class="form_button">Rechercher</button>
      </div>
    </form>
  </section>

  <section class="container">
    <ul class="card_deck">
     <c:forEach items="${ articles }" var="article">
	     <li class="card">
	        <div class="card_image_section">
	          <img class="card_image" src="./Public/Images/Articles/${ article.imageURL }" alt="">
	        </div>
	        <div class="card_text_section">
	          <div class="card_text_header">
	            <c:choose>
	              <c:when test="${user != null}">
	                <h3 class="card_text_title">
	                  <a href="${pageContext.request.contextPath }/encheres/detailEnchere?id=${article.articleId}">PC Gamer</a>
	                </h3>
	              </c:when>
	              <c:otherwise>
	                <h3 class="card_text_title">${ article.nomArticle }</h3>
	              </c:otherwise>
	            </c:choose>
	          </div>
	          <div class="card_text_content">
	            <ul>
	              <li><strong>Prix</strong> : ${ article.prixInitial } points</li>
	              <li><strong>Fin de l'enchère</strong> : ${ article.dateFinEncheres }</li>
	            </ul>
	          </div>
	          <div class="card_text_footer">
	            <p>Vendeur <a href="${ pageContext.request.contextPath }/encheres/profil?id=${ article.utilisateur.utilisateurId }">${ article.utilisateur.pseudo }</a></p>
	          </div>
	        </div>
	      </li>
     </c:forEach>
    </ul>
  </section>
<%@ include file="./Partials/Footer.jspf" %>