package com.crs.datajpa.config;


import com.crs.datajpa.model.*;
import com.crs.datajpa.repository.*;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class mockApp implements ApplicationRunner {


    private CategoryRepository categoryRepository;

    private UserRepository userRepository;

    private AddressRepository addressRepository;


    private CartRepository cartRepository;

    private CartItemRepository cartItemRepository;

    private ProductRepository productRepository;


    private OrderRepository orderRepository;


    private OrderItemRepository orderItemRepository;

    private PaymenteRepository paymenteRepository;

    private InvoiceRepository invoiceRepository;

    mockApp(CategoryRepository categoryRepository, UserRepository userRepository, AddressRepository addressRepository, CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository, PaymenteRepository paymenteRepository, InvoiceRepository invoiceRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.paymenteRepository = paymenteRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("-------ApplicationRunner--------");

        Customer customer = new Customer();
        customer.setUsername("mockuser");
        customer.setEmail("mockuser@example.com");
        customer.setPassword("mockpassword");
        customer.setRole(Customer.Role.ROLE_CLIENTE);

        userRepository.save(customer);


        Product product = new Product();

        product.setTitle("RTX 3050 TI");
        product.setDescription("Uma placa de vídeo");
        product.setCreatedAt(LocalDateTime.now());
        product.setPrice(250);

        productRepository.save(product);

        //

        Cart cart = new Cart();

        cart.setUserCart(customer);
        customer.setCart(cart);
        cartRepository.save(cart);

        CartItem cartItem = new CartItem();

        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(2);

        CartItem createdCartItem = cartItemRepository.save(cartItem);

        cart.getCartItems().add(createdCartItem);

        cartRepository.save(cart);

        // criar odemitem -ordem

        Order order = new Order();
        order.setId(1L);
        order.setUser(customer);
        Order savedOrder = orderRepository.save(order);



        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItem item: cart.getCartItems()){

            OrderItemPK orderItemPK = new OrderItemPK(order.getId(), item.getProduct().getId());

            OrderItem orderItem1 = new OrderItem();

            orderItem1.setId(orderItemPK);
            orderItem1.setProduct(product);
            orderItem1.setOrder(savedOrder);

            orderItem1.setSize("M");
            orderItem1.setQuantity(2);
            orderItem1.setPrice(450);


            OrderItem createdOrderItem = orderItemRepository.save(orderItem1);

            orderItems.add(createdOrderItem);

        }

        // order

        order.setOrderItems(orderItems);

        orderRepository.save(order);
//
//        for (OrderItem item : orderItems) {
//            item.setId(orderItemPK);
//        }
//
//        List<OrderItem> savedOrderItems = orderItemRepository.saveAll(orderItems);
//
//        savedOrder.setOrderItems(savedOrderItems);
//
//        orderRepository.save(savedOrder);



        // payment

        Payment payment = new Payment();

        payment.setOrder(order);
        payment.setStatus(PaymentStatus.PROCESSING);

        paymenteRepository.save(payment);

        order.setPayment(payment);

        orderRepository.save(order);

        // nota fiscal

        Invoice invoice1 = new Invoice();

        invoice1.setOrder(order);
        invoice1.setText(carregarNotaFiscal());
        invoice1.setDateOfIssue(new Date());

        invoiceRepository.save(invoice1);


    }

    private static byte[] carregarNotaFiscal(){
        try{
            return mockApp.class.getResourceAsStream("/nota-fiscal.xml").readAllBytes();
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }
}
