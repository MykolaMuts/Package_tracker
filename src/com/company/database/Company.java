package com.company.database;

public class Company {
    private final String name;
    private final String deliveryPeriod;
    private final double priceLimit;

    public Company(String name, String deliveryPeriod, double priceLimit) {
        this.name = name;
        this.deliveryPeriod = deliveryPeriod;
        this.priceLimit = priceLimit;
    }

    public String getName() {
        return name;
    }

    public String getDeliveryPeriod() {
        return deliveryPeriod;
    }

    public double getPriceLimit() {
        return priceLimit;
    }
}