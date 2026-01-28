package com.subscription.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.subscription.billing.entity.Invoice;
import com.subscription.billing.service.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    // Get invoices by subscription
    @GetMapping("/subscription/{subscriptionId}")
    public List<Invoice> getInvoicesBySubscription(@PathVariable Long subscriptionId) {
        return invoiceService.getInvoicesBySubscription(subscriptionId);
    }
}
