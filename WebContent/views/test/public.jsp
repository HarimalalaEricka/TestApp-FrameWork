<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Page Publique</title>
</head>
<body>
    <h1>${message}</h1>
    <p>Cette page est accessible à tout le monde.</p>
    
    <h2>Liens de test :</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/auth/login">Connexion</a></li>
        <li><a href="${pageContext.request.contextPath}/auth/profile">Profil (authentifié requis)</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/dashboard">Admin (ROLE_ADMIN requis)</a></li>
        <li><a href="${pageContext.request.contextPath}/posts/delete/123">Supprimer post (permission DELETE_POSTS)</a></li>
    </ul>
    
    <p><a href="${pageContext.request.contextPath}/public/welcome">Retour à l'accueil</a></p>
</body>
</html>