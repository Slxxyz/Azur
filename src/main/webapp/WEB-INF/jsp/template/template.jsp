<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../include/importTags.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>
<head>
    <title>${title}</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value='/css/template.css'/>">

    <spring:url var="localeFr" value="">
        <spring:param name="locale" value="fr"/>
    </spring:url>

    <spring:url var="localeEn" value="">
        <spring:param name="locale" value="en"/>
    </spring:url>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const catalogueLink = document.getElementById('catalogueLink');
            const dropdownMenu = document.getElementById('dropdownMenu');

            if (catalogueLink && dropdownMenu) {
                // Ajouter un événement de clic au lien "Catalogue"
                catalogueLink.addEventListener('click', function () {
                    dropdownMenu.classList.toggle('show');
                });

                // Ajouter un événement pour fermer le menu si l'utilisateur clique en dehors
                window.addEventListener('click', function (e) {
                    if (!catalogueLink.contains(e.target) && !dropdownMenu.contains(e.target)) {
                        dropdownMenu.classList.remove('show');
                    }
                });
            }
        });

    </script>

</head>
<body>
<header>
    <div class="header-container">
        <div class="left-section">
            <img id="logo" src="<spring:url value='/images/Template/AzurBlanc.png'/>" alt="Azur" height="50px">
            <nav class="nav-links">
                <a href="<spring:url value='/azur' />">Accueil</a>

                <a href="javascript:void(0);" id="catalogueLink">Catalogue</a>

                <div id="dropdownMenu">
                    <a href="/category1">Bois</a>
                    <a href="/category2">Mousse</a>
                    <a href="/category3">Balle</a>
                    <a href="/category3">Table</a>
                    <a href="/category3">Filet</a>
                </div>

                <a href="<spring:url value='/a-propos' />">À propos</a>
            </nav>
        </div>
        <div class="right-section">
            <a href="/login">Log in</a>
            <div class="language-section">
                <a href="${localeFr}">
                    <img alt="Français" src="<spring:url value='/images/Template/drapeauFr.png'/>" height="20px">
                </a>
                <span>|</span>
                <a href="${localeEn}">
                    <img alt="English" src="<spring:url value='/images/Template/drapeauEn.png'/>" height="20px">
                </a>
            </div>
            <a href="/panier" class="cart-icon">🛒</a>
        </div>
    </div>
</header>

<div>
    <tiles:insertAttribute name="main-content"/>
</div>

<footer>
    <div class="footer-container">
        <div class="footer-links">
            <a href="/accueil">Accueil</a>
            <a href="/catalogue">Catalogue</a>
            <a href="/apropos">À propos</a>
        </div>
        <div class="copyright">
            <p>Copyright © 2024 Azur Inc. Tous droits réservés.</p>
        </div>
    </div>
</footer>

</body>
</html>