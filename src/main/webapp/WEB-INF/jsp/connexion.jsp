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
        <c:if test="${not empty loginError}">
            <div class="error-message">${loginError}</div>
        </c:if>
        <form:form action="${pageContext.request.contextPath}/connexion/login" method="post" modelAttribute="customer">
            <form:label path="mailAddress">Email</form:label>
            <form:input path="mailAddress" type="email" placeholder="Entrez votre adresse email" required="true" id="email" name="email"/>
            <form:errors path="mailAddress" cssClass="error-message" />

            <form:label path="userPassword">Mot de passe</form:label>
            <form:input path="userPassword" type="password" placeholder="Entrez votre mot de passe" required="true" id="password" name="password"/>
            <form:errors path="userPassword" cssClass="error-message" />

            <button type="submit">Connexion</button>
        </form:form>
    </div>

    <div class="right-section">
        <h1>SALUT TOI !</h1>
        <p>Entre tes informations personnelles et<br> commence ta journée avec nous !</p>
        <a href="<spring:url value='/inscription' />">Inscription</a>
    </div>
</div>
</body>
</html>