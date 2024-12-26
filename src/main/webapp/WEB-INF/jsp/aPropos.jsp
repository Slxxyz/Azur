<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="include/importTags.jsp"%>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="<spring:url value='/css/aPropos.css'/>">
    <title>${title}</title>
</head>
<body>
<div class="page-about">
    <div class="left-section">
        <div class="text-container">
            <h1><spring:message code="AboutUs"/></h1>
            <div class="paragraph-container">
            <spring:message code="Description"/>
            </div>
        </div>
    </div>
    <div class="right-section">
        <img src="<spring:url value='/images/APropos/raquetteBalle.png'/>" alt="Raquette et balle">
    </div>
</div>

</body>


</html>
