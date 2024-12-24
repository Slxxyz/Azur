<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment Success</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value='/css/paymentSuccess.css' />">
</head>
<body>
<main class="checkout-page">
    <section class="payment-section">
        <h2>Payment Success</h2>
        <div class="success-message">
            <p>Your payment was successful! Thank you for your purchase.</p>
            <a href="<spring:url value='/azur' />" class="btn">Return to Home</a>
        </div>
    </section>
</main>
</body>
</html>