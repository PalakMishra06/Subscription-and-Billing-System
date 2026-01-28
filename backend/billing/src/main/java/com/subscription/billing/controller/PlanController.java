package com.subscription.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.subscription.billing.entity.Plan;
import com.subscription.billing.service.PlanService;

@RestController
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    private PlanService planService;

    // Create plan
    @PostMapping("/add")
    public String addPlan(@RequestBody Plan plan) {
        planService.createPlan(plan);
        return "Plan Added Successfully";
    }

  
    // Get all plans
    @GetMapping("/all")
    public List<Plan> getAllPlans() {
        return planService.getAllPlans();
    }
}
