<%@ include file="./Partials/Header.jspf" %>

<!-- Formulaire de recherche écrire le javascript pour modifier les couleurs du fond -->
  <section class="container-fluid main_section">
    <form action="" class="search_bar">
      <div class="form_search_section">
        <input class="form_input" type="text" name="rechercher" id="rechercher" placeholder="Chercher un article">
      </div>
      <div class="form_select_section">
        <label class="form_label" for="selection_category">Catégorie : </label>
        <Select class="form_input" name="selection_category">
          <option value="toutes">Toutes</option>
          <option value="informatique">Informatique</option>
          <option value="electronique">Electronique</option>
        </Select>
      </div>
      <div>
        <button class="form_button">Rechercher</button>
      </div>
    </form>
  </section>


  <!-- 
    résultat de recherche / liste produits cette partie sera à remplacer par une boucle 
    et des partials
  -->
  <section class="container">
    <ul class="card_deck">
      <li class="card">
        <div class="card_image_section">
          <img class="card_image" src="./Public/Images/pc.jpg" alt="">
        </div>
        <div class="card_text_section">
          <div class="card_text_header">
            <h3 class="card_text_title">
              <c:choose>
                <c:when test="${user != null}">
                  <a href="${pageContext.request.contextPath}/encheres/detailEnchere">PC Gamer</a>
                </c:when>
                <c:otherwise>
                  PC Gamer
                </c:otherwise>
              </c:choose>
            </h3>
          </div>
          <div class="card_text_content">
            <ul>
              <li><strong>Prix</strong> : 210 points</li>
              <li><strong>Fin de l'enchère</strong> : 10/08/2023</li>
            </ul>
          </div>
          <div class="card_text_footer">
            <p>Vendeur <a>jojo44</a></p>
          </div>
        </div>
      </li>
      <li class="card">
        <div class="card_image_section">
          <img class="card_image" src="./Public/Images/pc.jpg" alt="">
        </div>
        <div class="card_text_section">
          <div class="card_text_header">
            <h3 class="card_text_title"><a href="${pageContext.request.contextPath}/encheres/detailEnchere">PC Gamer</a></h3>
          </div>
          <div class="card_text_content">
            <ul>
              <li><strong>Prix</strong> : 210 points</li>
              <li><strong>Fin de l'enchère</strong> : 10/08/2023</li>
            </ul>
          </div>
          <div class="card_text_footer">
            <p>Vendeur <a>jojo44</a></p>
          </div>
        </div>
      </li>
      <li class="card">
        <div class="card_image_section">
          <img class="card_image" src="./Public/Images/pc.jpg" alt="">
        </div>
        <div class="card_text_section">
          <div class="card_text_header">
            <h3 class="card_text_title"><a href="#">PC Gamer</a></h3>
          </div>
          <div class="card_text_content">
            <ul>
              <li><strong>Prix</strong> : 210 points</li>
              <li><strong>Fin de l'enchère</strong> : 10/08/2023</li>
            </ul>
          </div>
          <div class="card_text_footer">
            <p>Vendeur <a>jojo44</a></p>
          </div>
        </div>
      </li>
      <li class="card">
        <div class="card_image_section">
          <img class="card_image" src="./Public/Images/pc.jpg" alt="">
        </div>
        <div class="card_text_section">
          <div class="card_text_header">
            <h3 class="card_text_title"><a href="#">PC Gamer</a></h3>
          </div>
          <div class="card_text_content">
            <ul>
              <li><strong>Prix</strong> : 210 points</li>
              <li><strong>Fin de l'enchère</strong> : 10/08/2023</li>
            </ul>
          </div>
          <div class="card_text_footer">
            <p>Vendeur <a>jojo44</a></p>
          </div>
        </div>
      </li>
      <li class="card">
        <div class="card_image_section">
          <img class="card_image" src="./Public/Images/pc.jpg" alt="">
        </div>
        <div class="card_text_section">
          <div class="card_text_header">
            <h3 class="card_text_title"><a href="#">PC Gamer</a></h3>
          </div>
          <div class="card_text_content">
            <ul>
              <li><strong>Prix</strong> : 210 points</li>
              <li><strong>Fin de l'enchère</strong> : 10/08/2023</li>
            </ul>
          </div>
          <div class="card_text_footer">
            <p>Vendeur <a>jojo44</a></p>
          </div>
        </div>
      </li>
      <li class="card">
        <div class="card_image_section">
          <img class="card_image" src="./Public/Images/pc.jpg" alt="">
        </div>
        <div class="card_text_section">
          <div class="card_text_header">
            <h3 class="card_text_title"><a href="#">PC Gamer</a></h3>
          </div>
          <div class="card_text_content">
            <ul>
              <li><strong>Prix</strong> : 210 points</li>
              <li><strong>Fin de l'enchère</strong> : 10/08/2023</li>
            </ul>
          </div>
          <div class="card_text_footer">
            <p>Vendeur <a>jojo44</a></p>
          </div>
        </div>
      </li>
    </ul>
  </section>
<%@ include file="./Partials/Footer.jspf" %>