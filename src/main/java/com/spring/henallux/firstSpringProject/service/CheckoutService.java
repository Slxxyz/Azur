package com.spring.henallux.firstSpringProject.service;

import com.spring.henallux.firstSpringProject.dataAccess.dao.CustomerDAO;
import com.spring.henallux.firstSpringProject.dataAccess.dao.LocationDAO;
import com.spring.henallux.firstSpringProject.dataAccess.dao.OrderCustomerDAO;
import com.spring.henallux.firstSpringProject.dataAccess.util.ProviderConverter;
import com.spring.henallux.firstSpringProject.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CheckoutService {


    private final CustomerDAO customerDAO;
    private final ProviderConverter providerConverter;


    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private OrderCustomerDAO orderCustomerDAO;
    @Autowired
    private LocationDAO locationDAO;

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
            if (amount < 500 && amount > 300) {
                discount = 0.10;
            } else {
                if (amount > 500) {
                    discount = 0.20;
                }
            }
        }
        commandModel.setDiscountAmount(Math.round((amount * discount) * 100.0) / 100.0);
        commandModel.setTotalAmountDiscount(Math.round((amount - (amount * discount)) * 100.0) / 100.0);
    }


    public void initializeCommandModel(String username, ShoppingCart shoppingCart, CommandModel commandModel) {
        HashMap<Integer, OrderLine> productsOrdered = shoppingCartService.loadShoppingCartForUser(username);
        shoppingCart.setProductsOrdered(productsOrdered);
        commandModel.setShoppingCart(shoppingCart);
        commandModel.setCustomer(providerConverter.customerEntityToCustomerModel(customerDAO.findByUsername(username)));
        commandModel.setTotalAmount(commandModel.getShoppingCart().getProductsOrdered().entrySet().iterator().next().getValue().getOrder().getTotalAmount());
        calculatePromotion(commandModel);
        PaymentModel paymentModel = new PaymentModel(commandModel.getTotalAmountDiscount(), "EUR");
        commandModel.setPaymentModel(paymentModel);
    }

    public void orderDone(CommandModel commandModel) {
        OrderCustomer orderCustomer = commandModel.getShoppingCart().getProductsOrdered().entrySet().iterator().next().getValue().getOrder();
        orderCustomer.setState("Accepté");
        orderCustomerDAO.save(providerConverter.orderCustomerModelToOrderCustomerEntity(orderCustomer));
    }

    public void location(Map<String, String> formData) {
        Location location = new Location();
        location.setCountry(formData.get("country"));
        String address = formData.get("address");
        location.setHouseNumber(Integer.parseInt(formData.get("houseNumber")));
        if (formData.get("letterBox") != null) {
            location.setLetterBox(formData.get("letterBox"));
        }
        location.setLocation(formData.get("location"));
        location.setPostalCode(Integer.parseInt(formData.get("postalCode")));
        location.setStreet(formData.get("street"));
        System.out.println(location);
        locationDAO.saveLocation(providerConverter.locationModelToLocationEntity(location));
    }
}
