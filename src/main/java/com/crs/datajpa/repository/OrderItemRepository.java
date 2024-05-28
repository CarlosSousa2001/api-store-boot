package com.crs.datajpa.repository;

import com.crs.datajpa.model.OrderItem;
import com.crs.datajpa.model.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
