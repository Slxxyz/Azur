<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <link rel="stylesheet" href="<spring:url value='/css/paymentSuccess.css' />">
</head>
<body>
<main class="checkout-page">
    <section class="payment-section">
        <div class="icon-container">
            <img src="<spring:url value='/images/Success/success.png' />" alt="Success Icon" class="success-icon">
        </div>
        <h2 class="title"><spring:message code="SuccessTitle"/></h2>
        <p class="message"><spring:message code="SuccessMessage"/></p>
        <a href="<spring:url value='/azur' />" class="btn btn-primary"><spring:message code="SuccessBtn"/></a>
    </section>
</main>
</body>
</html>