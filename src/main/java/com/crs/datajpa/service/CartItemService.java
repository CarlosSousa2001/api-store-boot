package com.crs.datajpa.service;

import com.crs.datajpa.model.CartItem;
import com.crs.datajpa.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItem createCartItem(CartItem cartItem){
        // aqui eu posso pegar diretamento o cartItem que eu vou receber no metodo e setar algumas infomações

        cartItem.setSize("M");
        cartItem.setQuantity(1);
        //cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());

        return cartItemRepository.save(cartItem);
    }
}
