<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="include/importTags.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link type="text/css" rel="stylesheet" href="<spring:url value='/css/welcome.css'/>">

<html>
<head>
    <title>${title}</title>
</head>
<body>
<div class="image-container">
    <img alt="English" src="<spring:url value='/images/Accueil/imageAccueil.png'/>">
</div>
<div class="collaborations">
    <h1 class="title"><spring:message code="Collaborations"/></h1>
    <div class="collaborations-img">
        <img alt="English" src="<spring:url value='/images/Accueil/collab1.png'/>">
        <img alt="English" src="<spring:url value='/images/Accueil/collab2.png'/>">
        <img alt="English" src="<spring:url value='/images/Accueil/collab3.png'/>">
    </div>
</div>
</body>
</html>
