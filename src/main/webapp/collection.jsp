x#<!-- 
 *  Devoir AJAX et Java EE, par Eric Boivin
 *  Cette page contient une liste déroulante d'images populée
 *  par un tableau de titres reçu par le servlet. Lors de la 
 *  sélection d'un article, une requête AJAX est faite au servlet
 *  en arrière-plan, permettant de recevoir la page des détails
 *  et de l'afficher dans le conteneur prévu à cet effet.
 /-->
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="ca.etsmtl.gti525.devoir1.Image"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%
	List<Image> listeImages = (ArrayList<Image>)request.getAttribute("collection");
%>
<!DOCTYPE html>
<html>
<head>
<title>Démo AJAX - J2EE</title>

<script>
	function appelAjax(strURL) {

		var xmlHttpReq = false;
		// Création du conteneur XML pour Mozilla/Safari
		if (window.XMLHttpRequest) {

			xmlHttpReq = new XMLHttpRequest();
		}
		// Création du conteneur XML pour IE
		else if (window.ActiveXObject) {
			xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
		}
		var url = strURL
				+ "?listeImages="
				+ selection.listeImages.options[selection.listeImages.selectedIndex].value;
		xmlHttpReq.open('GET', url, true);
		xmlHttpReq.onreadystatechange = function() {
			if (xmlHttpReq.readyState == 4) {
				updatepage(xmlHttpReq.responseText);
			}
		}
		xmlHttpReq.send(url);
	}

	function updatepage(str) {
		document.getElementById("result").innerHTML = str;
	}
</script>

</head>
<body>
	<form name="selection">
		<select name="listeImages"
			onchange='JavaScript:appelAjax("AjaxServlet")'>
			<option value=""></option>
			<%
				for (int i = 0; i < listeImages.size(); i++) {
						Image image = listeImages.get(i);
			%>
					<option value="<%=image.getId()%>"><%=image.getTitre()%></option>
			<%
				}
			%>
		</select>
	</form>
	<br />
	<div id="result"></div>
</body>
</html>
