package com.thiagoferraz.myBatchProcessing.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    @Id
    private Integer id;
    private String payerName;
    private double amount;
    private String invoiceNumber;
    private String additionalInfo;
}