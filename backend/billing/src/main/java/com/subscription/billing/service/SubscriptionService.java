package com.subscription.billing.service;

import com.subscription.billing.dto.SubscriptionDTO;
import com.subscription.billing.entity.Plan;
import com.subscription.billing.entity.Subscription;
import com.subscription.billing.entity.User;
import com.subscription.billing.repository.PlanRepository;
import com.subscription.billing.repository.SubscriptionRepository;
import com.subscription.billing.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final PlanRepository planRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository,
                               UserRepository userRepository,
                               PlanRepository planRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }
    
    // ✅ ADD THIS METHOD
    public void subscribeUser(Long userId, Long planId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setPlan(plan);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusMonths(1));
        subscription.setStatus("ACTIVE");

        subscriptionRepository.save(subscription);
    }
    
    public void deactivateSubscription(Long subscriptionId) {
        Subscription sub = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        sub.setStatus("DEACTIVATED");
        sub.setEndDate(LocalDate.now());

        subscriptionRepository.save(sub);
    }


    // Get subscriptions of a user and convert to DTO
    public List<SubscriptionDTO> getUserSubscriptions(Long userId) {
        List<Subscription> subscriptions = subscriptionRepository.findByUserId(userId);

        // Convert List<Subscription> to List<SubscriptionDTO>
        List<SubscriptionDTO> subscriptionDTOs = subscriptions.stream().map(sub -> {
            SubscriptionDTO dto = new SubscriptionDTO();
            dto.setSubscriptionId(sub.getId());
            dto.setUserName(sub.getUser() != null ? sub.getUser().getName() : null);
            dto.setPlanName(sub.getPlan() != null ? sub.getPlan().getName() : null);
            dto.setPlanPrice(sub.getPlan() != null ? sub.getPlan().getPrice() : 0);
            dto.setStartDate(sub.getStartDate());
            dto.setEndDate(sub.getEndDate());
            dto.setStatus(sub.getStatus());
            return dto;
        }).collect(Collectors.toList());

        return subscriptionDTOs;
    }
}
