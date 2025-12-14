package com.smarttravel.bookingservice.client;

import com.smarttravel.bookingservice.dto.FlightDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "flight-service", url = "http://localhost:8082")
public interface FlightClient {

    @GetMapping("/api/flights/{id}")
    FlightDTO getFlightById(@PathVariable Long id);
}
