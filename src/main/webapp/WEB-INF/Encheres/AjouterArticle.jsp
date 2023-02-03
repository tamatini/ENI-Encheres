<%@ include file="./Partials/Header.jspf" %>
	<div class="container">
		<form class="create_form" method="post">
			<div>
				<img alt="" src="" >
			</div>
			<div>
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
						<option value="1">Informatique</option>
						<option value="2">Informatique</option>
						<option value="3">Informatique</option>
						<option value="4">Informatique</option>
					</select>
				</div>
				<div class="form_create_section">
					<label class="form_label" for="imageArticle">Photo de l'article</label>
					<input  type="file" value="UPLOADER" name="image" id="imageArticle">
				</div>
				<div class="form_create_section">
					<label class="form_label" for="prixVente">Mise à prix :</label>
					<input class="form_input" type="number" name="prixVente" id="prixVente">
				</div>
				<div class="form_create_section">
					<label class="form_label" for="dateDebut">Début de l'enchère :</label>
					<input class="form_input" type="date" name="dateDebut" id="dateDebut">
				</div>
				<div class="form_create_section">
					<label class="form_label" for="dateFin">Fin de l'enchère :</label>
					<input class="form_input" type="date" name="dateFin" id="dateFin">
				</div>
				<fieldset>
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
				<div>
					<button type="submit" class="form_button">Enregistrer</button>
					<a class="form_button">Annuler</a>
				</div>	
			</div>
		</form>
	</div>
<%@ include file="./Partials/Footer.jspf" %>