package com.spring.henallux.firstSpringProject.service;


import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Locale;

@Service
public class PayPalService {
    private final String clientId;
    private final String clientSecret;
    private final String mode;

    public PayPalService(@Value("${paypal.client.id}") String clientId,
                  @Value("${paypal.client.secret}") String clientSecret,
                  @Value("${paypal.mode}") String mode) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.mode = mode;
    }

    public Payment createPayment(Double amount, String currency, String method, String intent,
                                 String description, String cancelUrl, String successUrl) throws PayPalRESTException {
        System.out.println("Amount: " + amount + ", Currency: " + currency);

        String formattedAmount = String.format(Locale.US, "%.2f", amount);
        System.out.println("Formatted Amount: " + formattedAmount);

        Amount payAmount = new Amount();
        payAmount.setCurrency(currency);
        payAmount.setTotal(formattedAmount);

        System.out.println("Pay Amount: " + payAmount);

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(payAmount);

        System.out.println("Transaction: " + transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(Collections.singletonList(transaction));

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        APIContext apiContext = new APIContext(clientId, clientSecret, mode);

        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        APIContext apiContext = new APIContext(clientId, clientSecret, mode);

        return payment.execute(apiContext, paymentExecution);
    }

}
