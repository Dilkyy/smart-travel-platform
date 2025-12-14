package com.smarttravel.flightservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightDTO {

    private Long id;
    private String fromLocation;
    private String toLocation;
    private String date;
    private Double price;
    private Integer availableSeats;
}
