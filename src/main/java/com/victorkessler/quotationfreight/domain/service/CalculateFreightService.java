package com.victorkessler.quotationfreight.domain.service;

import com.deliverypf.gis.sdk.distance.GisDistanceCalculator;
import com.victorkessler.quotationfreight.infrastructure.repository.FreightPerKmRepository;
import com.victorkessler.quotationfreight.infrastructure.repository.FreightRepository;
import com.victorkessler.quotationfreight.infrastructure.request.NewFreightRequest;
import com.victorkessler.quotationfreight.domain.model.Freight;
import com.victorkessler.quotationfreight.domain.model.FreightPerKm;
import org.springframework.stereotype.Service;

@Service
public class CalculateFreightService {
    private FreightPerKmRepository freightPerKmRepository;
    private FreightRepository freightRepository;

    public CalculateFreightService(FreightPerKmRepository freightPerKmRepository, FreightRepository freightRepository) {
        this.freightPerKmRepository = freightPerKmRepository;
        this.freightRepository = freightRepository;
    }

    public Freight calculate(NewFreightRequest request) {
        final Long distanceInMeters = getGeodesicDistance(request.latitude1(), request.longitude1(), request.latitude2(), request.longitude2());
        final var freightPerKmsRanges = freightPerKmRepository.findAll();

        for (FreightPerKm freightPerKms : freightPerKmsRanges) {
            if (freightPerKms.getDistanceInMeters() >= distanceInMeters) {
                final var priceInCents = freightPerKms.getPriceInCentsPerMeter();
                final Freight freight = new Freight(distanceInMeters.intValue(), priceInCents);

                return freightRepository.save(freight);
            }
        }

        throw new RuntimeException();
    }

    public Long getGeodesicDistance(double originLatitude,
                                    double originLongitude,
                                    double destinationLatitude,
                                    double destinationLongitude) {
        var distance = GisDistanceCalculator.GEODETIC.distance(originLatitude, originLongitude, destinationLatitude, destinationLongitude) * 1000;
        return Math.round(distance);
    }
}
