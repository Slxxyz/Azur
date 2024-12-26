<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/importTags.jsp"%>
<html>
<head>
    <title>${title}</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value='/css/inscription.css'/>">
</head>
<body>
<div class="page-inscription">
    <div class="left-section">
        <h1>TU ES DE RETOUR ?</h1>
        <p>Pour une meilleure cohésion<br>d'équipe, connecte-toi avec tes<br>informations personnelles.</p>
        <a href="<spring:url value='/connexion' />">Connexion</a>
    </div>
    <div class="right-section">
        <h1>INSCRIPTION</h1>
        <form action="<spring:url value='/inscription' />" method="post">
            <label for="nom">Nom</label>
            <input type="text" id="nom" name="nom" placeholder="Entrez votre nom" required>

            <label for="prenom">Prénom</label>
            <input type="text" id="prenom" name="prenom" placeholder="Entrez votre prénom" required>

            <label for="telephone">Numéro de téléphone</label>
            <input type="tel" id="telephone" name="telephone" placeholder="Entrez votre numéro de téléphone">

            <label for="email">Email</label>
            <input type="email" id="email" name="email" placeholder="Entrez votre adresse email" required>

            <label for="password">Mot de passe</label>
            <input type="password" id="password" name="password" placeholder="Entrez votre mot de passe" required>

            <button type="submit">Inscription</button>
        </form>
    </div>
</div>
</body>
</html>
