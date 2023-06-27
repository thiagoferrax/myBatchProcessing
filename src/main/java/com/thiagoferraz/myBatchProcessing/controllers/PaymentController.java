package com.thiagoferraz.myBatchProcessing.controllers;

import com.thiagoferraz.myBatchProcessing.entities.Payment;
import com.thiagoferraz.myBatchProcessing.repositories.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final static Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @GetMapping
    public Iterable<Payment> findAllPayments(){
        return paymentRepository.findAll();
    }

    @PostMapping("/import")
    public ResponseEntity<String> importPayments() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("startAt", System.currentTimeMillis())
                    .toJobParameters();
            JobExecution jobExecution = jobLauncher.run(job, jobParameters);

            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                LOGGER.info("Payment import job completed successfully.");
                return ResponseEntity.ok("Payment import job completed successfully.");
            } else {
                String errorMessage = "Payment import job failed with status: " + jobExecution.getStatus();
                LOGGER.error(errorMessage);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
            }
        } catch (JobExecutionException e) {
            String errorMessage = "Error when processing the payments.";
            LOGGER.error(errorMessage, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
