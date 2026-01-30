<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Accès interdit</title>
    <style>
        .error-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            border: 2px solid #ff4444;
            border-radius: 10px;
            background: #fff0f0;
        }
        .error-code {
            font-size: 48px;
            color: #ff4444;
            font-weight: bold;
        }
        .error-message {
            font-size: 18px;
            margin: 20px 0;
        }
        .user-info {
            background: #f0f0f0;
            padding: 10px;
            border-radius: 5px;
            margin: 15px 0;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <div class="error-code">403</div>
        <h1>Accès interdit</h1>
        
        <div class="error-message">
            <p><strong>Message :</strong> ${errorMessage}</p>
        </div>
        
        <% 
            Object principal = session.getAttribute("APP_USER_PRINCIPAL");
            if (principal != null) { 
        %>
            <div class="user-info">
                <p><strong>Utilisateur connecté :</strong> 
                    <%= principal.toString() %>
                </p>
            </div>
        <% } else { %>
            <div class="user-info">
                <p><strong>Statut :</strong> Non authentifié</p>
            </div>
        <% } %>
        
        <div style="margin-top: 30px;">
            <a href="${pageContext.request.contextPath}/auth/login">Page de connexion</a> | 
            <a href="${pageContext.request.contextPath}/public/welcome">Page publique</a> | 
            <a href="javascript:history.back()">Retour</a>
        </div>
    </div>
</body>
</html>