package com.smarttravel.bookingservice.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long flightId;
    private Long hotelId;

    private String travelDate;

    private Double totalCost;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
