package com.subscription.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.subscription.billing.repository.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private SubscriptionRepository subRepo;
    @Autowired
    private PaymentRepository payRepo;

    @GetMapping("/stats")
    public String stats() {
        return "Users: " + userRepo.count()
             + ", Subscriptions: " + subRepo.count()
             + ", Payments: " + payRepo.count();
    }
}
