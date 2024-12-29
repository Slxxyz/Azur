package com.spring.henallux.firstSpringProject.service;

import com.spring.henallux.firstSpringProject.dataAccess.dao.OrderCustomerDAO;
import com.spring.henallux.firstSpringProject.dataAccess.dao.OrderLineDAO;
import com.spring.henallux.firstSpringProject.dataAccess.entity.CustomerEntity;
import com.spring.henallux.firstSpringProject.dataAccess.entity.OrderCustomerEntity;
import com.spring.henallux.firstSpringProject.dataAccess.entity.ProductEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.CustomerRepository;
import com.spring.henallux.firstSpringProject.dataAccess.repository.OrderCustomerRepository;
import com.spring.henallux.firstSpringProject.dataAccess.repository.OrderLineRepository;
import com.spring.henallux.firstSpringProject.dataAccess.repository.ProductRepository;
import com.spring.henallux.firstSpringProject.dataAccess.util.ProviderConverter;
import com.spring.henallux.firstSpringProject.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ShoppingCartService {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartService.class);

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private OrderCustomerRepository orderCustomerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProviderConverter providerConverter;

    private final OrderCustomerDAO orderCustomerDAO;

    private final OrderLineDAO orderLineDAO;

    @Autowired
    public ShoppingCartService(OrderCustomerDAO orderCustomerDAO, OrderLineDAO orderLineDAO) {
        this.orderCustomerDAO = orderCustomerDAO;
        this.orderLineDAO = orderLineDAO;
    }

    public HashMap<Integer, OrderLine> loadShoppingCartForUser(String username) {
        // Récupère la commande (OrderCustomer) pour cet utilisateur
        CustomerEntity customerEntity = customerRepository.findByUsername(username);
        OrderCustomer orderCustomer = orderCustomerDAO.getOrderByCustomerId(customerEntity);

        // Si aucun panier n'existe pour cet utilisateur, retourne une HashMap vide
        if (orderCustomer == null) {
            return new HashMap<>();
        }

        List<OrderLine> orderLines = orderLineDAO.findByOrderId(providerConverter.orderCustomerModelToOrderCustomerEntity(orderCustomer));
        HashMap<Integer, OrderLine> productsOrdered = new HashMap<>();
        for (OrderLine orderLine : orderLines) {
            productsOrdered.put(orderLine.getProduct().getProductID(), orderLine);
        }
        return productsOrdered;
    }

    /**
     * Met à jour la quantité d'un produit dans le panier.
     */
    public void updateProductQuantity(ShoppingCart shoppingCart, Integer productId, Integer quantity) {
        logger.info("Updating product quantity: productId={}, quantity={}", productId, quantity);

        HashMap<Integer, OrderLine> productsOrdered = shoppingCart.getProductsOrdered();
        OrderLine orderLine = productsOrdered.get(productId);

        if (orderLine != null) {
            orderLine.setQuantity(quantity);
            orderLine.setSubTotal(orderLine.getProduct().getUnitPriceExcludingTax() * quantity);

            // Sauvegarder la ligne de commande
            orderLineRepository.save(providerConverter.orderLineModelToOrderLineEntity(orderLine));
            logger.info("Updated order line: {}", orderLine);

            // Mettre à jour le montant total de l'OrderCustomer
            updateOrderCustomerTotalAmount(orderLine.getOrder(), productsOrdered);
        } else {
            logger.warn("Product with productId={} not found in shopping cart", productId);
            throw new IllegalArgumentException("Produit introuvable dans le panier : " + productId);
        }
    }

    public void updateProductQuantityTemporary(ShoppingCart temporaryCart, Integer productId, Integer quantity) {
        logger.info("Updating product quantity (temporary cart): productId={}, quantity={}", productId, quantity);

        HashMap<Integer, OrderLine> productsOrdered = temporaryCart.getProductsOrdered();
        OrderLine orderLine = productsOrdered.get(productId);
        if (orderLine != null) {
            orderLine.setQuantity(quantity);
            orderLine.setSubTotal(orderLine.getProduct().getUnitPriceExcludingTax() * quantity);
            logger.info("Updated order line in temporary cart: {}", orderLine);
        } else {
            logger.warn("Product with productId={} not found in temporary cart", productId);
            throw new IllegalArgumentException("Produit introuvable dans le panier temporaire : " + productId);
        }
    }


    /**
     * Supprime une ligne de commande du panier.
     */
    public Map<String, Object> removeOrderLine(ShoppingCart shoppingCart, Integer productId) {
        logger.info("Removing order line: productId={}", productId);

        HashMap<Integer, OrderLine> productsOrdered = shoppingCart.getProductsOrdered();
        Map<String, Object> response = new HashMap<>();

        if (productsOrdered.containsKey(productId)) {
            OrderLine orderLine = productsOrdered.remove(productId);

            // Supprimer la ligne de commande de la base de données
            orderLineRepository.delete(providerConverter.orderLineModelToOrderLineEntity(orderLine));
            logger.info("Order line removed: {}", orderLine);

            // Mettre à jour ou supprimer l'OrderCustomer
            OrderCustomer orderCustomer = orderLine.getOrder();
            if (productsOrdered.isEmpty()) {
                // Supprimer l'OrderCustomer si plus de lignes de commande
                orderCustomerRepository.delete(providerConverter.orderCustomerModelToOrderCustomerEntity(orderCustomer));
                logger.info("Order customer removed: {}", orderCustomer);
                response.put("isOrderCustomerDeleted", true);
            } else {
                // Mettre à jour le montant total
                updateOrderCustomerTotalAmount(orderCustomer, productsOrdered);
                response.put("isOrderCustomerDeleted", false);
                response.put("totalAmount", orderCustomer.getTotalAmount());
            }

            response.put("success", true);
        } else {
            logger.warn("Product with productId={} not found in shopping cart", productId);
            response.put("success", false);
            response.put("error", "Produit introuvable dans le panier");
        }

        return response;
    }

    public Map<String, Object> removeOrderLineTemporary(ShoppingCart temporaryCart, Integer productId) {
        logger.info("Removing order line (temporary cart): productId={}", productId);

        HashMap<Integer, OrderLine> productsOrdered = temporaryCart.getProductsOrdered();
        Map<String, Object> response = new HashMap<>();

        if (productsOrdered.containsKey(productId)) {
            OrderLine removedOrderLine = productsOrdered.remove(productId);
            logger.info("Order line removed from temporary cart: {}", removedOrderLine);

            response.put("success", true);
        } else {
            logger.warn("Product with productId={} not found in temporary cart", productId);
            response.put("success", false);
            response.put("error", "Produit introuvable dans le panier temporaire");
        }

        return response;
    }


    public Customer getCustomerByUsername(String username) {

        return providerConverter.customerEntityToCustomerModel(customerRepository.findByUsername(username));
    }

    /**
     * Ajoute ou met à jour un produit dans le panier.
     */
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Integer productId, Integer quantity, Customer customer) {
        logger.info("Adding product: productId={}, quantity={}, user={}", productId, quantity, customer);

        // Récupérer ou créer une commande pour un utilisateur connecté

        OrderCustomer orderCustomer = null;
        if (customer != null) {
            System.out.println("jdois passer la");
            CustomerEntity customerEntity = customerRepository.findByUsername(customer.getUsername());
            System.out.println(customerEntity);
            OrderCustomerEntity orderCustomerEntity = orderCustomerRepository.findByCustomerID(customerEntity);

            if (orderCustomerEntity == null || !Objects.equals(orderCustomerEntity.getState(), "En attente")){
                // Créer une nouvelle commande pour l'utilisateur
                System.out.println("Creating new order for customer: " + customer);
                orderCustomer = new OrderCustomer();
                orderCustomer.setOrderID(-1);
                orderCustomer.setCustomer(customer);
                orderCustomer.setMethodOfPayment("Paypal");
                orderCustomer.setState("En attente");
                orderCustomer.setTotalAmount(1);
                System.out.println("here");
                orderCustomer = providerConverter.orderCustomerEntityToOrderCustomerModel(orderCustomerRepository.save(providerConverter.orderCustomerModelToOrderCustomerEntity(orderCustomer)));
            }else {
                System.out.println("ici");
                orderCustomer = providerConverter.orderCustomerEntityToOrderCustomerModel(orderCustomerEntity);
            }
        }

        // Vérifier si le produit est déjà dans le panier
        System.out.println("fin nouvelle section");
        OrderLine orderLine = shoppingCart.getProductsOrdered().get(productId);
        if (orderLine == null) {
            System.out.println("jdois passer la");
            // Ajouter un nouveau produit
            Optional<ProductEntity> optionalProductEntity = productRepository.findById(productId);
            System.out.println("optional "+optionalProductEntity);
            if (optionalProductEntity.isPresent()) {
                Product product = providerConverter.productEntityToProductModel(optionalProductEntity.get());
                orderLine = new OrderLine(quantity, product.getUnitPriceExcludingTax() * quantity, product);
                orderLine.setOrder(orderCustomer);
                shoppingCart.getProductsOrdered().put(productId, orderLine);

                // Sauvegarder la nouvelle ligne de commande en base si utilisateur connecté
                if (customer != null) {
                    orderLineRepository.save(providerConverter.orderLineModelToOrderLineEntity(orderLine));
                }
            } else {
                throw new IllegalArgumentException("Produit introuvable avec l'ID : " + productId);
            }
        } else {
            // Mettre à jour la quantité
            orderLine.setQuantity(orderLine.getQuantity() + quantity);
            orderLine.setSubTotal(orderLine.getProduct().getUnitPriceExcludingTax() * orderLine.getQuantity());

            // Mettre à jour en base si utilisateur connecté
            if (customer != null) {
                orderLineRepository.save(providerConverter.orderLineModelToOrderLineEntity(orderLine));
            }
        }

        // Mettre à jour le montant total de la commande
        if (customer != null && orderCustomer != null) {
            double totalAmount = shoppingCart.getProductsOrdered().values().stream()
                    .mapToDouble(OrderLine::getSubTotal)
                    .sum();
            orderCustomer.setTotalAmount(totalAmount);
            orderCustomerRepository.save(providerConverter.orderCustomerModelToOrderCustomerEntity(orderCustomer));
        }
        return shoppingCart;
    }


    /**
     * Récupère un produit à partir de son ID.
     */
    private Product fetchProductById(Integer productId) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(productId);
        if (optionalProductEntity.isPresent()) {
            return providerConverter.productEntityToProductModel(optionalProductEntity.get());
        } else {
            logger.error("Product not found with ID: {}", productId);
            throw new IllegalArgumentException("Produit introuvable avec l'ID : " + productId);
        }
    }

    /**
     * Met à jour le montant total d'un OrderCustomer.
     */
    private void updateOrderCustomerTotalAmount(OrderCustomer orderCustomer, HashMap<Integer, OrderLine> productsOrdered) {
        double totalAmount = productsOrdered.values().stream()
                .mapToDouble(OrderLine::getSubTotal)
                .sum();
        orderCustomer.setTotalAmount(totalAmount);
        orderCustomerRepository.save(providerConverter.orderCustomerModelToOrderCustomerEntity(orderCustomer));
        logger.info("Updated order customer total amount: {}", orderCustomer);
    }

    public OrderLine setOrderLineTemporary(ShoppingCart temporaryCart, Integer productId, Integer quantity) {
        logger.info("Setting order line (temporary cart): productId={}, quantity={}", productId, quantity);

        HashMap<Integer, OrderLine> productsOrdered = temporaryCart.getProductsOrdered();
        Map<String, Object> response = new HashMap<>();

        // Vérifier si le produit est déjà dans le panier
        if (productsOrdered.containsKey(productId)) {
            // Mise à jour de la quantité et du sous-total
            OrderLine existingOrderLine = productsOrdered.get(productId);
            existingOrderLine.setQuantity(quantity);
            logger.info("Order line updated in temporary cart: {}", existingOrderLine);
            return existingOrderLine;
        } else {
            // Création d'une nouvelle ligne de commande
            Product product = fetchProductById(productId);

            OrderLine newOrderLine = new OrderLine(quantity, product.getUnitPriceExcludingTax() * quantity, product);
            logger.info("New order line added to temporary cart: {}", newOrderLine);
            return newOrderLine;
        }
    }


    public double calculateTotalAmount(ShoppingCart shoppingCart) {
        return shoppingCart.getProductsOrdered().values().stream()
                .mapToDouble(OrderLine::getSubTotal)
                .sum();
    }
}
