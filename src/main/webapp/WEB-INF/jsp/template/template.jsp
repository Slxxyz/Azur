<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../include/importTags.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
            const cart = document.getElementsByClassName('cart-icon')[0];

            if (catalogueLink && dropdownMenu) {
                // Ajouter un événement de clic au lien "Catalogue"
                catalogueLink.addEventListener('click', function () {
                    dropdownMenu.classList.toggle('show');
                    fetchCategories();
                });

                // Ajouter un événement pour fermer le menu si l'utilisateur clique en dehors
                window.addEventListener('click', function (e) {
                    if (!catalogueLink.contains(e.target) && !dropdownMenu.contains(e.target)) {
                        dropdownMenu.classList.remove('show');
                    }
                });
            }

            function fetchCategories() {
                const currentLocale = new URLSearchParams(window.location.search).get('locale') || 'fr';
                const requestUrl = "/firstSpring/categories/fragment?locale=" + currentLocale;
                fetch(requestUrl)
                    .then(response => response.text())
                    .then(data => {
                        dropdownMenu.innerHTML = data;
                    })
                    .catch(error => console.error('Error fetching categories:', error));
            }

            // Update language links with current URL parameters
            const currentParams = new URLSearchParams(window.location.search);
            const localeFrLink = document.getElementById('localeFrLink');
            const localeEnLink = document.getElementById('localeEnLink');

            if (localeFrLink && localeEnLink) {
                currentParams.set('locale', 'fr');
                localeFrLink.href = window.location.pathname + '?' + currentParams.toString();

                currentParams.set('locale', 'en');
                localeEnLink.href = window.location.pathname + '?' + currentParams.toString();
            }

            if (cart) {
                cart.addEventListener('click', function () {
                    // Envoyer le panier au serveur
                    const cart = JSON.parse(localStorage.getItem('guestCart')) || {};

                    fetch('/firstSpring/panier/sync', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(cart),
                    })
                    .then((data) => {
                        console.log('Données :', data);
                        window.location.href = data.url;
                    })
                    .catch((error) => {
                        console.error('Erreur :', error);
                    });

                });
            }

        });
    </script>

</head>
<body>
<c:if test="${showHeader}">

    <header>
        <div class="header-container">
            <div class="left-section">
                <img id="logo" src="<spring:url value='/images/Template/AzurBlanc.png'/>" alt="Azur" height="50px">
                <nav class="nav-links">
                    <a href="<spring:url value='/azur' />"><spring:message code="Home"/></a>
                    <a href="javascript:void(0);" id="catalogueLink"><spring:message code="Catalog"/></a>
                    <div id="dropdownMenu">

                    </div>
                    <a href="<spring:url value='/a-propos' />"><spring:message code="AboutUs"/></a>
                </nav>
            </div>
            <div class="right-section">
                <a href="<spring:url value='/connexion' />"><spring:message code="LogIn"/></a>
                <div class="language-section">
                    <a id="localeFrLink" href="#">
                        <img alt="Français" src="<spring:url value='/images/Template/drapeauFr.png'/>" height="20px">
                    </a>
                    <span>|</span>
                    <a id="localeEnLink" href="#">
                        <img alt="English" src="<spring:url value='/images/Template/drapeauEn.png'/>" height="20px">
                    </a>
                </div>
                <a  class="cart-icon">🛒</a>
            </div>
        </div>
    </header>
</c:if>

<main class="content">
    <tiles:insertAttribute name="main-content"/>
</main>

<c:if test="${showFooter}">
    <footer>
        <div class="footer-container">
            <div class="footer-links">
                <a href="/accueil"><spring:message code="Home"/></a>
                <a href="/catalogue"><spring:message code="Catalog"/></a>
                <a href="/apropos"><spring:message code="AboutUs"/></a>
            </div>
            <div class="copyright">
                <p><spring:message code="Copyright"/></p>
            </div>
        </div>
    </footer>
</c:if>

</body>
</html>