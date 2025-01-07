<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/importTags.jsp"%>

<head>
  <title>${title}</title>
  <link rel="stylesheet" href="<spring:url value='/css/catalogue.css'/>">
</head>

<body>
  <div class="page-catalogue">
    <div class="container my-5">
      <h2 class="text-center mb-4">${categoryName}</h2>
      <div class="product-grid">
        <c:forEach items="${products}" var="product">
          <div class="product-card">
            <img
              src="<spring:url value='${product.getImagePath()}'/>"
              alt="${product.getLabelProduct()}"
              class="product-image">
            <div class="product-details">
              <h5 class="product-title">${product.getLabelProduct()}</h5>
              <p class="product-price">${product.getUnitPriceExcludingTax()}€</p>
              <a href="<spring:url value='/product/details?productID=${product.getProductID()}&locale=${language}'/>" class="btn btn-primary"><spring:message code="ViewMore"/></a>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
  </div>
</body>
