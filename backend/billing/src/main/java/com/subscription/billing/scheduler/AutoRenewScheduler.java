package com.subscription.billing.scheduler;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.subscription.billing.entity.Invoice;
import com.subscription.billing.entity.Plan;
import com.subscription.billing.entity.Subscription;
import com.subscription.billing.repository.InvoiceRepository;
import com.subscription.billing.repository.SubscriptionRepository;

@Component
public class AutoRenewScheduler {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    // Runs daily at midnight
    @Scheduled(cron = "0 0 0 * * ?")
    public void autoRenew() {

        List<Subscription> subs =
                subscriptionRepository.findByStatus("ACTIVE");

        for (Subscription sub : subs) {

            if (sub.getEndDate().isBefore(LocalDate.now())) {

                Plan plan = sub.getPlan();

                // Extend end date
                if (plan.getDuration().equalsIgnoreCase("MONTHLY")) {
                    sub.setEndDate(sub.getEndDate().plusMonths(1));
                } else {
                    sub.setEndDate(sub.getEndDate().plusYears(1));
                }

                subscriptionRepository.save(sub);

                // Create new invoice
                Invoice invoice = new Invoice();
                invoice.setSubscription(sub);
                invoice.setAmount(plan.getPrice());
                invoice.setInvoiceDate(LocalDate.now());
                invoice.setStatus("UNPAID");

                invoiceRepository.save(invoice);
            }
        }
    }
}
