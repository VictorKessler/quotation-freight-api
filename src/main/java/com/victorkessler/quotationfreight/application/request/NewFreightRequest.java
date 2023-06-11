package com.victorkessler.quotationfreight.application.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NewFreightRequest(
        @JsonProperty
        Double latitude1,
        @JsonProperty
        Double longitude1,
        @JsonProperty
        Double latitude2,
        @JsonProperty
        Double longitude2
) {}
