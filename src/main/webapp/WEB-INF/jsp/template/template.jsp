<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../include/importTags.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>
<head>
    <title>${title}</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value='/css/header.css'/>">

    <spring:url var="localeFr" value="">
        <spring:param name="locale" value="fr"/>
    </spring:url>

    <spring:url var="localeEn" value="">
        <spring:param name="locale" value="en"/>
    </spring:url>
</head>
<body>
<header>
    <div class="header-container">
        <div class="left-section">
            <img id="logo" src="<spring:url value='/images/AzurBlanc.png'/>" alt="Azur" height="50px">
            <nav class="nav-links">
                <a href="/accueil">Accueil</a>
                <a href="/catalogue">Catalogue</a>
                <a href="/apropos">À propos</a>
            </nav>
        </div>
        <div class="right-section">
            <a href="/login">Log in</a>
            <div class="language-section">
                <a href="${localeFr}">
                    <img alt="Français" src="<spring:url value='/images/drapeauFr.png'/>" height="20px">
                </a>
                <span>|</span>
                <a href="${localeEn}">
                    <img alt="English" src="<spring:url value='/images/drapeauEn.png'/>" height="20px">
                </a>
            </div>
            <a href="/panier" class="cart-icon">
                🛒
            </a>
        </div>
    </div>
</header>



<div>
    <tiles:insertAttribute name="main-content"/>
</div>
<div>
    Footer :
</div>
</body>
</html>