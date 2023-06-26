package com.victorkessler.quotationfreight.infrastructure.sync;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victorkessler.quotationfreight.infrastructure.request.NewFreightRequest;
import com.victorkessler.quotationfreight.domain.model.Freight;
import com.victorkessler.quotationfreight.domain.service.CalculateFreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    private CalculateFreightService service;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/new-freight")
    public ResponseEntity<Freight> newFreight(@RequestBody NewFreightRequest request) throws JsonProcessingException {
        sendMessage(request);
        return ResponseEntity.ok(service.calculate(request));
    }

    public void sendMessage(NewFreightRequest msg) throws JsonProcessingException {
        kafkaTemplate.send("quotation-freight.new-freight", new ObjectMapper().writeValueAsString(msg));
    }

}
