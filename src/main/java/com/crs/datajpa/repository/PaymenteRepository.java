package com.crs.datajpa.repository;

import com.crs.datajpa.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymenteRepository extends JpaRepository<Payment, Long> {
}
