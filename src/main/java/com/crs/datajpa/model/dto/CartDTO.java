package com.crs.datajpa.model.dto;

import com.crs.datajpa.model.Cart;
import com.crs.datajpa.model.CartItem;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CartDTO {

    private Long id;
    private long cartId;
    private Instant moment;
    private CustomerDTO customerDTO;

    private List<CartItemDTO> items = new ArrayList<>();

    public CartDTO() {
    }

    public CartDTO(Long id, long cartId, Instant moment, CustomerDTO customerDTO, List<CartItemDTO> items) {
        this.id = id;
        this.cartId = cartId;
        this.moment = moment;
        this.customerDTO = customerDTO;
        this.items = items;
    }

    public CartDTO(Cart entity) {
        this.cartId = entity.getId();
        this.moment = entity.getMoment();
        this.customerDTO = new CustomerDTO(entity.getCustomer());
        for(CartItem item : entity.getCartItems()){
            CartItemDTO itemDto = new CartItemDTO(item);
            items.add(itemDto);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }
}
