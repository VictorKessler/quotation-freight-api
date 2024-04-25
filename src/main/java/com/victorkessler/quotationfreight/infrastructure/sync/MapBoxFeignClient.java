package com.victorkessler.quotationfreight.infrastructure.sync;

import com.victorkessler.quotationfreight.infrastructure.response.RouteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "mapBox-Client",
        url = "https://api.mapbox.com/directions/v5/mapbox/")
public interface MapBoxFeignClient {
    @GetMapping("/{transportation}/" +
            "{originLatitude}%2C{originLongitude}%3B{destinationLatitude}%2C{destinationLongitude}?" +
            "access_token=${map.box.api.token}")
    RouteResponse getRoute(@PathVariable("transportation") String transportation,
                           @PathVariable("originLatitude") Double originLatitude,
                           @PathVariable("originLongitude") Double originLongitude,
                           @PathVariable("destinationLatitude") Double destinationLatitude,
                           @PathVariable("destinationLongitude") Double destinationLongitude);
}
