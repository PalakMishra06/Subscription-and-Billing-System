package com.subscription.billing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subscription.billing.entity.Invoice;
import com.subscription.billing.entity.Subscription;
import com.subscription.billing.repository.InvoiceRepository;
import com.subscription.billing.repository.SubscriptionRepository;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    // Get invoices by subscription
    public List<Invoice> getInvoicesBySubscription(Long subscriptionId) {

        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        return invoiceRepository.findBySubscription(subscription);
    }
}
