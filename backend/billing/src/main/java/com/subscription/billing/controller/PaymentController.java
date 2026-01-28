package com.subscription.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.subscription.billing.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Pay invoice
    @PostMapping("/pay/{invoiceId}")
    public String payInvoice(@PathVariable Long invoiceId) {
        paymentService.payInvoice(invoiceId);
        return "Payment Successful";
    }
}
