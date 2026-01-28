package com.subscription.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.subscription.billing.dto.SubscriptionDTO;  // ✅ Import DTO
import com.subscription.billing.service.SubscriptionService;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    // Subscribe user to plan + generate invoice
    @PostMapping("/subscribe")
    public String subscribe(@RequestParam Long userId, @RequestParam Long planId) {
        subscriptionService.subscribeUser(userId, planId);
        return "Subscription Activated & Invoice Generated";
    }

    // Get subscriptions of a user
    @GetMapping("/user/{userId}")
    public List<SubscriptionDTO> getUserSubscriptions(@PathVariable Long userId) {
        // ✅ Call service method that returns DTO
        return subscriptionService.getUserSubscriptions(userId);
    }

    @PutMapping("/deactivate/{id}")
    public String deactivate(@PathVariable Long id) {
        subscriptionService.deactivateSubscription(id);
        return "Subscription Deactivated";
    }

}
