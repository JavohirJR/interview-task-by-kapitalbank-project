package com.javohir.lastproject.repository;

import com.javohir.lastproject.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
