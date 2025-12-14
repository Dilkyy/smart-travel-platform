package com.smarttravel.bookingservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequestDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long flightId;

    @NotNull
    private Long hotelId;

    @NotNull
    private String travelDate;
}
