<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="include/importTags.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Bienvenue mon gâté</title>
</head>
<body>
<div>
    <h1>Welcome</h1>
    <p>Age : ${age}</p>
    <form:form id="form" method="POST" action="hello/send" modelAttribute="magicKeyForm">

        <form:label path="magicKey">
            <spring:message code="magicKeyLabel"/>
        </form:label>

        <form:input path="magicKey"/>
        <form:button>
            <spring:message code="magicKeySendButton"/>
        </form:button>

    </form:form>
</div>
</body>
</html>
