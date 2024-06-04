package com.crs.datajpa.service;

import com.crs.datajpa.exceptions.EntityNotFoundException;
import com.crs.datajpa.model.Cart;
import com.crs.datajpa.model.CartItem;
import com.crs.datajpa.model.Product;
import com.crs.datajpa.model.Customer;
import com.crs.datajpa.repository.CartRepository;
import com.crs.datajpa.request.AddItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {


    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemService cartItemService;

    public Cart findCart(Long id){
        return cartRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException()
        );

    }

    public Cart addItemToCart(AddItemRequest addItem)  {

        // eu posso adicionar itens ao carrinho mesmo sem usuairo logado, assim como a amazon

//        Customer user = customerService.getById(addItem.getUserID());
//
//        Optional<Cart> cartOptional = cartRepository.findByUserId(addItem.getUserID());
//
//        Cart cart;
//
//        if (cartOptional.isEmpty()) {
//            cart = new Cart();
//            cart.setUserCart(user);
//            user.setCart(cart);
//            cartRepository.save(cart);
//        } else {
//            cart = cartOptional.get();
//        }

        Cart cart = new Cart();

        cartRepository.save(cart);

        Product product = productService.getById(addItem.getProductId());

        CartItem cartItem = new CartItem();

        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(addItem.getQuantity());


        CartItem createdCartItem = cartItemService.createCartItem(cartItem);

        cart.getCartItems().add(createdCartItem);

        cartRepository.save(cart);

        return cart;
    }
}
