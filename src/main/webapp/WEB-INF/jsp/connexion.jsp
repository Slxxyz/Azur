<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/importTags.jsp"%>
<html>
<head>
    <title>${title}</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value='/css/connexion.css'/>">
</head>
<body>
<div class="page-connexion">

    <div class="left-section">
        <h1>CONNEXION</h1>
        <form action="<spring:url value='/connexion' />" method="post">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" placeholder="Entrez votre adresse email" required>

            <label for="password">Mot de passe</label>
            <input type="password" id="password" name="password" placeholder="Entrez votre mot de passe" required>

            <button type="submit">Connexion</button>
        </form>
    </div>

    <div class="right-section">
        <h1>SALUT TOI !</h1>
        <p>Entre tes informations personnelles et<br> commence ta journée avec nous !</p>
        <a href="<spring:url value='/inscription' />">Inscription</a>
    </div>
</div>
</body>
</html>
