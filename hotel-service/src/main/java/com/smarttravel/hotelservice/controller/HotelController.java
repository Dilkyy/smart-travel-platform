
package com.smarttravel.hotelservice.controller;

import com.smarttravel.hotelservice.dto.HotelDTO;
import com.smarttravel.hotelservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
        import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotel(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @PostMapping
    public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO dto) {
        HotelDTO created = hotelService.createHotel(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}

