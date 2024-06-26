package com.crs.datajpa.repository;

import com.crs.datajpa.model.CartItem;
import com.crs.datajpa.model.CartItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemPK> {
}
