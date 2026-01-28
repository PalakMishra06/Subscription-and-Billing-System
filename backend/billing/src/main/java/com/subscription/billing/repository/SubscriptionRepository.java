package com.subscription.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subscription.billing.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByStatus(String status);
    List<Subscription> findByUserId(Long userId);

}
