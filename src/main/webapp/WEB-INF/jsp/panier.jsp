<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include/importTags.jsp"%>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="<spring:url value='/css/panier.css'/>">
</head>
<body>
    <h1>Panier</h1>
    <table>
    <thead>
    <tr>
        <th id="article">Article</th>
        <th>Prix</th>
        <th>Qte</th>
        <th>Sous-total</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${cartItems}" var="item">
    <tr>
        <td>
            <img src="${item.imageUrl}" alt="${item.name}" style="width:50px; height:auto;">
                ${item.name}
        </td>
        <td>${item.price}€</td>
        <td>${item.quantity}</td>
        <td>${item.subtotal}€</td>
        <td>
            <form action="/cart/remove" method="post">
                <input type="hidden" name="itemId" value="${item.id}">
                <button type="submit" class="remove-button">🗑️</button>
            </form>
        </td>
    </tr>
    </c:forEach>
    </tbody>
        <tfoot>
            <tr>
                <td colspan="3" style="text-align:right;">Total</td>
                <td>${totalPrice}€</td>
                <td></td>
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
