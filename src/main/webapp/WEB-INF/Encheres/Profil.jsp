<%@ include file="./Partials/Header.jsp" %>
<link rel="stylesheet" href="../Public/Styles/main.css">
<div class="container">
	<table class="profil_table">
		<tr>
	      <td>Pseudo :</td>
		  <td>${ utilisateur.pseudo }</td>
		</tr>
		<tr>
	      <td>Nom : </td>
	      <td>${ utilisateur.nom }</td>
		</tr>
		<tr>
	      <td>Prénom :</td>
		  <td>${ utilisateur.prenom }</td>
		</tr>
		<tr>
	      <td>Email : </td>
	      <td>${ utilisateur.email }</td>
		</tr>
		<tr>
	      <td>Téléphone :</td>
		  <td>${ utilisateur.telephone }</td>
		</tr>
		<tr>
	      <td>Rue : </td>
	      <td>${ utilisateur.rue }</td>
		</tr>
		<tr>
	      <td>Code Postal :</td>
		  <td>${ utilisateur.codePostal }</td>
		</tr>
		<tr>
	      <td>Ville : </td>
	      <td>${ utilisateur.ville }</td>
		</tr>    
	</table>		
</div>	
<%@ include file="./Partials/Footer.jsp" %>