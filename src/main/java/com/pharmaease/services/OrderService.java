package com.pharmaease.services;


import com.pharmaease.dto.OrderDto;
import com.pharmaease.entity.Order;
import com.pharmaease.entity.Product;
import com.pharmaease.repository.OrderRepository;
import com.pharmaease.repository.ProductRepository;
import com.pharmaease.repository.UserRepository;
import com.pharmaease.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;



@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order placeOrder(OrderDto orderDto) {
        Optional<User> userOptional = userRepository.findByEmail(orderDto.getEmail());
        if (userOptional.isEmpty()) {
            // Handle user not found scenario
            return null;
        }
        User user = userOptional.get();

        // Convert list of product IDs to list of actual Product objects
        List<Long> productIds = orderDto.getProductIds();
        List<Product> products = productIds.stream()
                                           .map(productId -> productRepository.findById(productId)
                                                                              .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId)))
                                           .collect(Collectors.toList());

        // Calculate total price based on product prices
        double totalPrice = products.stream().mapToDouble(Product::getPrice).sum();

        // Create the order entity and set properties
        Order order = new Order();
        order.setUser(user);
        order.setProducts(products);
        order.setTotalPrice(totalPrice);
        order.setShippingAddress(orderDto.getShippingAddress());
        order.setStatus("Pending"); // Set status to pending by default
        order.setPaymentStatus("Pending"); // Set payment status to pending by default

        // Save the order to the database
        return orderRepository.save(order);
    }

    public List<Order> getOrderHistory(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            // Handle user not found scenario
            return null;
        }
        User user = userOptional.get();
        return orderRepository.findByUser(user);
    }
}
