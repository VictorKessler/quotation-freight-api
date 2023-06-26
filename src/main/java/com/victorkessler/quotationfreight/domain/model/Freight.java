package com.victorkessler.quotationfreight.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.apache.kafka.common.protocol.types.Field;

import java.util.UUID;

@Entity
public class Freight {
    @Id
    private UUID id;

    private Integer distanceInMeters;

    private Integer priceInCents;

    public Freight() {
    }

    public Freight(Integer distanceInMeters, Integer princeInCents) {
        this.distanceInMeters = distanceInMeters;
        this.priceInCents = princeInCents;
    }

    public UUID getUuid() {
        return id;
    }

    public void setUuid(UUID id) {
        this.id = id;
    }

    public Integer getDistanceInMeters() {
        return distanceInMeters;
    }

    public void setDistanceInMeters(Integer distanceInMeters) {
        this.distanceInMeters = distanceInMeters;
    }

    public Integer getPrinceInCents() {
        return priceInCents;
    }

    public void setPrinceInCents(Integer princeInCents) {
        this.priceInCents = princeInCents;
    }

    @PrePersist
    public void generateUuid(){
        this.id = UUID.randomUUID();
    }
}
