package com.victorkessler.quotationfreight.infrastructure.request;

public record NewFreightRequest(
        Double latitude1,
        Double longitude1,
        Double latitude2,
        Double longitude2
) {}
