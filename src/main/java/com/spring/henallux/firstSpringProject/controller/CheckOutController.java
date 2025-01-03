package com.spring.henallux.firstSpringProject.controller;


import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.spring.henallux.firstSpringProject.model.CommandModel;
import com.spring.henallux.firstSpringProject.model.ShoppingCart;
import com.spring.henallux.firstSpringProject.service.CheckoutService;
import com.spring.henallux.firstSpringProject.service.PayPalService;
import com.spring.henallux.firstSpringProject.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
@SessionAttributes("commandModel")
public class CheckOutController {

    @Autowired
    private PayPalService payPalService;

    @Autowired
    private CheckoutService checkoutService;

    @ModelAttribute("commandModel")
    public CommandModel initializeCommandModel() {
        return new CommandModel();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String checkOut(Model model, @ModelAttribute(value = "panier") ShoppingCart shoppingCart,
                           @ModelAttribute(value = "commandModel") CommandModel commandModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        commandModel = checkoutService.initializeCommandModel(username, shoppingCart);

        model.addAttribute("products", commandModel.getShoppingCart().getProductsOrdered());
        model.addAttribute("command", commandModel);
        model.addAttribute("title", "Checkout");
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
                          @RequestParam("PayerID") String payerId, Model model, @ModelAttribute(value="commandModel") CommandModel commandModel) {
        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);
            checkoutService.orderDone(commandModel);
            model.addAttribute("title", "Payment Successful!");
            model.addAttribute("showHeader", true);
            model.addAttribute("showFooter", true);
            return  "integrated:paymentSuccess"; // Afficher une page de succès
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            model.addAttribute("title", "Payment failed.");
            model.addAttribute("showHeader", true);
            model.addAttribute("showFooter", true);
            return "integrated:paymentError"; // En cas d'erreur
        }
    }

    @GetMapping("/successtest")
    public String successtest(Model model) {
        model.addAttribute("locale", "fr");
        model.addAttribute("title", "Payment Successful!");
        model.addAttribute("showHeader", true);
        model.addAttribute("showFooter", true);
        return "integrated:paymentSuccess"; // Afficher une page de succès
    }

    @GetMapping("/errortest")
    public String errortest(Model model) {
        model.addAttribute("title", "Payment failed.");
        model.addAttribute("showHeader", true);
        model.addAttribute("showFooter", true);
        return "integrated:paymentError"; // Afficher une page d'erreur
    }

    // Gérer l'annulation du paiement
    @GetMapping("/cancel")
    public String cancel() {
        return "redirect:/panier"; // Afficher une page d'annulation
    }


}
