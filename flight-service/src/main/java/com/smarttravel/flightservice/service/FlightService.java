package com.smarttravel.flightservice.service;
import com.smarttravel.flightservice.dto.FlightDTO;

public interface FlightService {
    FlightDTO getFlightById(Long id);
    FlightDTO createFlight(FlightDTO dto);
}
