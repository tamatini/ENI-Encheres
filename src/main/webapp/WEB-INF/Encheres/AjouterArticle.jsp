<%@ include file="./Partials/Header.jspf" %>
<div class="container_fluid dark_bg">
	<div class="container article_new_form">
		<c:if test="${ !empty listeCodesErreurs }" >
			<div class="erreurs" id="msg">
				<a href="" onclick="clearMessage()">Fermer x</a>
				<ul>
			 		<c:forEach items="${ listeCodesErreurs }" var="erreur">
						<li>${ LecteurMessage.getMessageErreur(erreur) }</li>
					</c:forEach>
				</ul>		
			</div>
		</c:if>
		<form class="create_form" method="post">
			<div class="article_form">
				<div class="article_form_img">
					<img alt="default" src="../Public/Images/default-image.jpg" >
				</div>
				<div class="article_form_input_section">
					<div class="form_create_section">
						<label class="form_label" for="nomArticle">Article : </label>
						<input class="form_input" name="nomArticle" id="nomArticle" type="text" >
					</div>
					<div class="form_create_section">
						<label class="form_label">Description :</label>
						<textarea name="description" id="description"  class="form_input" rows="" cols=""></textarea>
					</div>
					<div class="form_create_section">
						<label class="form_label">Catégorie</label>
						<select class="form_input" name="categorieId" id="categorieId">
							<option value="">--Sélectionner une catégories--</option>
							<c:if test="${ !empty categories }" >
						 		<c:forEach items="${ categories }" var="categorie">
									<option value="${ categorie.categorieId }">${ categorie.libelle }</option>
								</c:forEach>
							</c:if>
						</select>
					</div>
					<div class="form_create_section">
						<label class="form_label" for="imageArticle">Photo de l'article</label>
						<input  type="file" value="UPLOADER" name="image" id="imageArticle">
					</div>
					<div class="form_create_section">
						<label class="form_label" for="prixInitial">Mise à prix :</label>
						<input class="form_input" type="number" name="prixInitial" id="prixInitial">
					</div>
					<div class="form_create_section">
						<label class="form_label" for="dateDebut">Début de l'enchère :</label>
						<input class="form_input" type="date" name="dateDebut" id="dateDebut">
					</div>
					<div class="form_create_section">
						<label class="form_label" for="dateFin">Fin de l'enchère :</label>
						<input class="form_input" type="date" name="dateFin" id="dateFin">
					</div>				
				</div>
			</div>
			<div>
				<fieldset class="article_form_fieldset">
					<legend class="form_label">Retrait</legend>
					<div>
						<label class="form_label" for="rue">Rue :</label>
						<input class="form_input" type="text" name="rue" id="rue" value=${ user.rue }>
					</div>
					<div>
						<label class="form_label" for="codePostal">Code postal :</label>
						<input class="form_input" type="text" name="codePostal" id="codePostal" value=${ user.codePostal }>
					</div>
					<div>
						<label class="form_label" for="ville">Ville :</label>
						<input class="form_input" type="text" name="ville" id="ville" value=${ user.ville }>
					</div>
				</fieldset>
				<div class="article_form_buttons">
					<button type="submit" class="form_button">Enregistrer</button>
					<a class="form_button">Annuler</a>
				</div>	
			</div>
		</form>
	</div>
</div>
	<script>
		function clearMessage() {
			var msg = document.getElementById("msg");
			msg.style.display = "none";
		}
	</script>
<%@ include file="./Partials/Footer.jspf" %>