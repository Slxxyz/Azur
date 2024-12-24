<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
      <div class="item">
        <img src="<spring:url value='/images/Articles/Falcima.jpg' />" alt="Produit">
        <p>Butterfly Ovtcharov S1</p>
        <span><spring:message code="Qty"/>: 1</span>
        <span><spring:message code="subtotal"/>: 24,90€</span>
      </div>
      <div class="totals">
        <p><spring:message code="subtotal"/>: 24,90€</p>
        <p><spring:message code="Discount"/>: 4,99€</p>
        <p><strong><spring:message code="Total"/>: 29,95€</strong></p>
      </div>
    </div>
  </section>

  <form action="/firstSpring/checkout/create-payment" method="post">
    <input type="hidden" name="amount" value="${paymentModel.getAmount()}">
    <input type="hidden" name="currency" value="${paymentModel.getCurrency()}">
    <button type="submit" class="order-button"><spring:message code="Order"/></button>
  </form>


</main>
</body>
</html>