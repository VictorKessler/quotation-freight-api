package com.victorkessler.quotationfreight.infrastructure.async.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.victorkessler.quotationfreight.domain.service.CalculateFreightService;
import com.victorkessler.quotationfreight.infrastructure.request.NewFreightRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class NewFreightConsumer {

    private CalculateFreightService service;

    public NewFreightConsumer(CalculateFreightService service) {
        this.service = service;
    }

    @KafkaListener(topics = "quotation-freight.new-freight", groupId = "freight-request")
    public void calculateNewFreightRequest(Message<NewFreightRequest> msg) throws JsonProcessingException{

        NewFreightRequest payload = msg.getPayload();
        final var newFreightRequest = new NewFreightRequest(payload.latitude1(),
                payload.longitude1(),
                payload.latitude2(),
                payload.longitude2());

        service.calculate(newFreightRequest);
    }

}
