<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/importTags.jsp"%>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="<spring:url value='/css/panier.css'/>">
    <script src="<spring:url value='/js/shoppingCart.js'/>"></script>
</head>

<body>
    <h1>Panier</h1>
    <table>
    <thead>
    <tr>
        <th></th>
        <th categoryID="article">Article</th>
        <th>Prix</th>
        <th>Qte</th>
        <th>Sous-total</th>
        <th></th>
    </tr>
    </thead>
    <tbody id="order-lines">
    <c:forEach items="${productsOrdered}" var="item">
        <c:set var="orderLine" value="${item.value}" />
    <tr id="order-line-${orderLine.product.productID}">
        <td>
            <img class="product-image" src="<spring:url value='${orderLine.product.imagePath}'/>" alt="${orderLine.product.labelProduct}">
        </td>
        <td>
            ${orderLine.product.labelProduct}
        </td>
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
                <td colspan="3" style="text-align:right;">Total</td>
                <td id="totalAmountMen">${orderLine.order.totalAmount} €</td>
            </tr>
        </tfoot>
    </table>

    <div class="checkout">
        <form action="/checkout" method="post">
            <button type="submit" class="checkout-button">Commander</button>
        </form>
    </div>
</body>
</html>
