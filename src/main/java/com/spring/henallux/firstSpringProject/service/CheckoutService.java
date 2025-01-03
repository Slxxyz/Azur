package com.spring.henallux.firstSpringProject.service;

import com.spring.henallux.firstSpringProject.dataAccess.dao.CustomerDAO;
import com.spring.henallux.firstSpringProject.dataAccess.util.ProviderConverter;
import com.spring.henallux.firstSpringProject.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CheckoutService {


    private final CustomerDAO customerDAO;
    private final ProviderConverter providerConverter;


    @Autowired
    private ShoppingCartService shoppingCartService;

    public CheckoutService(CustomerDAO customerDAO, ProviderConverter providerConverter) {
        this.customerDAO = customerDAO;
        this.providerConverter = providerConverter;
    }

    public void calculatePromotion(CommandModel commandModel) {
        double discount = 0;
        double amount = commandModel.getTotalAmount();
        if (amount > 100 && amount < 300) {
            discount = 0.05;
        } else {
            if (amount < 500) {
                discount = 0.10;
            } else {
                if (amount > 500) {
                    discount = 0.20;
                }
            }
        }
        commandModel.setDiscountAmount(amount * discount);
        commandModel.setTotalAmountDiscount(amount - (amount * discount));
    }


    public CommandModel initializeCommandModel(String username, ShoppingCart shoppingCart) {
        HashMap<Integer, OrderLine> productsOrdered = shoppingCartService.loadShoppingCartForUser(username);
        shoppingCart.setProductsOrdered(productsOrdered);

        CommandModel commandModel = new CommandModel();
        commandModel.setShoppingCart(shoppingCart);
        commandModel.setCustomer(providerConverter.customerEntityToCustomerModel(customerDAO.findByUsername(username)));
        commandModel.setTotalAmount(commandModel.getShoppingCart().getProductsOrdered().entrySet().iterator().next().getValue().getOrder().getTotalAmount());
        calculatePromotion(commandModel);
        PaymentModel paymentModel = new PaymentModel(commandModel.getTotalAmountDiscount(), "EUR");
        commandModel.setPaymentModel(paymentModel);
        return commandModel;
    }

    public void orderDone(CommandModel commandModel) {
        OrderCustomer orderCustomer = commandModel.getShoppingCart().getProductsOrdered().entrySet().iterator().next().getValue().getOrder();
        orderCustomer.setState("Accepté");
    }
}
