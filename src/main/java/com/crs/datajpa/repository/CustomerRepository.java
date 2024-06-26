package com.crs.datajpa.repository;

import com.crs.datajpa.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByEmail(String username);

//    @Query("SELECT u.role from Customer u WHERE u.username LIKE :username")
//    Customer.Role findByRoleWhereUsername(String username);
}
