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
    private UUID uuid;
    private Integer distanceInMeters;
    private Integer pricePerMeter;

    public FreightPerKm(UUID uuid, Integer distanceInMeters, Integer pricePerMeter) {
        this.uuid = uuid;
        this.distanceInMeters = distanceInMeters;
        this.pricePerMeter = pricePerMeter;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(Integer distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }

    public Integer getPricePerMeter() {
        return pricePerMeter;
    }

    public void setPricePerMeter(Integer pricePerMeter) {
        this.pricePerMeter = pricePerMeter;
    }
}
