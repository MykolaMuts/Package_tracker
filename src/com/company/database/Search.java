package com.company.database;

import java.util.Date;

public class Search {
    private final String name;
    private final double weight;
    private final double price;
    private final Date deliveryDate;
    public final boolean isLate;

    public Search(String name, double weight, double price, Date deliveryDate, boolean isLate) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.deliveryDate = deliveryDate;
        this.isLate = isLate;
    }



    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public boolean isLate() {
        return isLate;
    }
}
