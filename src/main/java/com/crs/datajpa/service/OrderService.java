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
    private CustomerRepository customerRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymenteRepository paymenteRepository;

    @Transactional
    public OrderDTO createOrder(OrderDTO addOrder){

        Customer customer = customerService.authenticated();

        Cart cart = getCart(addOrder.getCartId());

        if(cart == null) throw new EntityNotFoundException();

        // Se o usuário existir e o carrinho não tiver um usuário associado, associar o usuário ao carrinho
        if (cart.getCustomer() == null) {
            cart.setCustomer(customer);
            cartRepository.save(cart); // Atualiza o carrinho com o usuário associado
        }
        
        // preciso verificar se caso o cart ja tenha um usuario associado é o mesmo id do usuario que eu mandei no json
        if(cart.getCustomer() != customer) throw new EntityNotFoundException();



        Order createdOrder = new Order();
        createdOrder.setMoment(Instant.now());
        createdOrder.setCustomer(customer);

        Payment payment = new Payment();
        payment.setStatus(PaymentStatus.PROCESSING);

        createdOrder.setPayment(payment);

        // Salva a ordem no banco de dados para obter o ID
        Order savedOrder = orderRepository.save(createdOrder);


        for (CartItem item: cart.getCartItems()){

            Product product = productRepository.getReferenceById(item.getProduct().getId());

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(savedOrder);
            orderItem.setProduct(item.getProduct());
//          orderItem.setOrder(savedOrder); o jpa vai persistir para mim a ordem devido o uso do cascade.all

            orderItem.setPrice(item.getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());


            savedOrder.getOrderItems().add(orderItem);
        }


        orderRepository.save(savedOrder);



        orderItemRepository.saveAll(savedOrder.getOrderItems());

       return new OrderDTO(savedOrder);

    }

    private Cart getCart(Long id){
        return cartRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

}
