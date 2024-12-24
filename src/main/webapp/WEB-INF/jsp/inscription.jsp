<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/importTags.jsp"%>
<html>
<head>
    <title>${title}</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value='/css/inscription.css'/>">
    <script src="<spring:url value='/js/validationFormInscription.js'/>"></script>
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

        <c:if test="${not empty errorEmail}">
            <div class="error-message">${errorEmail}</div>
        </c:if>
        <c:if test="${not empty errorTelephone}">
            <div class="error-message">${errorTelephone}</div>
        </c:if>
        <c:if test="${not empty errorUsername}">
            <div class="error-message">${errorUsername}</div>
        </c:if>
        <form:form id="inscriptionForm" action="${pageContext.request.contextPath}/inscription/sendInscription" method="post" modelAttribute="customer">
            <h2>Informations Personnelles</h2>

            <form:label path="username">Nom d'utilisateur</form:label>
            <form:input path="username" placeholder="Entrez votre nom d'utilisateur" required="true" id="username"/>
            <form:errors path="username" cssClass="error-message" />
            <div id="error-username" class="error-message"></div>

            <form:label path="firstName">Prénom</form:label>
            <form:input path="firstName" placeholder="Entrez votre prénom" required="true" id="firstName"/>
            <form:errors path="firstName" cssClass="error-message" />
            <div id="error-firstName" class="error-message"></div>

            <form:label path="lastName">Nom</form:label>
            <form:input path="lastName" placeholder="Entrez votre nom" required="true" id="lastName"/>
            <form:errors path="lastName" cssClass="error-message" />
            <div id="error-lastName" class="error-message"></div>

            <form:label path="telNumber">Numéro de téléphone</form:label>
            <form:input path="telNumber" type="tel" placeholder="Entrez votre numéro de téléphone" id="telephone"/>
            <form:errors path="telNumber" cssClass="error-message" />
            <div id="error-telephone" class="error-message">${errorTelephone}</div>

            <form:label path="mailAddress">Email</form:label>
            <form:input path="mailAddress" type="email" placeholder="Entrez votre adresse email" required="true" id="email"/>
            <form:errors path="mailAddress" cssClass="error-message" />
            <div id="error-email" class="error-message">${errorEmail}</div>

            <form:label path="userPassword">Mot de passe</form:label>
            <form:input path="userPassword" type="password" placeholder="Entrez votre mot de passe" required="true" id="password"/>
            <form:errors path="userPassword" cssClass="error-message" />
            <div id="error-password" class="error-message"></div>

            <h2>Adresse de Livraison</h2>
            <div class="form-group">
                <div class="input-wrapper">
                    <form:label path="location.street">Rue</form:label>
                    <form:input path="location.street" placeholder="Entrez votre rue" required="true" id="rue"/>
                    <form:errors path="location.street" cssClass="error-message" />
                    <div id="error-rue" class="error-message"></div>
                </div>

                <div class="input-wrapper">
                    <form:label path="location.houseNumber">Numéro</form:label>
                    <form:input path="location.houseNumber" placeholder="Numéro" required="true" id="numero"/>
                    <form:errors path="location.houseNumber" cssClass="error-message" />
                    <div id="error-numero" class="error-message"></div>
                </div>

                <div class="input-wrapper">
                    <form:label path="location.letterBox">Lettre (facultatif)</form:label>
                    <form:input path="location.letterBox" placeholder="A, B, etc." id="lettre"/>
                    <form:errors path="location.letterBox" cssClass="error-message" />
                    <div id="error-lettre" class="error-message"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="input-wrapper">
                    <form:label path="location.postalCode">Code Postal</form:label>
                    <form:input path="location.postalCode" placeholder="Code postal" required="true" id="code-postal"/>
                    <form:errors path="location.postalCode" cssClass="error-message" />
                    <div id="error-code-postal" class="error-message"></div>
                </div>

                <div class="input-wrapper">
                    <form:label path="location.location">Ville</form:label>
                    <form:input path="location.location" placeholder="Entrez votre ville" required="true" id="ville"/>
                    <form:errors path="location.location" cssClass="error-message" />
                    <div id="error-ville" class="error-message"></div>
                </div>
            </div>

            <form:label path="location.country">Pays</form:label>
            <form:input path="location.country" placeholder="Entrez votre pays" required="true" id="pays"/>
            <form:errors path="location.country" cssClass="error-message" />
            <div id="error-pays" class="error-message"></div>

            <button type="submit">Inscription</button>
        </form:form>

    </div>
</div>
</body>
</html>