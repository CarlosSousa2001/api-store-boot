package com.crs.datajpa.service;

import com.crs.datajpa.model.Cart;
import com.crs.datajpa.model.CartItem;
import com.crs.datajpa.model.Product;
import com.crs.datajpa.model.User;
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
    private UserService userService;
    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemService cartItemService;

    public Cart findCart(Long id){
        Optional<Cart> cartOptional = cartRepository.findByUserId(id);

        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            User user = cart.getUserCart();

            if (user == null) {
                throw new IllegalStateException("Carrinho encontrado, mas não há usuário associado.");
            }

            return cart;
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    public String addItemToCart(AddItemRequest addItem)  {

        User user = userService.getById(addItem.getUserID());

        Optional<Cart> cartOptional = cartRepository.findByUserId(addItem.getUserID());

        Cart cart;

        if (cartOptional.isEmpty()) {
            cart = new Cart();
            cart.setUserCart(user);
            user.setCart(cart);
            cartRepository.save(cart);
        } else {
            cart = cartOptional.get();
        }

        Product product = productService.getById(addItem.getProductId());

        CartItem cartItem = new CartItem();

        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(addItem.getQuantity());


        CartItem createdCartItem = cartItemService.createCartItem(cartItem);

        cart.getCartItems().add(createdCartItem);

        cartRepository.save(cart);

        return "item adicionado com sucesso";
    }
}
