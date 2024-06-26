package com.crs.datajpa.controller;

import com.crs.datajpa.model.Order;
import com.crs.datajpa.model.dto.OrderDTO;
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
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO){
        var orderRes = orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRes);
    }

}
