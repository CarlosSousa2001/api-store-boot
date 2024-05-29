package com.crs.datajpa.service;

import com.crs.datajpa.model.*;
import com.crs.datajpa.repository.OrderItemRepository;
import com.crs.datajpa.repository.OrderRepository;
import com.crs.datajpa.request.AddOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Order createOrder(AddOrderRequest addOrder){

        Customer user = userService.getById(addOrder.getUserId());
        Cart cart = cartService.findCart(addOrder.getUserId());

        Order createdOrder = new Order();
        createdOrder.setUser(user);
        // Salva a ordem no banco de dados para obter o ID
        Order savedOrder = orderRepository.save(createdOrder);

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem item: cart.getCartItems()){

            OrderItemPK orderItemPK = new OrderItemPK(savedOrder.getId(), item.getProduct().getId());

            OrderItem orderItem = new OrderItem();

            orderItem.setId(orderItemPK);
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(item.getProduct());

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
