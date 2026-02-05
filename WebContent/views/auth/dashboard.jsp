<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Tableau de bord</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
    </style>
</head>
<body>
    <h1>Tableau de bord</h1>

    <c:choose>
        <c:when test="${not empty user}">
            <p>Bienvenue, <strong>${user.username}</strong> !</p>
            <p>Email : ${user.email}</p>
            <p>Roles : ${user.roles}</p>
            <form action="${pageContext.request.contextPath}/auth/logout" method="post">
                <button type="submit">Se déconnecter</button>
            </form>
        </c:when>
        <c:otherwise>
            <p>Utilisateur non connecté.</p>
            <p><a href="${pageContext.request.contextPath}/auth/login">Se connecter</a></p>
        </c:otherwise>
    </c:choose>

    <p><a href="${pageContext.request.contextPath}/">Accueil</a></p>
</body>
</html>
