package com.thiagoferraz.myBatchProcessing.repositories;

import com.thiagoferraz.myBatchProcessing.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
