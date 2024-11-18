<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/importTags.jsp"%>
<html>
<head>
    <title>Article</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value='/css/article.css'/>">
    <script src="<spring:url value='/js/article.js'/>"></script>
</head>
<body>
<div class="page-article">

    <div class="divImage">
        <img alt="article1" src="<spring:url value='/images/Articles/article1.png'/>">
    </div>

    <!-- Section Contenu -->
    <div class="content">
        <h1>Article 1</h1>

        <h3 class="stock">EN STOCK</h3>
        <div class="details">
        <h3>Détails</h3>
        <p>
            Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore
            et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.
            Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.
        </p>
        </div>
        <div class="name-quantity">
            <h2>Article 1</h2>
            <div class="quantity-controls">
                <button class="decrement">-</button>
                <input type="text" id="quantity" value="1">
                <button class="increment">+</button>
            </div>
        </div>
        <h2>349.99€</h2>
        <button class="add-to-cart">Ajouter au panier</button>
    </div>
</div>
</body>
</html>
