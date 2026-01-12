package com.example.quickship.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PacakgeCreateRequest {
    @NotNull
    private String destination;

    @NotNull
    @Positive
    private double weight;

    @NotNull
    private DeliveryType deliveryType;

    public String getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }
}
