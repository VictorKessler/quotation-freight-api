package com.victorkessler.quotationfreight.domain.service;

import com.victorkessler.quotationfreight.application.repository.FreightPerKmRepository;
import com.victorkessler.quotationfreight.application.request.NewFreight;
import com.victorkessler.quotationfreight.domain.model.FreightPerKm;
import org.springframework.stereotype.Service;

@Service
public class CalculateFreightService {
    private FreightPerKmRepository repository;

    public CalculateFreightService(FreightPerKmRepository repository) {
        this.repository = repository;
    }

    public FreightPerKm calculate (NewFreight newFreight) {
        Integer distanceInMeters = newFreight.distanceInMeters();
        Integer pricePerMeters = newFreight.pricePerMeters();

        Integer calculatedFreight = ((distanceInMeters * pricePerMeters)/100);

        FreightPerKm freightPerKm = new FreightPerKm(distanceInMeters, pricePerMeters, calculatedFreight.longValue());

        return repository.save(freightPerKm);
    }
}
