<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenue sur ProxiBanqueSI</title>
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<img src="img/banner.jpg" alt="">
	<p>Bienvenue, <b>${conseillerSession.prenom} ${conseillerSession.nom}</b>
		(${conseillerSession.login}).</p>
	<p>
		Afin d'utiliser les services ProxiBanqueSI, veuillez utiliser
		l'application <a
			href="https://addons.mozilla.org/en-US/firefox/addon/rester/">RESTer</a>
		de Firefox. Les services disponsibles sont accessibles <a
			href="services">ici</a>.
	</p>
	<p>
		Afin de faciliter vos tests, vous pouvez créer un client par défaut dans la base de données :
		<input type="button" value="Créer un client par défaut" onClick="doPost()">
	</p>
	<a href="Logout">Se déconnecter</a>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="restClient.js"></script>
</body>
</html>