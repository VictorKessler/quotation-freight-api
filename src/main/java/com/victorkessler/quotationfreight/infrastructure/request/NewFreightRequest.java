package com.victorkessler.quotationfreight.infrastructure.request;

public record NewFreightRequest(
        double originLatitude,
        double originLongitude,
        double destinationLatitude,
        double destinationLongitude){}
