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
	<p>Bienvenue, ${conseillerSession.prenom} ${conseillerSession.nom} (${conseillerSession.login}).</p>
	<p>
		Afin d'utiliser les services ProxiBanqueSI, veuillez utiliser
		l'application <a
			href="https://addons.mozilla.org/en-US/firefox/addon/rester/">RESTer</a>
		de Firefox. Les services disponsibles sont accessibles ici.</p>
	<a href="Logout">Logout</a>
</body>
</html>