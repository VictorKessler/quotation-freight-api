package com.victorkessler.quotationfreight.application.entrypoint;

import com.victorkessler.quotationfreight.application.request.NewFreightRequest;
import com.victorkessler.quotationfreight.domain.model.Freight;
import com.victorkessler.quotationfreight.domain.service.CalculateFreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    private CalculateFreightService service;

    @PostMapping("/new-freight")
    public ResponseEntity<Freight> newFreight(@RequestBody NewFreightRequest request) {
        return ResponseEntity.ok(service.calculate(request));
    }

}
