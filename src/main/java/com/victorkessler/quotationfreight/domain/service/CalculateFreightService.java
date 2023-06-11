package com.victorkessler.quotationfreight.domain.service;

import com.victorkessler.quotationfreight.application.repository.FreightPerKmRepository;
import com.victorkessler.quotationfreight.application.repository.FreightRepository;
import com.victorkessler.quotationfreight.application.request.NewFreightRequest;
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
        final var distanceInMeters = calculateDistance(request.latitude1(), request.longitude1(), request.latitude2(), request.longitude2());
        double priceInCents;
        final var freightPerKmsRanges = freightPerKmRepository.findAll();

        for (FreightPerKm freightPerKms : freightPerKmsRanges) {
            if (distanceInMeters <= freightPerKms.getDistanceInMeters()) {
                priceInCents = distanceInMeters * freightPerKms.getPriceInCentsPerMeter();
                final var newFreight = new Freight(distanceInMeters, priceInCents);

                return freightRepository.save(newFreight);
            }
        }

        throw new RuntimeException();
    }

    private static double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        //double earthRadius = 3958.75;//miles
        double earthRadius = 6371;//kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        return dist * 1000; //in meters
    }
}
