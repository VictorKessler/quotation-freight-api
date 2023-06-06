package com.victorkessler.quotationfreight.domain.service;

import com.victorkessler.quotationfreight.application.repository.FreightPerKmRepository;
import com.victorkessler.quotationfreight.application.request.NewFreightRequest;
import com.victorkessler.quotationfreight.domain.model.FreightPerKm;
import org.springframework.stereotype.Service;

@Service
public class CalculateFreightService {
    private FreightPerKmRepository repository;

    public CalculateFreightService(FreightPerKmRepository repository) {
        this.repository = repository;
    }

    public FreightPerKm calculate (NewFreightRequest newFreight) {
        Integer distanceInMeters = newFreight.distanceInMeters();
        Integer pricePerMeters = 0;

        final var freightPerKmsRanges = repository.findAll();

        for (FreightPerKm freightPerKms : freightPerKmsRanges) {
            if (distanceInMeters - freightPerKms.getDistanceInMeters() <= 500) {
                pricePerMeters = distanceInMeters * freightPerKms.getPricePerMeter();
            }
        }

        FreightPerKm freightPerKm = new FreightPerKm(distanceInMeters, pricePerMeters);

        return repository.save(freightPerKm);
    }
}
