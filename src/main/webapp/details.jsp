<!-- 
 *  Devoir AJAX et Java EE, par Eric Boivin
 *  Cette page est un fragment et doît être populée après un appel
 *  du servlet avec le Bean Image approprié. Elle présente la fiche de 
 *  détails d'une Image pour en faire l'aperçu ou la commande. 
 /-->
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="ca.etsmtl.gti525.devoir1.Image"%>
<%
Image image = (Image)request.getAttribute("image");
String chemin = request.getAttribute("dossierVignettes")+"/"+image.getChemin();
%>
<tr>
	<td align="center">
		<h2><%=image.getTitre()%></h2>
	</td>
</tr>
<tr>
	<td>
		<img src="<%=chemin%>" />
	</td>
</tr>
 <tr>
	<td align="center">
		<h2><a href="watermark/<%=chemin%>" target="_blank">Aperçu de cette image</a></h2>
		<br/>
		<form name="payer" action="watermark/<%=chemin%>" method="post">
			Carte de crédit <input type="text" name="carteCredit" />
			<br/>
		   <input type="submit" value="Acheter cette image" />
		</form>
	</td>
</tr>