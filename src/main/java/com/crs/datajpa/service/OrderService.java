package com.crs.datajpa.service;

import com.crs.datajpa.exceptions.EntityNotFoundException;
import com.crs.datajpa.model.*;
import com.crs.datajpa.repository.CartRepository;
import com.crs.datajpa.repository.CustomerRepository;
import com.crs.datajpa.repository.OrderItemRepository;
import com.crs.datajpa.repository.OrderRepository;
import com.crs.datajpa.request.AddOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;


    @Transactional
    public Order createOrder(AddOrderRequest addOrder){

        Customer user = getCustomer(addOrder.getUserId());

        if(user == null) throw new EntityNotFoundException();

        Cart cart = getCart(addOrder.getCartId());

        if(cart == null) throw new EntityNotFoundException();

        // Se o usuário existir e o carrinho não tiver um usuário associado, associar o usuário ao carrinho
        if (cart.getCustomer() == null) {
            cart.setCustomer(user);
            cartRepository.save(cart); // Atualiza o carrinho com o usuário associado
        }
        
        // preciso verificar se caso o cart ja tenha um usuario associado é o mesmo id do usuario que eu mandei no json


        Order createdOrder = new Order();
        createdOrder.setCustomer(user);
        // Salva a ordem no banco de dados para obter o ID
        Order savedOrder = orderRepository.save(createdOrder);

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem item: cart.getCartItems()){


            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(item.getProduct());
//          orderItem.setOrder(savedOrder); o jpa vai persistir para mim a ordem devido o uso do cascade.all

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

    private Customer getCustomer(Long id){
        return customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    private Cart getCart(Long id){
        return cartRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
