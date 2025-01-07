<!-- src/main/webapp/WEB-INF/jsp/panier.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/importTags.jsp"%>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="<spring:url value='/css/panier.css'/>">
    <script src="<spring:url value='/js/shoppingCart.js'/>"></script>
</head>

<body>
<div class="wrapper">
    <div class="navbar">
        <!-- Navbar content -->
    </div>
    <div class="content">
        <h1><spring:message code="cart.title" /></h1>
        <table>
            <thead>
            <tr>
                <th></th>
                <th categoryID="article"><spring:message code="cart.column.article" /></th>
                <th><spring:message code="cart.column.price" /></th>
                <th><spring:message code="cart.column.quantity" /></th>
                <th><spring:message code="cart.column.subtotal" /></th>
                <th></th>
            </tr>
            </thead>
            <tbody id="order-lines">
            <c:forEach items="${products}" var="item">
                <c:set var="orderLine" value="${item.value}" />
                <tr id="order-line-${orderLine.product.productID}">
                    <td>
                        <img class="product-image" src="<spring:url value='${orderLine.product.imagePath}'/>" alt="${orderLine.product.labelProduct}">
                    </td>
                    <td>${orderLine.product.labelProduct}</td>
                    <td>${orderLine.product.unitPriceExcludingTax} €</td>
                    <td>
                        <input type="number" name="quantity" value="${orderLine.quantity}" min="1" class="quantity-input" data-product-id="${orderLine.product.productID}">
                    </td>
                    <td id='subtotal-${orderLine.product.productID}'>${orderLine.subTotal} €</td>
                    <td>
                        <button type="submit" class="remove-button" data-product-id="${orderLine.product.productID}">🗑️</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td></td>
                <td colspan="3" style="text-align:right;"><spring:message code="cart.total" /></td>
                <td id="totalAmountMen">${orderLine.order.totalAmount} €</td>
            </tr>
            </tfoot>
        </table>

        <div class="checkout">
            <form action="/firstSpring/checkout" method="GET">
                <button type="submit" class="checkout-button"><spring:message code="cart.checkout.button" /></button>
            </form>
        </div>
    </div>
</div>
</body>
</html>