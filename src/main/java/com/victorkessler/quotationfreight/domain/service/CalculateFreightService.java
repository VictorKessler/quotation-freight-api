package com.victorkessler.quotationfreight.domain.service;

import com.deliverypf.gis.sdk.distance.GisDistanceCalculator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.victorkessler.quotationfreight.domain.model.Freight;
import com.victorkessler.quotationfreight.domain.model.FreightAvro;
import com.victorkessler.quotationfreight.domain.model.FreightPerKm;
import com.victorkessler.quotationfreight.infrastructure.repository.FreightPerKmRepository;
import com.victorkessler.quotationfreight.infrastructure.repository.FreightRepository;
import com.victorkessler.quotationfreight.infrastructure.request.NewFreightRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CalculateFreightService {
    private FreightPerKmRepository freightPerKmRepository;
    private FreightRepository freightRepository;

    private KafkaTemplate kafkaTemplate;

    public CalculateFreightService(FreightPerKmRepository freightPerKmRepository, FreightRepository freightRepository, KafkaTemplate kafkaTemplate) {
        this.freightPerKmRepository = freightPerKmRepository;
        this.freightRepository = freightRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Freight calculate(NewFreightRequest request) throws JsonProcessingException {
        final Long distanceInMeters = getGeodesicDistance(request.latitude1(), request.longitude1(), request.latitude2(), request.longitude2());
        final var freightPerKmsRanges = freightPerKmRepository.findAll();

        for (FreightPerKm freightPerKms : freightPerKmsRanges) {
            if (freightPerKms.getDistanceInMeters() >= distanceInMeters) {
                final var priceInCents = freightPerKms.getPriceInCentsPerMeter();
                final Freight freight = new Freight(distanceInMeters.intValue(), priceInCents);

                final var persistedFreight = freightRepository.save(freight);

                sendMessage(persistedFreight);

                return persistedFreight;
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

    public void sendMessage(Freight msg) throws JsonProcessingException {

        final var freightAvro = FreightAvro.newBuilder()
                .setId(msg.getUuid())
                .setDistanceInMeters(msg.getDistanceInMeters())
                .setPriceInCents(msg.getPrinceInCents())
                .build();

        kafkaTemplate.send("quotation-freight.calculated-freight", freightAvro);
    }

}
