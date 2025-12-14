
package com.smarttravel.bookingservice.client;

import com.smarttravel.bookingservice.dto.HotelDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "hotel-service", url = "http://localhost:8083")
public interface HotelClient {

    @GetMapping("/api/hotels/{id}")
    HotelDTO getHotelById(@PathVariable Long id);
}

