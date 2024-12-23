<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/importTags.jsp"%>

<head>
  <title>${title}</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="<spring:url value='/css/productsByCategory.css'/>">
</head>

<body>
  <div class="container my-5">
    <h2 class="text-center mb-4">${categoryName}</h2>
    <div class="row justify-content-center">
      <c:forEach items="${products}" var="product">
        <div class="col-12 col-sm-6 col-md-4 col-lg-3 mb-4">
          <div class="card h-100">
            <img
              src="<spring:url value='${product.getImagePath()}'/>"
              class="card-img-top"
              alt="${product.getLabelProduct()}"
              style="object-fit: cover; height: 200px;">
            <div class="card-body d-flex flex-column">
              <h5 class="card-title">${product.getLabelProduct()}</h5>
              <p class="card-text mb-4">${product.getUnitPriceExcludingTax()}€</p>
              <a href="<spring:url value='/product/details?productID=${product.getProductID()}&locale=${language}'/>" class="btn btn-primary mt-auto">Détails</a>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>
</body>