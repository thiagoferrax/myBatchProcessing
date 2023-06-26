package com.thiagoferraz.myBatchProcessing.controllers;

import com.thiagoferraz.myBatchProcessing.entities.Payment;
import com.thiagoferraz.myBatchProcessing.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping
    public Iterable<Payment> findAllPayments(){
        return paymentRepository.findAll();
    }

}
