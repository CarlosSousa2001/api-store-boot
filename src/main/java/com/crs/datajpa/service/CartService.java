package com.crs.datajpa.service;

import com.crs.datajpa.exceptions.EntityNotFoundException;
import com.crs.datajpa.model.Cart;
import com.crs.datajpa.model.CartItem;
import com.crs.datajpa.model.Product;
import com.crs.datajpa.model.Customer;
import com.crs.datajpa.repository.CartRepository;
import com.crs.datajpa.repository.ProductRepository;
import com.crs.datajpa.request.AddItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {


    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    public Cart findCart(Long id) {
        return cartRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException()
        );

    }

    public Cart addItemToCart(AddItemRequest addItem) {

        // Uso de orElseGet(Cart::new): Isso cria um novo Cart apenas se ele não existir, economizando uma verificação explícita.
        // achei meio escondido a implementação, por isso deixei mesmo no if e else
        // Cart cart = cartRepository.findById(addItem.getCartId())
        //       .orElseGet(Cart::new);

        Optional<Cart> isCart = cartRepository.findById(addItem.getCartId());

        Cart cart;

        if (isCart.isEmpty()) {
            cart = new Cart();
        } else {
            cart = isCart.get();
        }

        Product product = productRepository.findById(addItem.getProductId())
                .orElseThrow(() -> new EntityNotFoundException());


        CartItem cartItem = new CartItem();

        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(addItem.getQuantity());


        cart.getCartItems().add(cartItem);

        return cartRepository.save(cart);
    }
}
