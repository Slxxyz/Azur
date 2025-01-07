<%@ include file="include/importTags.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>${title}</title>
  <link type="text/css" rel="stylesheet" href="<spring:url value='/css/checkout.css' />">
</head>
<body>
<main class="checkout-page">
  <section class="address-section">
    <h2><spring:message code="DeliveryAddress"/></h2>
    <form id="livraison" action="/firstSpring/checkout/create-payment" method="post">
      <div class="name-fields">
        <input type="text" name="firstName" placeholder="Prenom" required>
        <input type="text" name="lastName" placeholder="Nom" required>
      </div>
      <div class="address-fields">
        <input type="text" name="street" placeholder="Adresse" required>
        <input type="number" name="houseNumber" placeholder="Numéro de rue" required>
      </div>
      <div class="city-fields">
        <input type="number" name="postalCode" placeholder="Code postal" required>
        <input type="text" name="letterBox" placeholder="Complément d'adresse">
      </div>
      <div class="location-fields">
        <input type="text" name="country" placeholder="Pays" required>
        <input type="text" name="city" placeholder="Ville" required>
      </div>
      <input type="hidden" name="amount" value="${command.paymentModel.getAmount()}">
      <input type="hidden" name="currency" value="${command.paymentModel.getCurrency()}">
    </form>
  </section>

  <section class="payment-section">
    <h2><spring:message code="PaymentMethod"/></h2>
    <div class="paypal">
      <img src="<spring:url value='/images/CheckOut/paypal.png' />" alt="PayPal">
    </div>
  </section>

  <section class="order-summary-section">
    <h2><spring:message code="OrderSummary"/></h2>
    <div class="order-summary">
      <c:forEach items="${products}" var="command">
        <div class="item">
          <img src="<spring:url value='${command.value.product.imagePath}' />" alt="${command.value.product.labelProduct}">
          <p>${command.value.product.labelProduct}</p>
          <span><spring:message code="Qty"/>: ${command.value.quantity}</span>
          <span><spring:message code="subtotal"/>: ${command.value.subTotal} €</span>
        </div>
      </c:forEach>
      <div class="totals">
        <p><spring:message code="subtotal"/>: ${command.totalAmount} €</p>
        <p><spring:message code="Discount"/>: ${command.discountAmount} €</p>
        <p><strong><spring:message code="Total"/>: ${command.totalAmountDiscount} €</strong></p>
      </div>
    </div>
  </section>

  <button type="submit" form="livraison" class="order-button"><spring:message code="Order"/></button>

</main>
</body>
</html>