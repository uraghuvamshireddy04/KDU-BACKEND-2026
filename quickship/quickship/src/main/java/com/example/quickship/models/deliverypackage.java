package com.example.quickship.models;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class deliverypackage {
    private String id;
    private String destination;
    private double weight;
    private PackageStatus status;
    private  DeliveryType deliveryType;

    public deliverypackage() {}

public deliverypackage(String destination, double weight, PackageStatus status, DeliveryType deliveryType) {
    this.id = UUID.randomUUID().toString();
    this.destination = destination;
    this.weight = weight;
    this.status = status;
    this.deliveryType = deliveryType;
}

}
