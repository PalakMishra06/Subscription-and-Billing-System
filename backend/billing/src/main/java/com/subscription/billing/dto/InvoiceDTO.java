package com.subscription.billing.dto;

import java.time.LocalDate;

public class InvoiceDTO {
    private Long invoiceId;
    private Long subscriptionId;
    private double totalAmount;
    private LocalDate issuedDate;
    private String invoiceNumber;
}
