package com.victorkessler.quotationfreight.application;

import com.victorkessler.quotationfreight.application.entrypoint.SampleController;
import com.victorkessler.quotationfreight.application.repository.FreightRepository;
import com.victorkessler.quotationfreight.application.request.NewFreightRequest;
import com.victorkessler.quotationfreight.configuration.AbstractTest;
import com.victorkessler.quotationfreight.configuration.TestContextConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;

@ContextConfiguration(classes = TestContextConfiguration.class)
public class CalculateFreightServiceTest extends AbstractTest {

    @Autowired
    SampleController controller;
    @Autowired
    FreightRepository repository;

    NewFreightRequest request;

    @BeforeEach
    void init() throws IOException, ClassNotFoundException {
        request = getRequest("src/test/resources/data/new-freight-request.json", NewFreightRequest.class);
    }


    @Test
    @DisplayName("""
                When a valid request of latitude and longitude comes,
                then it should calculate the geodesic distance between the two points,
                return the price in cents and persist in database 
            """)
    void t1() {

    }

}
