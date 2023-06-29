package com.victorkessler.quotationfreight.infrastructure.async.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victorkessler.quotationfreight.domain.service.CalculateFreightService;
import com.victorkessler.quotationfreight.infrastructure.request.NewFreightRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NewFreightConsumer {

    private CalculateFreightService service;

    public NewFreightConsumer(CalculateFreightService service) {
        this.service = service;
    }

    @KafkaListener(topics = "quotation-freight.new-freight", groupId = "freight-request")
    public void calculateNewFreightRequest(String msg) throws JsonProcessingException {

        service.calculate(new ObjectMapper().convertValue(msg, NewFreightRequest.class));
    }

}
