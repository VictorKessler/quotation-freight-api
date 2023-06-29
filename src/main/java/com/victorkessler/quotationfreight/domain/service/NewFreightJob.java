package com.victorkessler.quotationfreight.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victorkessler.quotationfreight.infrastructure.request.NewFreightRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NewFreightJob {

    KafkaTemplate<String, String> kafkaTemplate;

    public NewFreightJob(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 20000)
    public void newFreightCreator() throws JsonProcessingException {
        final var request = getNewFreightRequest();

        sendMessage(request);
    }

    private NewFreightRequest getNewFreightRequest() {

        final var originCoordinates = getRandomCoordinates();
        final var latitude1 = originCoordinates[0];
        final var longitude1 = originCoordinates[1];

        final var destinyCoordinates = getRandomCoordinates();
        final var latitude2 = destinyCoordinates[0];
        final var longitude2 = destinyCoordinates[1];


        return new NewFreightRequest(latitude1, longitude1, latitude2, longitude2);
    }

    public void sendMessage(NewFreightRequest msg) throws JsonProcessingException {
        kafkaTemplate.send("quotation-freight.new-freight", new ObjectMapper().writeValueAsString(msg));
    }

    public double[] getRandomCoordinates() {
        Random random = new Random();
        double latitude = -90 + 180 * random.nextDouble();
        double longitude = -180 + 360 * random.nextDouble();
        return new double[]{latitude, longitude};
    }

}
