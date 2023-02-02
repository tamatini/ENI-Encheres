<%@ include file="./Partials/Header.jspf" %>
	<div class="container">
		<form method="post">
			<div>
				<label for="nomArticle">Article : </label>
				<input name="nomArticle" id="nomArticle" type="text" >
			</div>
			<div>
				<label>Description :</label>
				<textarea rows="" cols=""></textarea>
			</div>
			<div>
				<label>Catégorie</label>
				<select>
					<option value="">--Sélectionner une catégories--</option>
					<option value="informatique">Informatique</option>
					<option value="informatique">Informatique</option>
					<option value="informatique">Informatique</option>
					<option value="informatique">Informatique</option>
				</select>
			</div>
			<div>
				<label for="imageArticle">Photo de l'article</label>
				<input type="file" value="UPLOADER" name="image" id="imageArticle">
			</div>
			<div>
				<label for="prixVente">Mise à prix :</label>
				<input type="number">
			</div>
			<div>
				<label for="dateDebut">Début de l'enchère :</label>
				<input type="date" name="dateDebut" id="dateDebut">
			</div>
			<div>
				<label for="dateFin">Fin de l'enchère :</label>
				<input type="date" name="dateFin" id="dateFin">
			</div>
			<fieldset>
				<legend>Retrait</legend>
				<div>
					<label for="rue">Rue :</label>
					<input type="text" name="rue" id="rue" value=${ utilisateur.rue }>
				</div>
				<div>
					<label for="codePostal">Code postal :</label>
					<input type="text" name="codePostal" id="codePostal" value=${ utilisateur.codePostal }>
				</div>
				<div>
					<label for="ville">Ville :</label>
					<input type="text" name="ville" id="ville" value=${ utilisateur.ville }>
				</div>
			</fieldset>
			<div>
				<button type="submit" class="form_button">Enregistrer</button>
				<a class="form_button">Annuler</a>
			</div>
		</form>
	</div>
<%@ include file="./Partials/Footer.jspf" %>