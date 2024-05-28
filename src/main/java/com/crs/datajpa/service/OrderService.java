package com.crs.datajpa.service;

import com.crs.datajpa.model.*;
import com.crs.datajpa.repository.OrderItemRepository;
import com.crs.datajpa.repository.OrderRepository;
import com.crs.datajpa.request.AddOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    public Order createOrder(AddOrderRequest addOrder){

        Customer user = userService.getById(addOrder.getUserId());
        Cart cart = cartService.findCart(addOrder.getUserId());

        Order createdOrder = new Order();
        createdOrder.setUser(user);
        // Salva a ordem no banco de dados para obter o ID
        Order savedOrder = orderRepository.save(createdOrder);

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem item: cart.getCartItems()){
            OrderItem orderItem = new OrderItem();

            OrderItemPK orderItemPK = new OrderItemPK();

            orderItemPK.setProductPK(item.getProduct().getId());
            orderItemPK.setOrderPK(savedOrder.getId());

            orderItem.setId(orderItemPK);  // Define a chave composta no OrderItem
            orderItem.setPrice(item.getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());

            OrderItem createdOrderItem = orderItemRepository.save(orderItem);

            orderItems.add(createdOrderItem);
        }


        savedOrder.setOrderItems(orderItems);
        orderRepository.save(savedOrder);

       return savedOrder;

    }

}
