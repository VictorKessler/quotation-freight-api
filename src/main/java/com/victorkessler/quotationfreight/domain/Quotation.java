package com.victorkessler.quotationfreight.domain;

public class Quotation {
    private Long price;
    private Long distanceInMeters;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(Long distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }
}
