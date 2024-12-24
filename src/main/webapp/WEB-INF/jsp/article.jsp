<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/importTags.jsp"%>
<html>
<head>
    <title>${title}</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value='/css/article.css'/>">
    <script src="<spring:url value='/js/article.js'/>"></script>
</head>
<body>
<div class="page-article">

    <div class="divImage">
        <img alt="article1" src="<spring:url value='${product.getImagePath()}'/>">
    </div>

    <!-- Section Contenu -->
    <div class="content">
        <h1>${product.getLabelProduct()}</h1>

        <h3 class="stock"><spring:message code="Stock"/></h3>
        <div class="details">
        <h3><spring:message code="Details"/></h3>
        <p>
            <c:choose>
                <c:when test="${language == 'fr'}">
                    ${product.getDescriptionFR()}
                </c:when>
                <c:otherwise>
                    ${product.getDescriptionEN()}
                </c:otherwise>
            </c:choose>
        </p>
        </div>
        <div class="name-quantity">
            <h2><spring:message code="Quantity"/></h2>
            <div class="quantity-controls">
                <button class="decrement">-</button>
                <input type="text" id="quantity" value="1">
                <button class="increment">+</button>
            </div>
        </div>
        <h2>${product.getUnitPriceExcludingTax()}€</h2>
        <button class="add-to-cart"><spring:message code="AddToCard"/></button>
    </div>
</div>
</body>
</html>
