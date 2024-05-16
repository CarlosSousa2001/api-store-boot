package com.crs.datajpa.controller;

import com.crs.datajpa.model.Order;
import com.crs.datajpa.request.AddOrderRequest;
import com.crs.datajpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody AddOrderRequest addOrderRequest){
        var orderRes = orderService.createOrder(addOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRes);
    }

}
