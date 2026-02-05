<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Connexion</title>
	<style>
		body { font-family: Arial, sans-serif; padding: 20px; }
		form { max-width: 320px; }
		label { display: block; margin-bottom: 8px; }
		input[type="text"], input[type="password"] { width: 100%; padding: 6px; }
		.error { color: red; margin-bottom: 12px; }
	</style>
</head>
<body>
	<h1>Connexion</h1>

	<!-- Affiche le message d'erreur si fourni par le contrôleur -->
	<p class="error">${error}</p>

	<form action="${pageContext.request.contextPath}/auth/login" method="post">
		<label>Nom d'utilisateur
			<input type="text" name="username" value="${username}" />
		</label>

		<label>Mot de passe
			<input type="password" name="password" />
		</label>

		<button type="submit">Se connecter</button>
	</form>

	<p><a href="${pageContext.request.contextPath}/">Retour à l'accueil</a></p>
</body>
</html>
