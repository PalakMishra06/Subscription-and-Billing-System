package com.subscription.billing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subscription.billing.entity.Plan;
import com.subscription.billing.repository.PlanRepository;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    // Create plan
    public Plan createPlan(Plan plan) {
        return planRepository.save(plan);
    }

    // Get all plans
    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    // Get plan by id
    public Plan getPlanById(Long id) {
        return planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
    }
}
