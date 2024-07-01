package com.crs.datajpa.service;

import com.crs.datajpa.exceptions.EntityNotFoundException;
import com.crs.datajpa.model.*;
import com.crs.datajpa.repository.*;
import com.crs.datajpa.model.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductRepository productRepository;


    @Transactional
    public OrderDTO createOrder(OrderDTO addOrder) {

        Customer customer = customerService.authenticated();

        Cart cart = cartService.getCart(addOrder.getCartId(), customer);

        Order order = new Order();
        order.setMoment(Instant.now());
        order.setCustomer(customer);

        Payment payment = new Payment();
        payment.setStatus(PaymentStatus.PROCESSING);
        order.setPayment(payment);

        for (CartItem item : cart.getCartItems()) {

            Product product = productRepository.getReferenceById(item.getProduct().getId());

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setPrice(item.getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());

            order.getOrderItems().add(orderItem);
        }

        orderRepository.save(order);
        orderItemRepository.saveAll(order.getOrderItems());

        return new OrderDTO(order);

    }


}
