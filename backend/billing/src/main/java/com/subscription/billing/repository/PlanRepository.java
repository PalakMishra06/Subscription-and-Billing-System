package com.subscription.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.subscription.billing.entity.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

    // Optional: find plan by name
    Plan findByName(String name);
}
