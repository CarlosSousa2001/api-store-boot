package com.crs.datajpa.service;

import com.crs.datajpa.exceptions.EntityNotFoundException;
import com.crs.datajpa.model.Cart;
import com.crs.datajpa.model.CartItem;
import com.crs.datajpa.model.Product;
import com.crs.datajpa.model.Customer;
import com.crs.datajpa.model.dto.CartDTO;
import com.crs.datajpa.model.dto.CartItemDTO;
import com.crs.datajpa.repository.CartItemRepository;
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
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerService customerService;

    public Cart findCart(Long id) {
        return cartRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException()
        );

    }

    public CartDTO addItemToCart(CartDTO addItem) {

        // Uso de orElseGet(Cart::new): Isso cria um novo Cart apenas se ele não existir, economizando uma verificação explícita.
        // achei meio escondido a implementação, por isso deixei mesmo no if e else
        // Cart cart = cartRepository.findById(addItem.getCartId())
        //       .orElseGet(Cart::new);

        Customer customer = customerService.authenticated();

        Optional<Cart> isCart = cartRepository.findById(addItem.getCartId());

        Cart cart;

        if (isCart.isEmpty()) {
            cart = new Cart();
        } else {
            cart = isCart.get();
        }

        if (cart.getCustomer() == null) {
            cart.setCustomer(customer);
            cartRepository.save(cart);
        }

        if (!cart.getCustomer().equals(customer)) throw new EntityNotFoundException();

        for(CartItemDTO item : addItem.getItems() ){

            Product product = productRepository.getReferenceById(item.getProductId());

            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setPrice(item.getPrice());
            cartItem.setQuantity(item.getQuantity());


            cart.getCartItems().add(cartItem);
        }

        cartRepository.save(cart);
        cartItemRepository.saveAll(cart.getCartItems());

        return new CartDTO(cart);
    }

    public Cart getCart(Long id, Customer customer) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        // Se o usuário existir e o carrinho não tiver um usuário associado, associar o usuário ao carrinho
        if (cart.getCustomer() == null) {
            cart.setCustomer(customer);
            cartRepository.save(cart); // Atualiza o carrinho com o usuário associado
        }

        // preciso verificar se caso o cart ja tenha um usuario associado é o mesmo id do usuario que eu mandei no json
        if (!cart.getCustomer().equals(customer)) throw new EntityNotFoundException();

        return cart;
    }

}
