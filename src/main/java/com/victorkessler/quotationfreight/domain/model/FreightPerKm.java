package com.victorkessler.quotationfreight.domain.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FreightPerKm {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Integer distanceInMeters;
    private Integer priceInCentsPerMeter;

    public FreightPerKm() {
    }

    public FreightPerKm(Integer distanceInMeters, Integer priceInCentsPerMeter) {
        this.distanceInMeters = distanceInMeters;
        this.priceInCentsPerMeter = priceInCentsPerMeter;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(Integer distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }

    public Integer getPriceInCentsPerMeter() {
        return priceInCentsPerMeter;
    }

    public void setPriceInCentsPerMeter(Integer priceInCentsPerMeter) {
        this.priceInCentsPerMeter = priceInCentsPerMeter;
    }
}
