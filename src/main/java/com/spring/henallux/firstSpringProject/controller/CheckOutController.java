package com.spring.henallux.firstSpringProject.controller;


import ch.qos.logback.core.boolex.EvaluationException;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.spring.henallux.firstSpringProject.model.PaymentModel;
import com.spring.henallux.firstSpringProject.service.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/checkout")
public class CheckOutController {

    @Autowired
    private PayPalService payPalService;

    @RequestMapping(method = RequestMethod.GET)
    public String checkOut(Model model) {
        PaymentModel paymentModel = new PaymentModel(1.0,"EUR");
        model.addAttribute("title", "Checkout");
        model.addAttribute("paymentModel", paymentModel);
        model.addAttribute("showHeader", true);
        model.addAttribute("showFooter", true);
        return "integrated:checkout";
    }

    // Créer un paiement
    @RequestMapping(value="/create-payment", method= RequestMethod.POST)
    public String createPayment(@RequestParam("amount") Double amount,
                                @RequestParam("currency") String currency,
                                Model model) {
        try {
            // Créer le paiement avec PayPal
            Payment payment = payPalService.createPayment(amount, currency, "paypal", "sale",
                    "Payment description", "http://localhost:8082/firstSpring/checkout/cancel", "http://localhost:8082/firstSpring/checkout/success");

            // Ajouter l'URL de redirection vers PayPal à la vue
            for (com.paypal.api.payments.Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    model.addAttribute("approvalUrl", link.getHref());
                    break;
                }
            }

            // Rediriger vers PayPal pour approbation
            return "redirect:" + model.getAttribute("approvalUrl");
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Payment creation failed. Please try again.");
            return "integrated:paymentError"; // En cas d'erreur, redirige vers la page d'erreur
        }
    }


    // Gérer le succès du paiement
    @GetMapping("/success")
    public String success(@RequestParam("paymentId") String paymentId,
                          @RequestParam("PayerID") String payerId, Model model) {
        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);
            model.addAttribute("message", "Payment Successful!");
            return  "integrated:paymentSuccess"; // Afficher une page de succès
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return "integrated:paymentError"; // En cas d'erreur
        }
    }

    @GetMapping("/successtest")
    public String successtest() {
        return "integrated:paymentSuccess"; // Afficher une page de succès
    }

    @GetMapping("/errortest")
    public String errortest() {
        return "integrated:paymentError"; // Afficher une page d'erreur
    }

    // Gérer l'annulation du paiement
    @GetMapping("/cancel")
    public String cancel() {
        return "integrated:welcome"; // Afficher une page d'annulation
    }


}
