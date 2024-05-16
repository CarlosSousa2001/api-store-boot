package com.crs.datajpa.service;

import com.crs.datajpa.model.*;
import com.crs.datajpa.repository.OrderItemRepository;
import com.crs.datajpa.repository.OrderRepository;
import com.crs.datajpa.request.AddOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        User user = userService.getById(addOrder.getUserId());
        Cart cart = cartService.findCart(addOrder.getUserId());

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem item: cart.getCartItems()){
            OrderItem orderItem = new OrderItem();

            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());

            OrderItem createdOrderItem = orderItemRepository.save(orderItem);

            orderItems.add(createdOrderItem);
        }

        Order createdOrder = new Order();

        createdOrder.setUser(user);
        createdOrder.setOrderItems(orderItems);

        Order savedOrder = orderRepository.save(createdOrder);

        // eu fiz o relacionamento da lista de Orderitems com o order, agora eu preciso dizer para cada ordemitem que order ele pentence, assim como fiz no for acima

        for(OrderItem item: orderItems){
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }

       return savedOrder;

    }

}
