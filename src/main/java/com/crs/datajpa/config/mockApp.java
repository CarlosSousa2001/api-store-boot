package com.crs.datajpa.config;


import com.crs.datajpa.model.*;
import com.crs.datajpa.repository.*;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

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


    private ProductRepository productRepository;


    private OrderRepository orderRepository;


    private OrderItemRepository orderItemRepository;

    private PaymenteRepository paymenteRepository;

    private InvoiceRepository invoiceRepository;

    mockApp(CategoryRepository categoryRepository, UserRepository userRepository, AddressRepository addressRepository, CartRepository cartRepository, ProductRepository productRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository, PaymenteRepository paymenteRepository, InvoiceRepository invoiceRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.cartRepository = cartRepository;
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

        // criar odemitem -ordem

        List<OrderItem> orderItems = new ArrayList<>();

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setSize("M");
        orderItem1.setQuantity(2);
        orderItem1.setPrice(450);

        OrderItem createdOrderItem = orderItemRepository.save(orderItem1);

        orderItems.add(createdOrderItem);

        // order

        Order order = new Order();
        order.setId(1L);
        order.setUser(customer);
        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);

//        OrderItemPK orderItemPK = new OrderItemPK();
//
//        orderItemPK.setProduct(product);
//        orderItemPK.setOrder(order);
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
        invoice1.setText("HTML OU XML DA NOTA");
        invoice1.setDateOfIssue(new Date());

        invoiceRepository.save(invoice1);




    }
}
