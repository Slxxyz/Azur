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
    <form>
      <input type="email" placeholder="Adresse email" required>
      <div class="name-fields">
        <input type="text" placeholder="Prenom" required>
        <input type="text" placeholder="Nom" required>
      </div>
      <input type="text" placeholder="Adresse" required>
      <div class="location-fields">
        <input type="text" placeholder="Pays" required>
        <input type="text" placeholder="Ville" required>
      </div>
      <div class="zip-phone-fields">
        <input type="text" placeholder="Code postal" required>
        <input type="text" placeholder="Numéro de téléphone" required>
      </div>
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
        <p><spring:message code="Discount"/>: ${formattedDiscount} €</p>
        <p><strong><spring:message code="Total"/>: ${command.totalAmountDiscount} €</strong></p>
      </div>
    </div>
  </section>

  <form action="/firstSpring/checkout/create-payment" method="post">
    <input type="hidden" name="amount" value="${command.paymentModel.getAmount()}">
    <input type="hidden" name="currency" value="${command.paymentModel.getCurrency()}">
    <button type="submit" class="order-button"><spring:message code="Order"/></button>
  </form>

</main>
</body>
</html>