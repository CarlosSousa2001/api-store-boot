package com.crs.datajpa.model.dto;

import com.crs.datajpa.model.Order;
import com.crs.datajpa.model.OrderItem;
import com.crs.datajpa.model.PaymentStatus;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private Long id;

    @NotEmpty
    private long cartId;

    private Instant moment;

    private PaymentStatus paymentStatus;

    private CustomerDTO customerDTO;

    private List<OrdemItemDto> items = new ArrayList<>();


    public OrderDTO() {
    }

    public OrderDTO(Long id, long cartId, Instant moment, PaymentStatus paymentStatus, CustomerDTO customerDTO, List<OrdemItemDto> items) {
        this.id = id;
        this.cartId = cartId;
        this.moment = moment;
        this.paymentStatus = paymentStatus;
        this.customerDTO = customerDTO;
        this.items = items;
    }

    public OrderDTO(Order entity) {
        this.id = entity.getId();
        this.moment = entity.getMoment();
        this.paymentStatus = (entity.getPayment().getStatus() == null) ? PaymentStatus.PROCESSING : entity.getPayment().getStatus();
        this.customerDTO = new CustomerDTO(entity.getCustomer());
        for(OrderItem item: entity.getOrderItems()){
            OrdemItemDto dto = new OrdemItemDto(item);
            items.add(dto);
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

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public List<OrdemItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrdemItemDto> items) {
        this.items = items;
    }
}
