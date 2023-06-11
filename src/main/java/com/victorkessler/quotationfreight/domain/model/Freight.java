package com.victorkessler.quotationfreight.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Freight {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private Double distanceInMeters;

    private Double princeInCents;

    public Freight(Double distanceInMeters, Double princeInCents) {
        this.distanceInMeters = distanceInMeters;
        this.princeInCents = princeInCents;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Double getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(Double distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }

    public Double getPrinceInCents() {
        return princeInCents;
    }

    public void setPrinceInCents(Double princeInCents) {
        this.princeInCents = princeInCents;
    }
}
