package com.thiagoferraz.myBatchProcessing.utils;

import com.thiagoferraz.myBatchProcessing.entities.Payment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CSVGenerator {
    private static final String[] PAYER_NAMES = {"John Doe", "Jane Smith", "David Johnson", "Emily Brown", "Michael Wilson"};
    private static final String[] ADDITIONAL_INFO = {"Payment for services rendered", "Payment for product purchase", "Payment for monthly subscription", "Payment for consultation"};

    public static void main(String[] args) {
        String csvFile = "payments.csv";
        int numOfRecords = 1000;

        try (FileWriter writer = new FileWriter(csvFile)) {
            // Write CSV header
            writer.append("id,payer_name,amount,invoice_number,additional_info\n");

            // Generate and write payment records
            for (int i = 1; i <= numOfRecords; i++) {
                Payment payment = generateRandomPayment(i);
                writer.append(String.valueOf(payment.getId())).append(",");
                writer.append(payment.getPayerName()).append(",");
                writer.append(String.valueOf(payment.getAmount())).append(",");
                writer.append(payment.getInvoiceNumber()).append(",");
                writer.append(payment.getAdditionalInfo()).append("\n");
            }

            System.out.println("CSV file generated successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while generating CSV file: " + e.getMessage());
        }
    }

    private static Payment generateRandomPayment(int id) {
        Random random = new Random();
        String payerName = PAYER_NAMES[random.nextInt(PAYER_NAMES.length)];
        double amount = random.nextDouble() * 1000; // Random amount between 0 and 1000
        String invoiceNumber = "INV-" + String.format("%03d", random.nextInt(1000)); // Random invoice number between INV-001 and INV-999
        String additionalInfo = ADDITIONAL_INFO[random.nextInt(ADDITIONAL_INFO.length)];
        return new Payment(id, payerName, amount, invoiceNumber, additionalInfo);
    }
}
