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
        <div class="title-container">
            <spring:message code="Connexion"/>
        </div>
        <c:if test="${not empty param.error}">
            <div class="error-message"><spring:message code="ErrorConnection"/></div>
        </c:if>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <label for="usernameOrEmail">
                <spring:message code="UserNameOrEmail"/>
            </label>
            <input id="usernameOrEmail" name="username" placeholder="<spring:message code="EnterUserNameOrEmail"/>" required />

            <label for="password">
                <spring:message code="Password"/>
            </label>
            <input type="password" id="password" name="password" placeholder="<spring:message code="EnterPassword"/>" required />

            <button type="submit">
                <spring:message code="SeConnecter"/>
            </button>
        </form>
    </div>

    <div class="right-section">
        <div class="title-container">
            <spring:message code="SalutToi"/>
        </div>
        <div class="message-container">
            <spring:message code="MessageInscription"/>
        </div>
        <a href="<spring:url value='/inscription' />">
            <spring:message code="Inscription"/>
        </a>
    </div>
</div>
</body>
</html>