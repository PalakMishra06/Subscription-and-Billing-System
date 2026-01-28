package com.subscription.billing.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subscription.billing.entity.Invoice;
import com.subscription.billing.entity.Payment;
import com.subscription.billing.repository.InvoiceRepository;
import com.subscription.billing.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    // Pay invoice
    public void payInvoice(Long invoiceId) {

        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        invoice.setStatus("PAID");
        invoiceRepository.save(invoice);

        Payment payment = new Payment();
        payment.setInvoice(invoice);
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentMode("CARD");
        payment.setPaymentStatus("SUCCESS");

        paymentRepository.save(payment);
    }
}
