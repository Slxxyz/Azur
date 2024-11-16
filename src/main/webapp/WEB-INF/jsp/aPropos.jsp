<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="include/importTags.jsp"%>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="<spring:url value='/css/aPropos.css'/>">
</head>
<body>
<div class="page-about">
    <!-- Section gauche : Texte -->
    <div class="left-section">
        <div class="text-container">
            <h1>À PROPOS</h1>
            <div class="paragraph-container">
            <p>
                Nous sommes JeF, une jeune équipe de passionnés<br>de ping-pong dédiée à partager notre amour pour<br> ce sport rapide et intense.
            </p>
            <p>
                Ce qui nous unit, c’est une vision commune :
            </p>
            <p>
                Faire du ping-pong une expérience accessible,<br> divertissante et dynamique pour tous,<br>
                des débutants aux compétiteurs aguerris.
            </p>
            <p>
                Avec une énergie débordante et l'envie de toujours<br> aller plus loin, nous nous engageons à offrir des<br> contenus, des conseils, et des événements qui<br> inspirent et rassemblent la communauté du tennis<br> de table.
            </p>
            </div>
        </div>
    </div>

    <!-- Section droite : Image -->
    <div class="right-section">
        <img src="<spring:url value='/images/APropos/raquetteBalle.png'/>" alt="Raquette et balle">
    </div>
</div>

</body>


</html>
