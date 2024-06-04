package com.crs.datajpa.controller;

import com.crs.datajpa.model.Cart;
import com.crs.datajpa.request.AddItemRequest;
import com.crs.datajpa.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable Long id){
        Cart cartRes =  cartService.findCart(id);
        return ResponseEntity.ok().body(cartRes);
    }

    @PostMapping
    public ResponseEntity<Cart> addItemToCart(@RequestBody AddItemRequest addReq){
        var cart = cartService.addItemToCart(addReq);

        return ResponseEntity.ok().body(cart);
    }
}
