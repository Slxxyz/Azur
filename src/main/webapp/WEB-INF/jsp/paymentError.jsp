<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment Error</title>
    <link type="text/css" rel="stylesheet" href="<spring:url value='/css/paymentError.css' />">
</head>
<body>
<main class="checkout-page">
    <section class="payment-section">
        <h2>Payment Error</h2>
        <div class="error-message">
            <p>There was an error processing your payment. Please try again.</p>
            <a href="<spring:url value='/azur' />" class="btn">Return to Home</a>
        </div>
    </section>
</main>
</body>
</html>