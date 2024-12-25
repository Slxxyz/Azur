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
        <h1><spring:message code="AreYouBack"/></h1>
        <p><spring:message code="MessageConnexion"/></p>
        <a href="<spring:url value='/connexion' />"><spring:message code="Connexion"/></a>
    </div>
    <div class="right-section">

        <h1><spring:message code="Inscription"/></h1>

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
            <h2><spring:message code="PersonalInfo"/></h2>

            <form:label path="username"><spring:message code="UserName"/></form:label>
            <spring:message code="PlaceholderUsername" var="placeholderUsername"/>
            <form:input path="username" placeholder="${placeholderUsername}" required="true" id="username"/>
            <form:errors path="username" cssClass="error-message" />
            <div id="error-username" class="error-message"></div>

            <form:label path="firstName"><spring:message code="FirstName"/></form:label>
            <spring:message code="PlaceholderFirstName" var="placeholderFirstName"/>
            <form:input path="firstName" placeholder="${placeholderFirstName}" required="true" id="firstName"/>
            <form:errors path="firstName" cssClass="error-message" />
            <div id="error-firstName" class="error-message"></div>

            <form:label path="lastName"><spring:message code="LastName"/></form:label>
            <spring:message code="PlaceholderLastName" var="placeholderLastName"/>
            <form:input path="lastName" placeholder="${placeholderLastName}" required="true" id="lastName"/>
            <form:errors path="lastName" cssClass="error-message" />
            <div id="error-lastName" class="error-message"></div>

            <form:label path="telNumber"><spring:message code="Telephone"/></form:label>
            <spring:message code="PlaceholderTelephone" var="placeholderTelephone"/>
            <form:input path="telNumber" type="tel" placeholder="${placeholderTelephone}" id="telephone"/>
            <form:errors path="telNumber" cssClass="error-message" />
            <div id="error-telephone" class="error-message">${errorTelephone}</div>

            <form:label path="mailAddress"><spring:message code="Email"/></form:label>
            <spring:message code="PlaceholderEmail" var="placeholderEmail"/>
            <form:input path="mailAddress" type="email" placeholder="${placeholderEmail}" required="true" id="email"/>
            <form:errors path="mailAddress" cssClass="error-message" />
            <div id="error-email" class="error-message">${errorEmail}</div>

            <form:label path="userPassword"><spring:message code="Password"/></form:label>
            <spring:message code="PlaceholderPassword" var="placeholderPassword"/>
            <form:input path="userPassword" type="password" placeholder="${placeholderPassword}" required="true" id="password"/>
            <form:errors path="userPassword" cssClass="error-message" />
            <div id="error-password" class="error-message"></div>

            <label for="confirm-password"><spring:message code="ConfirmPassword"/></label>
            <spring:message code="PlaceholderPassword" var="placeholderConfirmPassword"/>
            <input type="password" placeholder="${placeholderConfirmPassword}" id="confirm-password"/>
            <div id="error-confirm-password" class="error-message"></div>

            <h2><spring:message code="DeliveryAddress"/></h2>
            <div class="form-group">
                <div class="input-wrapper">
                    <form:label path="location.street"><spring:message code="Street"/></form:label>
                    <spring:message code="PlaceholderStreet" var="placeholderStreet"/>
                    <form:input path="location.street" placeholder="${placeholderStreet}" required="true" id="rue"/>
                    <form:errors path="location.street" cssClass="error-message" />
                    <div id="error-rue" class="error-message"></div>
                </div>

                <div class="input-wrapper">
                    <form:label path="location.houseNumber"><spring:message code="Number"/></form:label>
                    <spring:message code="PlaceholderNumber" var="placeholderNumber"/>
                    <form:input path="location.houseNumber" placeholder="${placeholderNumber}" required="true" id="numero"/>
                    <form:errors path="location.houseNumber" cssClass="error-message" />
                    <div id="error-numero" class="error-message"></div>
                </div>

                <div class="input-wrapper">
                    <form:label path="location.letterBox"><spring:message code="Letter"/></form:label>
                    <spring:message code="PlaceholderLetter" var="placeholderLetter"/>
                    <form:input path="location.letterBox" placeholder="${placeholderLetter}" id="lettre"/>
                    <form:errors path="location.letterBox" cssClass="error-message" />
                    <div id="error-lettre" class="error-message"></div>
                </div>
            </div>

            <div class="form-group">
                <div class="input-wrapper">
                    <form:label path="location.postalCode"><spring:message code="PostalCode"/></form:label>
                    <spring:message code="PlaceholderPostalCode" var="placeholderPostalCode"/>
                    <form:input path="location.postalCode" placeholder="${placeholderPostalCode}" required="true" id="code-postal"/>
                    <form:errors path="location.postalCode" cssClass="error-message" />
                    <div id="error-code-postal" class="error-message"></div>
                </div>

                <div class="input-wrapper">
                    <form:label path="location.location"><spring:message code="City"/></form:label>
                    <spring:message code="PlaceholderCity" var="placeholderCity"/>
                    <form:input path="location.location" placeholder="${placeholderCity}" required="true" id="ville"/>
                    <form:errors path="location.location" cssClass="error-message" />
                    <div id="error-ville" class="error-message"></div>
                </div>
            </div>

            <form:label path="location.country"><spring:message code="Country"/></form:label>
            <spring:message code="PlaceholderCountry" var="placeholderCountry"/>
            <form:input path="location.country" placeholder="${placeholderCountry}" required="true" id="pays"/>
            <form:errors path="location.country" cssClass="error-message" />
            <div id="error-pays" class="error-message"></div>

            <button type="submit"><spring:message code="Inscription"/></button>
        </form:form>

    </div>
</div>
</body>
</html>
