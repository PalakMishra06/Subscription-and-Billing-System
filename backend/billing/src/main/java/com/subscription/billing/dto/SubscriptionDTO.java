package com.subscription.billing.dto;

import java.time.LocalDate;

public class SubscriptionDTO {
    private Long subscriptionId;
    private String userName;
    private String planName;
    private double planPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    // Getters and Setters
    public Long getSubscriptionId() { return subscriptionId; }
    public void setSubscriptionId(Long subscriptionId) { this.subscriptionId = subscriptionId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }

    public double getPlanPrice() { return planPrice; }
    public void setPlanPrice(double planPrice) { this.planPrice = planPrice; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
