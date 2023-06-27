package com.thiagoferraz.myBatchProcessing.configurations;

import com.thiagoferraz.myBatchProcessing.entities.Payment;
import org.springframework.batch.item.ItemProcessor;


public class PaymentProcessor implements ItemProcessor<Payment, Payment> {

    @Override
    public Payment process(Payment item) throws Exception {
        return item;
    }
}
