
package com.smarttravel.bookingservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelDTO {
    private Long id;
    private String name;
    private String location;
    private Double pricePerNight;
    private Integer availableRooms;
}
