package com.smarttravel.bookingservice.dto;

import com.smarttravel.bookingservice.entity.BookingStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponseDTO {

    private Long id;
    private Long userId;
    private Long flightId;
    private Long hotelId;
    private String travelDate;
    private Double totalCost;
    private BookingStatus status;
}

