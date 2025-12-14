package com.smarttravel.flightservice.service.impl;

import com.smarttravel.flightservice.dto.FlightDTO;
import com.smarttravel.flightservice.entity.Flight;
import com.smarttravel.flightservice.exception.ResourceNotFoundException;
import com.smarttravel.flightservice.repository.FlightRepository;
import com.smarttravel.flightservice.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public FlightDTO getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + id));
        return mapToDTO(flight);
    }

    @Override
    public FlightDTO createFlight(FlightDTO dto) {
        Flight flight = Flight.builder()
                .fromLocation(dto.getFromLocation())
                .toLocation(dto.getToLocation())
                .date(dto.getDate())
                .price(dto.getPrice())
                .availableSeats(dto.getAvailableSeats())
                .build();
        Flight saved = flightRepository.save(flight);
        return mapToDTO(saved);
    }

    private FlightDTO mapToDTO(Flight flight) {
        return FlightDTO.builder()
                .id(flight.getId())
                .fromLocation(flight.getFromLocation())
                .toLocation(flight.getToLocation())
                .date(flight.getDate())
                .price(flight.getPrice())
                .availableSeats(flight.getAvailableSeats())
                .build();
    }
}
