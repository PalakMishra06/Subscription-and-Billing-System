package com.subscription.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subscription.billing.entity.Invoice;
import com.subscription.billing.entity.Subscription;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findBySubscription(Subscription subscription);
}
