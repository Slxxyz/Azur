package com.spring.henallux.firstSpringProject.service;

import com.spring.henallux.firstSpringProject.dataAccess.entity.ProductEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.OrderCustomerRepository;
import com.spring.henallux.firstSpringProject.dataAccess.repository.OrderLineRepository;
import com.spring.henallux.firstSpringProject.dataAccess.repository.ProductRepository;
import com.spring.henallux.firstSpringProject.dataAccess.util.ProviderConverter;
import com.spring.henallux.firstSpringProject.model.OrderCustomer;
import com.spring.henallux.firstSpringProject.model.OrderLine;
import com.spring.henallux.firstSpringProject.model.Product;
import com.spring.henallux.firstSpringProject.model.ShoppingCart;
import org.apache.catalina.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    private ProviderConverter providerConverter;

    public void updateProductQuantity(ShoppingCart shoppingCart, Integer productId, Integer quantity) {
        logger.info("Updating product quantity: productId={}, quantity={}", productId, quantity);
        HashMap<Integer, OrderLine> productsOrdered = shoppingCart.getProductsOrdered();
        if (productsOrdered.containsKey(productId)) {
            OrderLine orderLine = productsOrdered.get(productId);
            orderLine.setQuantity(quantity);
            orderLine.setSubTotal(orderLine.getProduct().getUnitPriceExcludingTax() * quantity);
            System.out.println(orderLine);
            orderLineRepository.save(providerConverter.orderLineModelToOrderLineEntity(orderLine)); // Save changes to the database
            logger.info("Updated order line: {}", orderLine);

            // Update OrderCustomer totalAmount
            OrderCustomer orderCustomer = orderLine.getOrder();
            double totalAmount = productsOrdered.values().stream()
                    .mapToDouble(OrderLine::getSubTotal)
                    .sum();
            orderCustomer.setTotalAmount(totalAmount);
            System.out.println(orderCustomer);
            orderCustomer.setCustomer(orderLine.getOrder().getCustomer());
            System.out.println("ici");
            orderCustomerRepository.save(providerConverter.orderCustomerModelToOrderCustomerEntity(orderCustomer));
            System.out.println("la");
            // Save changes to the database
            logger.info("Updated order customer: {}", orderCustomer);
        } else {
            logger.warn("Product with productId={} not found in shopping cart", productId);
        }
    }

    public Map<String, Object> removeOrderLine(ShoppingCart shoppingCart, Integer productId) {
        HashMap<Integer, OrderLine> productsOrdered = shoppingCart.getProductsOrdered();
        System.out.println("service" + productId);
        System.out.println(productsOrdered);

        Map<String, Object> response = new HashMap<>();

        if (productsOrdered.containsKey(productId)) {
            System.out.println("if" + productId);

            // Supprimer la ligne de commande
            OrderLine orderLine = productsOrdered.remove(productId);
            orderLineRepository.delete(providerConverter.orderLineModelToOrderLineEntity(orderLine)); // Supprimer de la base de données

            // Récupérer l'OrderCustomer
            OrderCustomer orderCustomer = orderLine.getOrder();

            // Vérifier s'il reste des OrderLine pour cet OrderCustomer
            if (productsOrdered.isEmpty()) {
                // Si plus de OrderLine, supprimer l'OrderCustomer
                System.out.println("No more order lines, removing OrderCustomer");
                orderCustomerRepository.delete(providerConverter.orderCustomerModelToOrderCustomerEntity(orderCustomer));

                response.put("success", true);
                response.put("isOrderCustomerDeleted", true); // Indiquer que l'OrderCustomer est supprimée
                return response;
            } else {
                // Sinon, mettre à jour le montant total
                double totalAmount = productsOrdered.values().stream()
                        .mapToDouble(OrderLine::getSubTotal)
                        .sum();
                orderCustomer.setTotalAmount(totalAmount);
                orderCustomerRepository.save(providerConverter.orderCustomerModelToOrderCustomerEntity(orderCustomer)); // Sauvegarder les changements

                response.put("success", true);
                response.put("isOrderCustomerDeleted", false); // Indiquer que l'OrderCustomer existe encore
                response.put("totalAmount", totalAmount);
                return response;
            }
        }

        response.put("success", false); // Ligne de commande non trouvée
        return response;
    }

    public void addOrUpdateProduct(ShoppingCart shoppingCart, Integer productId, Integer quantity) {
        // Vérifier si le produit est déjà dans le panier
        OrderLine orderLine = shoppingCart.getProductsOrdered().get(productId);
        if (orderLine == null) {
            // Ajouter le produit
            Optional<ProductEntity> optionalProductEntity = productRepository.findById(productId);
            if (optionalProductEntity.isPresent()) {
                Product product = providerConverter.productEntityToProductModel(optionalProductEntity.get());
                orderLine = new OrderLine(quantity, product.getUnitPriceExcludingTax() * quantity, product);
                shoppingCart.getProductsOrdered().put(productId, orderLine);
            } else {
                // Gérer le cas où le produit n'existe pas
                throw new IllegalArgumentException("Produit introuvable avec l'ID : " + productId);
            }
        } else {
            // Mettre à jour la quantité
            orderLine.setQuantity(orderLine.getQuantity() + quantity);
            orderLine.setSubTotal(orderLine.getProduct().getUnitPriceExcludingTax() * orderLine.getQuantity());
        }
    }

}
