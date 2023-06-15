package com.victorkessler.quotationfreight.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victorkessler.quotationfreight.application.entrypoint.SampleController;
import com.victorkessler.quotationfreight.application.repository.FreightRepository;
import com.victorkessler.quotationfreight.application.request.NewFreightRequest;
import com.victorkessler.quotationfreight.configuration.AbstractTest;
import com.victorkessler.quotationfreight.configuration.TestContextConfiguration;
import com.victorkessler.quotationfreight.domain.service.CalculateFreightService;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = TestContextConfiguration.class)
@AutoConfigureMockMvc
public class CalculateFreightServiceTest extends AbstractTest {

    @MockBean
    private CalculateFreightService service;
    @MockBean
    private FreightRepository repository;
    @MockBean
    private SampleController controller;

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("""
                Given a valid request of latitude and longitude,
                then it should return 200 ok
            """)
    public void t1() throws Exception {
        final var request = getContent("data/new-freight-valid-request.json");

        mockMvc.perform(post("/new-freight")
                        .contentType("application/json")
                        .content(request))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("""
                Given an invalid request of latitude and longitude,
                then it should return a 400 bad request
            """)
    public void t2() throws Exception {
        final var request = getContent("data/new-freight-invalid-request.json");

        mockMvc.perform(post("/new-freight")
                        .contentType("application/json")
                        .content(request))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("""
                Given a valid request of latitude and longitude,
                then it should be persisted in database
            """)
    public void t3() throws Exception {
        final var request = getContent("data/new-freight-valid-request.json");

        final var newFreightRequest = new ObjectMapper().readValue(request, NewFreightRequest.class);

        final var newFreight = service.calculate(newFreightRequest);

        final var freightList = repository.findAll();

        freightList.forEach(freight -> Assertions.assertEquals(freight, newFreight));
    }

}
