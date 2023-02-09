<%@ include file="./Partials/Header.jspf" %>
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
	      <td>Pr�nom :</td>
		  <td>${ utilisateur.prenom }</td>
		</tr>
		<tr>
	      <td>Email : </td>
	      <td>${ utilisateur.email }</td>
		</tr>
		<tr>
	      <td>T�l�phone :</td>
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
	      <td>${ utilisateur.ville }</td> <!--  expressions language pour afficher les variables -->
		</tr> 
		<c:if test="${ utilisateur.utilisateurId == user.utilisateurId }"> <!-- balise JSTL si l'utilsateur ouvre son profil il voit ses cr�dits  --> 
			<tr>
		      <td>Cr�dit : </td>
		      <td>${ utilisateur.credit }</td>
			</tr> 
		</c:if>  
	</table>
	<c:if test="${ utilisateur.utilisateurId == user.utilisateurId }"> 
		<div class = "container bouton_profil">
			<a class="form_button" href="${pageContext.request.contextPath}/encheres/ModifierProfil?id=${user.utilisateurId}">Modifier</a>	
		</div>
	</c:if>
</div>
<%@ include file="./Partials/Footer.jspf" %>
